package com.ecom2.order_service.order;

import com.ecom2.order_service.customer.CustomerClient;
import com.ecom2.order_service.exception.BusinessException;
import com.ecom2.order_service.kafka.OrderConfirmation;
import com.ecom2.order_service.kafka.OrderProducer;
import com.ecom2.order_service.orderLine.OrderLineRequest;
import com.ecom2.order_service.orderLine.OrderlineService;
import com.ecom2.order_service.payment.PaymentClient;
import com.ecom2.order_service.payment.PaymentRequest;
import com.ecom2.order_service.product.ProductClient;
import com.ecom2.order_service.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderlineService orderlineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    public Integer createdOrder(@Valid OrderRequest request) {
        // check the customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order:: No Customer exitsts with the provided ID:"+ request.customerId()));

        // purchase the product --> product-ms
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        // persist order

        var order = this.repository.save(mapper.toOrder(request));
        // persist order lines
        for (PurchaseRequest purchaseRequest: request.products()){
            orderlineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        // send the order confirmation --> notification-ms
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {

        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException("No order found with the provided ID: "+ orderId));
    }
}

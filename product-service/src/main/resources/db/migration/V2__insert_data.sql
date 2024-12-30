-- Insert into category table
INSERT INTO category (id, description, name)
VALUES
    (1, 'Electronics items such as phones, laptops', 'Electronics'),
    (2, 'Furniture items like tables, chairs', 'Furniture');

-- Insert into product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (1, 'Smartphone with 64GB storage', 'Smartphone', 50, 299.99, 1),
    (2, 'Laptop with 8GB RAM and 256GB SSD', 'Laptop', 30, 799.99, 1),
    (3, 'Wooden dining table for 6 people', 'Dining Table', 15, 450.00, 2),
    (4, 'Ergonomic office chair', 'Office Chair', 25, 150.00, 2);

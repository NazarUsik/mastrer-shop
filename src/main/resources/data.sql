INSERT INTO photo(file_name, google_file_id) VALUES ('avatar_default.jpg', '184iIbvreuYO_VFQcUJU73ttVjBc4R_k7');
INSERT INTO photo(file_name, google_file_id) VALUES ('banner_asia-food.jpg', '1kFWPqeKQzNoPSPM2yB9afjrLA1f9kbtg');
INSERT INTO photo(file_name, google_file_id) VALUES ('banner_shop-default.png', '1FiCYCUxFkcMY51BbOf4KJGsd8ywLQQDF');
INSERT INTO photo(file_name, google_file_id) VALUES ('logo_shop-logo.png', '1EppqS79MeqpyHVqvVi39w9sTH-QAn3On');
INSERT INTO photo(file_name, google_file_id) VALUES ('product_default.jpg', '12UicNRef2QWgOTRU6VWjAQPXajxBbXkk');
INSERT INTO photo(file_name, google_file_id) VALUES ('product_laptop.jpg', '1w8irO3QK22xXmv6i62pcQFtfgz31QMrE');

INSERT INTO status(name) VALUES ('WAITING_PAYMENT');
INSERT INTO status(name) VALUES ('DELIVERED');
INSERT INTO status(name) VALUES ('CANCELED');
INSERT INTO status(name) VALUES ('COMPLETED');

INSERT INTO role(name) VALUES ('ADMIN');
INSERT INTO role(name) VALUES ('SELLER');
INSERT INTO role(name) VALUES ('CUSTOMER');

INSERT INTO product_type(name) VALUES ('DIGITAL');
INSERT INTO product_type(name) VALUES ('ELECTRONICS');
INSERT INTO product_type(name) VALUES ('DECORATION');

INSERT INTO shop(name, alias, address, city, state, country, zip_code, email, phone_number, logo_id, banner_id) VALUES ('shop', 'shop', 'address', 'city', 'state', 'country', 101010, 'shop@shop.com', '101010', null, null);
INSERT INTO shop(name, alias, address, city, state, country, zip_code, email, phone_number, logo_id, banner_id) VALUES ('shopDef', 'shop def', 'address', 'city', 'state', 'country', 101010, 'def@shop.com', '010101', null, null);
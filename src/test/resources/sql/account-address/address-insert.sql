insert into permission (id, role_name) values (101, 'ROLE_CUSTOMER');
insert into permission (id, role_name) values (102, 'ROLE_ADMIN');

insert into account (id, username, email, pwd, profile_image, permission_id) values (101, 'user', 'customer@customer.com', '$2a$12$VQSVztr/M8tcwzcy9jJgFebpqybKpF4FVwM3zilhOAI4yL1iW3rJa', 'profileImage',101);
insert into account (id, username, email, pwd, profile_image, permission_id) values (102, 'admin', 'admin@admin.com', '$2a$12$VQSVztr/M8tcwzcy9jJgFebpqybKpF4FVwM3zilhOAI4yL1iW3rJa', 'profileImage',102);

insert into address (id, city,complement,mobile_number,neighborhood,number,street, uf,zipcode, account_id) values (101, 'city', 'complement', '123456789123','neighborhood',123, 'street', 'MG', '12345678', 102);
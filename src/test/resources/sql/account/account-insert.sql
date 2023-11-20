insert into permission (id, role_name) values (101, 'ROLE_CUSTOMER');
insert into permission (id, role_name) values (102, 'ROLE_ADMIN');

insert into account (id, username, email, pwd, profile_image, permission_id) values (101, 'user', 'customer@customer.com', '$2a$12$VQSVztr/M8tcwzcy9jJgFebpqybKpF4FVwM3zilhOAI4yL1iW3rJa', 'profileImage',101);
insert into account (id, username, email, pwd, profile_image, permission_id) values (102, 'admin', 'admin@admin.com', '$2a$12$VQSVztr/M8tcwzcy9jJgFebpqybKpF4FVwM3zilhOAI4yL1iW3rJa', 'profileImage',102);

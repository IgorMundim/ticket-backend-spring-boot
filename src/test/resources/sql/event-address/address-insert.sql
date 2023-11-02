insert into permission (id, role_name) values (101, 'ROLE_CUSTOMER');
insert into permission (id, role_name) values (102, 'ROLE_ADMIN');

insert into account (id, username, email, pwd, profile_image, permission_id) values (101, 'user', 'customer@customer.com', '$2a$12$VQSVztr/M8tcwzcy9jJgFebpqybKpF4FVwM3zilhOAI4yL1iW3rJa', 'profileImage',101);
insert into account (id, username, email, pwd, profile_image, permission_id) values (102, 'admin', 'admin@admin.com', '$2a$12$VQSVztr/M8tcwzcy9jJgFebpqybKpF4FVwM3zilhOAI4yL1iW3rJa', 'profileImage',102);

insert into event (id, date_end, date_start, description, is_active, is_published, is_virtual, name, video_url) values (101, '2023-11-10 14:30:00', '2023-11-01 14:30:00', 'description', false, false, true, 'name', 'videoUrl');
insert into event (id, date_end, date_start, description, is_active, is_published, is_virtual, name, video_url) values (102, '2023-11-10 14:30:00', '2023-11-01 14:30:00', 'descriptionTwo', false, false, true, 'nameTwo', 'videoUrlTwo');

insert into event_address (id, city,complement,mobile_number,neighborhood,number,street, uf,zipcode, event_id) values (101, 'city', 'complement', '123456789123','neighborhood',123, 'street', 'MG', '12345678', 102);
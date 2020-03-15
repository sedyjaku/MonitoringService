insert into user(id, username, email, access_token)
values ('6a514e57-ee31-4643-a8c7-0172934cc77b', 'testuser123456', 'testuser123456@test.te', '39548dbf-8129-42eb-881f-645a6d2ed099');

insert into monitored_endpoint(id, name, url, monitored_interval, created_at, last_checked_at, owner_id)
values
('b416bed4-bdab-4231-aa49-e7a8ef3ae24c', 'testMonitoredEndpointName-e7a8ef3ae24c', 'test.url-e7a8ef3ae24c', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', '6a514e57-ee31-4643-a8c7-0172934cc77b'),
('c20df938-f29a-4120-83dc-8291b7693016', 'testMonitoredEndpointName-8291b7693016', 'test.url-8291b7693016', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', '6a514e57-ee31-4643-a8c7-0172934cc77b'),
('9c89b2a3-251b-42c3-9ad4-fc7ec13ea3c1', 'testMonitoredEndpointName-fc7ec13ea3c1', 'test.url-fc7ec13ea3c1', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', '6a514e57-ee31-4643-a8c7-0172934cc77b');

insert into monitoring_result(id, payload, status_code, checked_at, monitored_endpoint_id)
values
('550325f9-2698-4d35-9ac4-1bedb4d1a393', 'payload1', 401, '2020-03-15 14:01:39', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('c5fbbd5e-8445-4453-a0f5-312bb19a626c', 'paya626c', 401, '2020-03-15 14:01:40', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('9dea5fb7-b057-4665-98b3-1cdbde8c4434', 'payc4434', 401, '2020-03-15 14:01:45', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('0b3ec18d-2564-493b-93c6-38972dbf604d', 'payf604d', 401, '2020-03-15 14:01:01', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('0cee2431-baab-4d04-a0f9-559f4314ab98', 'pay4ab98', 401, '2020-03-15 14:01:03', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('2c70bb63-7a1b-4dec-b525-aaea69b412fd', 'pay412fd', 401, '2020-03-15 14:01:25', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('d6806c31-77f6-442a-82ca-58956f651c57', 'pay51c57', 401, '2020-03-15 14:01:07', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('aae51e75-052b-4bc7-898e-accf568bdf6a', 'paybdf6a', 401, '2020-03-15 14:01:09', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('0579ec60-7fd2-48cb-a301-0b2f334f8e68', 'payf8e68', 401, '2020-03-15 14:01:00', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('415d8731-4d82-4610-95b4-6b2b19c0f38c', 'pay0f38c', 401, '2020-03-15 14:01:11', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('564754fb-7092-459a-bf8d-5ef7684ce422', 'payce422', 401, '2020-03-15 14:01:25', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('a6ec948f-4f27-4629-9f3f-95a0f590cfdf', 'payload2', 202, '2020-03-15 14:01:37', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c'),
('97689693-3eaf-4fe9-84d1-8d4b4e21f134', 'payload3', 403, '2020-03-15 14:01:57', 'c20df938-f29a-4120-83dc-8291b7693016'),
('a1a8de76-3996-4401-b420-2bcc264ba177', 'payload4', 404, '2020-03-15 14:01:43', '9c89b2a3-251b-42c3-9ad4-fc7ec13ea3c1'),
('a0cc1fe8-63bf-4f5f-93fd-9bd829fe27fe', 'payload5', 405, '2020-03-15 14:01:40', 'c20df938-f29a-4120-83dc-8291b7693016'),
('c47a8115-cb61-4ed2-9849-dff9f5da4f93', 'payload6', 406, '2020-03-15 14:01:00', '9c89b2a3-251b-42c3-9ad4-fc7ec13ea3c1'),
('a4620735-8649-423d-b9b2-5fc0ef063cf5', 'payload7', 407, '2020-03-15 14:01:08', 'b416bed4-bdab-4231-aa49-e7a8ef3ae24c');
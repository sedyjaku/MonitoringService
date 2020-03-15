insert into user(id, username, email, access_token)
values ('6a514e57-ee31-4643-a8c7-0172934cc77b', 'testuser123456', 'testuser123456@test.te', '39548dbf-8129-42eb-881f-645a6d2ed099');

insert into monitored_endpoint(id, name, url, monitored_interval, created_at, last_checked_at, owner_id)
values ('b416bed4-bdab-4231-aa49-e7a8ef3ae24c', 'testMonitoredEndpointName', 'test.url', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', '6a514e57-ee31-4643-a8c7-0172934cc77b');
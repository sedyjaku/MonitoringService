insert into user(id, username, email, access_token)
values
('f51e18fe-4e52-4c7d-886c-9e9964b766d1', 'testuser-ee71d33b', 'testuser-ee71d33b@test.te', 'ee71d33b-35ac-4271-a1b6-ede7fedc77d6'),
('b63d6791-59e6-46eb-bc95-8b867724fe92', 'testuser-3bb1bb9e', 'testuser-3bb1bb9e@test.te', '3bb1bb9e-9cf2-4d23-bddf-86956b370bf2'),
('4c9687d0-34c8-4a32-866c-e96896892af5', 'testuser-983167d7', 'testuser-983167d7@test.te', '983167d7-f2c5-4e6b-993a-5dbfbbe06faf');

insert into monitored_endpoint(id, name, url, monitored_interval, created_at, last_checked_at, owner_id)
values
('b416bed4-bdab-4231-aa49-e7a8ef3ae24c', 'testMonitoredEndpointName', 'test.url', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', 'f51e18fe-4e52-4c7d-886c-9e9964b766d1'),
('9a032b05-fc6b-40ac-b4e8-481f2677f606', 'testMonitoredEndpointName', 'test.url', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', 'b63d6791-59e6-46eb-bc95-8b867724fe92'),
('1463051d-ab37-4b28-bbeb-e2dbae609a4a', 'testMonitoredEndpointName', 'test.url', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', '4c9687d0-34c8-4a32-866c-e96896892af5'),
('2dbfdb67-2f6b-4d57-8414-554f07f75586', 'testMonitoredEndpointName', 'test.url', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', 'f51e18fe-4e52-4c7d-886c-9e9964b766d1'),
('583da06a-a471-4316-9c67-f0f537963172', 'testMonitoredEndpointName', 'test.url', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', 'b63d6791-59e6-46eb-bc95-8b867724fe92'),
('4c9171ec-fe06-460e-998c-5526eaa89c5b', 'testMonitoredEndpointName', 'test.url', 5,'2020-03-15 14:01:39', '2020-03-15 16:01:43', '4c9687d0-34c8-4a32-866c-e96896892af5');
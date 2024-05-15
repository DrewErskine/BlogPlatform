-- Insert data into `users` using H2 compatible MERGE statement
MERGE INTO users (id, username, password, email, enabled) KEY (id) VALUES
(1, 'sara', '$2b$12$DY0wSg.HNqfYoV19/4oxYuOLCRJsjMRGbVGNh21RrmmxOncEoVxqG', 'OfficialPeepPeaPlog@gmail.com', true),
(2, 'drew', '$2b$12$DY0wSg.HNqfYoV19/4oxYuOLCRJsjMRGbVGNh21RrmmxOncEoVxqG', 'Dmerskine19@Gmail.com', true),
(3, 'testUser', '$2b$12$DY0wSg.HNqfYoV19/4oxYuOLCRJsjMRGbVGNh21RrmmxOncEoVxqG', 'test@example.com', true);

-- Insert data into `authorities`
MERGE INTO authorities (id, name) KEY (id) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- Insert data into `user_authority` assuming user_id and authority_id as keys
MERGE INTO user_authority (user_id, authority_id) KEY (user_id, authority_id) VALUES
((SELECT id FROM users WHERE username = 'drew'), (SELECT id FROM authorities WHERE name = 'ADMIN')),
((SELECT id FROM users WHERE username = 'sara'), (SELECT id FROM authorities WHERE name = 'USER'));

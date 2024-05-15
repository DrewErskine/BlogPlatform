-- Inserting data into users table
MERGE INTO users (id, username, password, email, enabled) KEY (id) 
VALUES 
(1, 'sara', '$2b$12$DY0wSg.HNqfYoV19/4oxYuOLCRJsjMRGbVGNh21RrmmxOncEoVxqG', 'OfficialPeepPeaPlog@gmail.com', true), 
(2, 'drew', '$2b$12$DY0wSg.HNqfYoV19/4oxYuOLCRJsjMRGbVGNh21RrmmxOncEoVxqG', 'Dmerskine19@Gmail.com', true), 
(3, 'testUser', '$2b$12$DY0wSg.HNqfYoV19/4oxYuOLCRJsjMRGbVGNh21RrmmxOncEoVxqG', 'test@example.com', true);

-- Inserting data into authorities table
MERGE INTO authorities (id, authority) KEY (id) 
VALUES 
(1, 'ADMIN'), 
(2, 'USER');

-- Inserting data into user_authority table
MERGE INTO user_authority (user_id, authority_id) KEY (user_id, authority_id) 
VALUES 
((SELECT id FROM users WHERE username = 'drew'), (SELECT id FROM authorities WHERE authority = 'ADMIN')), 
((SELECT id FROM users WHERE username = 'sara'), (SELECT id FROM authorities WHERE authority = 'USER'));

-- Inserting data into posts table
MERGE INTO posts (id, title, content, created_at, user_id) KEY (id) 
VALUES 
(1, 'Welcome to Our Blog', 'This is the first post on our blog! Stay tuned for more updates.', '2023-01-01T10:00:00', 1), 
(2, 'Latest News', 'Here is the latest news update. Check back regularly for more.', '2023-05-01T12:00:00', 2), 
(3, 'Introduction to Blogging', 'In this post, we will cover the basics of blogging.', '2023-05-05T14:00:00', 3);

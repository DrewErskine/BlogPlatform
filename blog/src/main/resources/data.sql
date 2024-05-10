
INSERT INTO users (id, username, password, email) VALUES
(33, 'drew', '$2b$12$DY0wSg.HNqfYoV19/4oxYuOLCRJsjMRGbVGNh21RrmmxOncEoVxqG', 'Dmerskine19@Gmail.com'),
(1, 'sara', '$2b$12$DY0wSg.HNqfYoV19/4oxYuOLCRJsjMRGbVGNh21RrmmxOncEoVxqG', 'OfficialPeepPeaPlog@gmail.com')
ON DUPLICATE KEY UPDATE username = VALUES(username), password = VALUES(password), email = VALUES(email);


INSERT INTO authorities (name) VALUES
('ADMIN'),
('USER')
ON DUPLICATE KEY UPDATE name = VALUES(name);


INSERT INTO post (title, content, created_at, author_id) VALUES
('First Post', 'This is the content of the first post.', NOW(), 33),
('Second Post', 'This is the content of the second post.', NOW(), 33)
ON DUPLICATE KEY UPDATE title = VALUES(title), content = VALUES(content);
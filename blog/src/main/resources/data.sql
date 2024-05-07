
INSERT INTO users (id, username, password, email) VALUES
(111, 'user1', 'password1', 'user1@example.com'),
(112, 'user2', 'password2', 'user2@example.com')
ON DUPLICATE KEY UPDATE username=username;


INSERT INTO roles (role_name) VALUES
('ADMIN'),
('USER')
ON DUPLICATE KEY UPDATE role_name=role_name;


INSERT INTO post (title, content, created_at, author_id) VALUES
('First Post', 'This is the content of the first post.', NOW(), 111),
('Second Post', 'This is the content of the second post.', NOW(), 112)
ON DUPLICATE KEY UPDATE title=title;

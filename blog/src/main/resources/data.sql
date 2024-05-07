-- Initial data for `users`
INSERT INTO users (username, password, email) VALUES
('user1', 'password1', 'user1@example.com'),
('user2', 'password2', 'user2@example.com')
ON DUPLICATE KEY UPDATE username=username;

-- Initial data for `roles`
INSERT INTO roles (role_name) VALUES
('ADMIN'),
('USER')
ON DUPLICATE KEY UPDATE role_name=role_name;

-- Initial data for `user_roles`
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1),
(2, 2)
ON DUPLICATE KEY UPDATE user_id=user_id;

-- Initial data for `post`
INSERT INTO post (title, content, author_id) VALUES
('First Post', 'This is the content of the first post.', 1),
('Second Post', 'This is the content of the second post.', 2)
ON DUPLICATE KEY UPDATE title=title;

-- Initial data for `comments`
INSERT INTO comments (post_id, user_id, comment) VALUES
(1, 1, 'This is a comment on the first post.'),
(1, 2, 'Another comment on the first post.'),
(2, 1, 'A comment on the second post.')
ON DUPLICATE KEY UPDATE comment=comment;

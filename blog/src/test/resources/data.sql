-- Initial data for `users`
MERGE INTO users (id, username, password, email) KEY (id) VALUES
(1, 'user1', 'password1', 'user1@example.com'),
(2, 'user2', 'password2', 'user2@example.com');

-- Initial data for `roles`
MERGE INTO roles (id, role_name) KEY (id) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- Initial data for `user_roles`
MERGE INTO user_roles (user_id, role_id) KEY (user_id, role_id) VALUES
(1, 1),
(2, 2);

-- Initial data for `post`
MERGE INTO post (id, title, content, author_id) KEY (id) VALUES
(1, 'First Post', 'This is the content of the first post.', 1),
(2, 'Second Post', 'This is the content of the second post.', 2);

-- Initial data for `comments`
MERGE INTO comments (id, post_id, user_id, comment) KEY (id) VALUES
(1, 1, 1, 'This is a comment on the first post.'),
(2, 1, 2, 'Another comment on the first post.'),
(3, 2, 1, 'A comment on the second post.');

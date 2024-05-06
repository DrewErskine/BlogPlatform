-- Insert data into table `comments`
INSERT INTO `comments` (`id`, `post_id`, `user_id`, `comment`, `created_at`) VALUES
(1, 1, 1, 'This is a comment on post 1', '2024-05-06 00:00:00'),
(2, 1, 2, 'Another comment on post 1', '2024-05-06 00:00:00'),
(3, 2, 1, 'Comment on post 2', '2024-05-06 00:00:00');

-- Insert data into table `post`
INSERT INTO `post` (`id`, `title`, `content`, `createdAt`, `author_id`) VALUES
(1, 'First Post', 'This is the content of the first post.', '2024-05-06 00:00:00', 1),
(2, 'Second Post', 'This is the content of the second post.', '2024-05-06 00:00:00', 2);

-- Insert data into table `roles`
INSERT INTO `roles` (`id`, `role_name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- Insert data into table `user_roles`
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2);

-- Insert data into table `users`
INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES
(1, 'user1', 'password1', 'user1@example.com'),
(2, 'admin1', 'adminpassword1', 'admin1@example.com');

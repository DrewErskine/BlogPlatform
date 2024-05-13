-- data.sql
MERGE INTO users KEY (id) VALUES (33, 'drew', '$2a$10$T5yH.Gk7Y6x6YzN.2ZDJ4OjU71gK5.T/wBYV8Jz2G3YOBShlh/ZSG', 'Dmerskine19@Gmail.com');
MERGE INTO users KEY (id) VALUES (1, 'sara', '$2a$10$T5yH.Gk7Y6x6YzN.2ZDJ4OjU71gK5.T/wBYV8Jz2G3YOBShlh/ZSG', 'OfficialPeepPeaPlog@gmail.com');
MERGE INTO authorities KEY (name) VALUES ('ADMIN');
MERGE INTO authorities KEY (name) VALUES ('USER');
MERGE INTO user_authority KEY (user_id, authority_name) VALUES (33, 'USER');
MERGE INTO user_authority KEY (user_id, authority_name) VALUES (1, 'ADMIN');

-- Posts
MERGE INTO post KEY (id) VALUES (1, 'First Post', 'This is the first post content.', 33);
MERGE INTO post KEY (id) VALUES (2, 'Second Post', 'This is the second post content.', 33);

-- Comments
MERGE INTO comments KEY (id) VALUES (1, 1, 33, 'First comment on first post.');
MERGE INTO comments KEY (id) VALUES (2, 2, 33, 'First comment on second post.');

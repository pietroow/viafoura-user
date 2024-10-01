CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    CONSTRAINT uk_email UNIQUE (email)
);

CREATE TABLE `user_friends`
(
    `user_id`   BIGINT NOT NULL,
    `friend_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `friend_id`),
    CONSTRAINT `fk_user_friends_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_friends_friend` FOREIGN KEY (`friend_id`) REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Ensure there are no duplicate rows for friendships in reverse order
ALTER TABLE `user_friends`
    ADD CONSTRAINT `ck_user_friendship_order` CHECK (`user_id` < `friend_id`);

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
create table users(
                      username VARCHAR(50) not null primary key,
                      password VARCHAR(500) not null,
                      enabled boolean not null
);
create table authorities (
                    `id` bigint(20) NOT NULL AUTO_INCREMENT,
                    username VARCHAR(50) not null,
                    authority VARCHAR(50) not null,
                    PRIMARY KEY (`id`),
                    constraint fk_authorities_users foreign key(username)
                        references users(username)
);
CREATE TABLE IF NOT EXISTS `conversation` (
                                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                                              `name` VARCHAR(255) NOT NULL,
                                              PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `message` (
                                         `id` BIGINT NOT NULL AUTO_INCREMENT,
                                         `content` TEXT NOT NULL,
                                         `role` TEXT NOT NULL,
                                         `conversation_id` BIGINT NOT NULL,
                                         PRIMARY KEY (`id`),
                                         FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`)
);

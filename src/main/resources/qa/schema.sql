DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id                  BINARY(16)      PRIMARY KEY,
    email               VARCHAR(255)    NOT NULL,
    username            VARCHAR(255)    NOT NULL,
    password            VARCHAR(255)    NOT NULL,
    account_enabled     BOOLEAN         NOT NULL,
    account_expired     BOOLEAN         NOT NULL,
    account_locked      BOOLEAN         NOT NULL,
    credentials_expired BOOLEAN         NOT NULL,
    created_at          TIMESTAMP
);

CREATE TABLE role
(
    id              BINARY(16)       PRIMARY KEY,
    user_id         BINARY(16)       NOT NULL,
    role            VARCHAR(255)     NOT NULL,
    enabled         BOOLEAN          NOT NULL,
    created_at      TIMESTAMP,
    CONSTRAINT fk_user_role FOREIGN KEY (user_id) REFERENCES user (id)
);
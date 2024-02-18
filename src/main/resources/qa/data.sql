INSERT INTO user (id, email, username, password, account_enabled, account_expired, account_locked, credentials_expired, created_at)
VALUES
    (UUID_TO_BIN(UUID()), 'user1@example.com', 'user1', '$2a$10$qD046qHeed2WxRL20VTFDeWqRxvsZgPum/Euu/Tv7wETJE2M44pqC', TRUE, FALSE, FALSE, FALSE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), 'user2@example.com', 'user2', '$2a$10$qD046qHeed2WxRL20VTFDeWqRxvsZgPum/Euu/Tv7wETJE2M44pqC', TRUE, FALSE, FALSE, FALSE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), 'user3@example.com', 'user3', '$2a$10$qD046qHeed2WxRL20VTFDeWqRxvsZgPum/Euu/Tv7wETJE2M44pqC', TRUE, FALSE, FALSE, FALSE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), 'user4@example.com', 'user4', '$2a$10$qD046qHeed2WxRL20VTFDeWqRxvsZgPum/Euu/Tv7wETJE2M44pqC', TRUE, FALSE, FALSE, FALSE, CURRENT_TIMESTAMP),

    (UUID_TO_BIN(UUID()), 'admin1@example.com', 'admin1', '$2a$10$qD046qHeed2WxRL20VTFDeWqRxvsZgPum/Euu/Tv7wETJE2M44pqC', TRUE, FALSE, FALSE, FALSE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), 'admin2@example.com', 'admin2', '$2a$10$qD046qHeed2WxRL20VTFDeWqRxvsZgPum/Euu/Tv7wETJE2M44pqC', TRUE, FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);


INSERT INTO role (id, user_id, role, enabled, created_at)
VALUES
    (UUID_TO_BIN(UUID()), (SELECT id FROM user WHERE username = 'user1'), 'ROLE_USER', TRUE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), (SELECT id FROM user WHERE username = 'user2'), 'ROLE_USER', TRUE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), (SELECT id FROM user WHERE username = 'user3'), 'ROLE_USER', TRUE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), (SELECT id FROM user WHERE username = 'user4'), 'ROLE_USER', TRUE, CURRENT_TIMESTAMP),

    (UUID_TO_BIN(UUID()), (SELECT id FROM user WHERE username = 'admin1'), 'ROLE_USER', TRUE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), (SELECT id FROM user WHERE username = 'admin1'), 'ROLE_ADMIN', TRUE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), (SELECT id FROM user WHERE username = 'admin2'), 'ROLE_USER', TRUE, CURRENT_TIMESTAMP),
    (UUID_TO_BIN(UUID()), (SELECT id FROM user WHERE username = 'admin2'), 'ROLE_ADMIN', TRUE, CURRENT_TIMESTAMP);
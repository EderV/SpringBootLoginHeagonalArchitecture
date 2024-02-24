INSERT INTO user (id, email, username, password, account_enabled, account_expired, account_locked, credentials_expired, created_at)
VALUES
    ('458ff945-1368-41d7-a9a6-3ca9bde9d73f', 'user1@example.com', 'user1', '$2a$10$qD046qHeed2WxRL20VTFDeWqRxvsZgPum/Euu/Tv7wETJE2M44pqC', TRUE, FALSE, FALSE, FALSE, CURRENT_TIMESTAMP),
    ('ab5facfe-aec1-4d64-8d88-e581f1369f8a', 'user_admin@mail.com', 'AdminUser', '$2a$10$qD046qHeed2WxRL20VTFDeWqRxvsZgPum/Euu/Tv7wETJE2M44pqC', TRUE, FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);


INSERT INTO role (id, user_id, role, enabled, created_at)
VALUES
    ('9096d031-e8b9-407c-86cf-eca4d3abc211', (SELECT id FROM user WHERE username = 'user1'), 'ROLE_USER', TRUE, CURRENT_TIMESTAMP),
    ('f5094368-c6b3-4cda-8ecd-a6528398a03e', (SELECT id FROM user WHERE username = 'AdminUser'), 'ROLE_USER', TRUE, CURRENT_TIMESTAMP),
    ('a8c8683a-c606-4834-93e0-fbb2bed6150a', (SELECT id FROM user WHERE username = 'AdminUser'), 'ROLE_ADMIN', TRUE, CURRENT_TIMESTAMP);
ALTER TABLE users ADD COLUMN failedLoginAttempts INT DEFAULT 0;
ALTER TABLE users ADD COLUMN blockingTimestamp BIGINT;

UPDATE users
SET failedLoginAttempts = 0;
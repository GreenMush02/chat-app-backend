
CREATE TABLE users (
  user_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  is_admin BOOLEAN NOT NULL,
  is_banned BOOLEAN NOT NULL
);
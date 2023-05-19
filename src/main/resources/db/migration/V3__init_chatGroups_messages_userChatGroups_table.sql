DROP TABLE IF EXISTS chat_groups;
DROP TABLE IF EXISTS user_chat_groups;
DROP TABLE IF EXISTS messages;


CREATE TABLE chat_groups (
  group_id VARCHAR(255) PRIMARY KEY,
  group_name VARCHAR(255) NOT NULL
);

CREATE TABLE user_chat_groups (
  user_id VARCHAR(255),
  group_id VARCHAR(255),
  PRIMARY KEY (user_id, group_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (group_id) REFERENCES chat_groups (group_id)
);

CREATE TABLE messages (
  message_id VARCHAR(255),
  chat_group_id VARCHAR(255),
  user_id VARCHAR(255),
  time TIMESTAMP,
  content VARCHAR(255),
  is_deleted BOOLEAN,
  FOREIGN KEY (chat_group_id) REFERENCES chat_groups (group_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);
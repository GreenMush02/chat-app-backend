INSERT INTO chat_groups (group_id, group_name)
VALUES
  ('8b8dd1e7-394d-4757-9e42-018e6f8ad7f4', 'General'),
  ('4b8d1193-ded0-4d6d-b4ff-21c31e6b5efc', 'NovoAkademia');

INSERT INTO messages (message_id, chat_group_id, user_id, time, content, is_deleted)
VALUES
  ('5dfaae04-5382-4f05-8b8a-23b62a4f091d', '8b8dd1e7-394d-4757-9e42-018e6f8ad7f4', 'f47ac10b-58cc-4372-a567-0e02b2c3d479', '2023-05-22T08:30:23', 'Siemka, jestem adminem!', false);


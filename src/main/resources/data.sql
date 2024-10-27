INSERT INTO article (id, name) VALUES
  ('6fede0af-5441-4552-887b-4e7c0b88bdd8', 'Chocolate'),
  ('e7cc3b79-e771-4b10-996a-194557e24b4e', 'Pasta'),
  ('8ca71549-c176-4bfb-bd53-fb5087e4734a', 'Apple'),
  ('b2468d7c-8838-439f-9d4e-1463fc531ff9', 'Banana');

INSERT INTO stock (id, city) VALUES
  ('ce7599c1-6bbb-489b-97ff-ff19b12418ce', 'Munich');

INSERT INTO stock_article_mapping (stock_id, articles_key, stock) VALUES
  ('ce7599c1-6bbb-489b-97ff-ff19b12418ce', '6fede0af-5441-4552-887b-4e7c0b88bdd8', 10),
  ('ce7599c1-6bbb-489b-97ff-ff19b12418ce', 'e7cc3b79-e771-4b10-996a-194557e24b4e', 100),
  ('ce7599c1-6bbb-489b-97ff-ff19b12418ce', '8ca71549-c176-4bfb-bd53-fb5087e4734a', 50),
  ('ce7599c1-6bbb-489b-97ff-ff19b12418ce', 'b2468d7c-8838-439f-9d4e-1463fc531ff9', 150);
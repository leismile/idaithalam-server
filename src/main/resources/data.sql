DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  userid VARCHAR(250) NOT NULL,
  apikey VARCHAR(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, userid, apikey) VALUES
  ('Oliver', 'Glas', 'user1','key1'),
  ('Fritz', 'Glas','user2', 'key2'),
  ('Whatever', 'Glas', 'user3','key3');
  
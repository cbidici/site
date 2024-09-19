DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username VARCHAR(255),
  password VARCHAR(255),
  name VARCHAR(255),
  email VARCHAR(255),
  created_at TEXT
);

DROP TABLE IF EXISTS post;
CREATE TABLE post (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  user_id INTEGER,
  title VARCHAR(255),
  content TEXT,
  created_at TEXT,
  published_at TEXT,
  updated_at TEXT
);



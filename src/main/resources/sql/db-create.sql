CREATE TABLE IF NOT EXISTS ingredients (
  id   INT          NOT NULL PRIMARY KEY,
  name VARCHAR(127) NOT NULL,
  unit VARCHAR(127) NOT NULL
);

CREATE TABLE IF NOT EXISTS recipes (
  id          INT          NOT NULL PRIMARY KEY,
  name        VARCHAR(127) NOT NULL,
  description TEXT
);

CREATE TABLE IF NOT EXISTS recipe_ingredients (
  recipe     INT,
  ingredient INT,
  amount     FLOAT,
  FOREIGN KEY (recipe) REFERENCES recipes,
  FOREIGN KEY (ingredient) REFERENCES ingredients
);
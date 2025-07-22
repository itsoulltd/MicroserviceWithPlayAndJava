--DROP table IF EXISTS books;

CREATE TABLE IF NOT EXISTS books (
  id INTEGER IDENTITY,
  isbn VARCHAR(40) NOT NULL,
  title VARCHAR(512) NOT NULL,
  subtitle VARCHAR(512) NULL,
  copyright_year VARCHAR(256) NULL,
  status VARCHAR(40) NULL,
  created_by VARCHAR(256) NULL,
  created_date TIMESTAMP NULL,
  modified_by VARCHAR(256) NULL,
  modified_date TIMESTAMP NULL,
  version INTEGER NULL,
  PRIMARY KEY(id)
);
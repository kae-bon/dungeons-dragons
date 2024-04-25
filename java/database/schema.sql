BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, character_classes, characters, subclasses, classes;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE characters (
	character_id SERIAL,
	user_id INTEGER NOT NULL,
	character_name VARCHAR(100) NOT NULL,
	character_race VARCHAR(50) NOT NULL,
	current_level INTEGER NOT NULL,
	alignment VARCHAR(50) NOT NULL,

	CONSTRAINT PK_character PRIMARY KEY (character_id),
	CONSTRAINT FK_userid FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT CK_level CHECK (current_level <= 20 AND current_level > 0)
);

CREATE TABLE classes (
	class_id SERIAL,
	class_name VARCHAR(100) NOT NULL UNIQUE,

	CONSTRAINT PK_class PRIMARY KEY (class_id)
);

CREATE TABLE subclasses (
	subclass_id SERIAL,
	subclass_name VARCHAR(100) NOT NULL,
	class_id INTEGER NOT NULL,

	CONSTRAINT PK_subclass PRIMARY KEY (subclass_id),
	CONSTRAINT FK_class_id FOREIGN KEY (class_id) REFERENCES classes (class_id)
);

CREATE TABLE character_classes (
	char_class_id SERIAL,
	character_id INTEGER NOT NULL,
	class_id INTEGER NOT NULL,
	subclass_id INTEGER NOT NULL,

	CONSTRAINT PK_char_class PRIMARY KEY (char_class_id),
	CONSTRAINT FK_class_id FOREIGN KEY (class_id) REFERENCES classes (class_id),
	CONSTRAINT FK_character_id FOREIGN KEY (character_id) REFERENCES characters (character_id),
	CONSTRAINT FK_subclass_id FOREIGN KEY (subclass_id) REFERENCES subclasses (subclass_id)
);

COMMIT TRANSACTION;
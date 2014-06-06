CREATE TABLE users (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL DEFAULT '',
    password varchar(32) NOT NULL DEFAULT '',
    firstname varchar(50) NOT NULL DEFAULT '',
    lastname varchar(50) NOT NULL DEFAULT '',
    version int(11) unsigned NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY username_uk (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

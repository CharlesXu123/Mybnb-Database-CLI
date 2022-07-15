DROP TABLE IF EXISTS myTable;

CREATE TABLE myTable (
                           id mediumint(8) unsigned NOT NULL auto_increment,
                           name varchar(255) default NULL,
                           phone varchar(100) default NULL,
                           email varchar(255) default NULL,
                           PRIMARY KEY (id)
) AUTO_INCREMENT=1;

INSERT INTO myTable (name,phone,email)
VALUES
    ('Burke Acosta','1-585-354-6250','molestie.arcu@aol.org'),
    ('Audra Hardin','1-858-666-8167','dapibus.gravida@aol.net'),
    ('Eric Perkins','(716) 537-1585','aliquam.nisl.nulla@yahoo.edu'),
    ('Inga Gallegos','(917) 967-3597','iaculis.lacus@google.couk'),
    ('Georgia Heath','(870) 669-0762','pharetra.sed@outlook.net');
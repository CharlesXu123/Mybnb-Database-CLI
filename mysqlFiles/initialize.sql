DROP TABLE IF EXISTS Renter;

CREATE TABLE Renter (
    uId char(36) primary key,
    name varchar(50) not null ,
    address varchar(225) not null ,
    date_of_birth DATE not null,
    occupation varchar(225) not null ,
    payment_info varchar(255) not null
);

CREATE TRIGGER renter_trigger
    BEFORE INSERT ON Renter
    FOR EACH ROW
BEGIN
    IF new.uId IS NULL THEN
        SET new.uId = uuid();
    END IF;
    IF (YEAR(CURDATE()) - YEAR(new.date_of_birth)) < 18 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'need be at least 18 years old!';
    END IF;
END;

DROP TABLE IF EXISTS Host;

CREATE TABLE Host (
                        uId char(36) primary key,
                        name varchar(50) not null ,
                        address varchar(225) not null ,
                        date_of_birth DATE not null,
                        occupation varchar(225) not null
);

CREATE TRIGGER host_trigger
    BEFORE INSERT ON Host
    FOR EACH ROW
BEGIN
    IF new.uId IS NULL THEN
        SET new.uId = uuid();
    END IF;
    IF (YEAR(CURDATE()) - YEAR(new.date_of_birth)) < 18 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'need be at least 18 years old!';
    END IF;
END;

DROP TABLE IF EXISTS Owned;

CREATE TABLE Owned (
                      uId char(36) not null ,
#                       lId char(36) not null ,
                      FOREIGN KEY (uId)
                        REFERENCES Renter(uId)
                        ON UPDATE CASCADE
);
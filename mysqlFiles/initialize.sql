DROP TABLE IF EXISTS Renter;

CREATE TABLE Renter (
    uId char(36) primary key,
    name varchar(50),
    address varchar(225),
    date_of_birth DATE,
    occupation varchar(225),
    payment_info varchar(255)
);

CREATE TRIGGER generate_Renter_uId
    BEFORE INSERT ON Renter
    FOR EACH ROW
BEGIN
    IF new.uId IS NULL THEN
        SET new.uId = uuid();
    END IF;
END;

DROP TABLE IF EXISTS Host;

CREATE TABLE Host (
                        uId char(36) primary key,
                        name varchar(50),
                        address varchar(225),
                        date_of_birth DATE,
                        occupation varchar(225)
);

CREATE TRIGGER generate_Host_uId
    BEFORE INSERT ON Host
    FOR EACH ROW
BEGIN
    IF new.uId IS NULL THEN
        SET new.uId = uuid();
    END IF;
END;
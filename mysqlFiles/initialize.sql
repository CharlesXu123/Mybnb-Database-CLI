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




################# LISTINGS, RENTED, AVAILABLE, AMENITIES #############################################
################# LISTINGS, RENTED, AVAILABLE, AMENITIES #############################################
################# LISTINGS, RENTED, AVAILABLE, AMENITIES #############################################
################# LISTINGS, RENTED, AVAILABLE, AMENITIES #############################################

DROP TABLE IF EXISTS listing;
CREATE TABLE listing (
                         uid char(36) NOT NULL primary key ,
                         type varchar(255) default NULL,
                         latitude double default NULL,
                         longitude  double default NULL,
                         `postal-code` varchar(10) default null,
                         city varchar(64) default null,
                         country varchar(32) default null
);
CREATE TRIGGER listing_trigger
    BEFORE INSERT ON listing
    FOR EACH ROW
BEGIN
    IF new.uid IS NULL THEN
        SET new.uid = uuid();
    end if;

end;
# insert into listing values(1,NULL,3,4,NULL,NULL, NULL);
#type: full house, apartment, room
DROP TABLE IF EXISTS available;
CREATE TABLE available (
                           uid char(36) NOT NULL primary key ,
                           available boolean default TRUE,
                           price double default 0

);

CREATE TRIGGER available_trigger
    BEFORE INSERT ON available
    FOR EACH ROW
BEGIN
    IF new.uid IS NULL THEN
        SET new.uid = uuid();
    end if;

end;

DROP TABLE IF EXISTS has;
CREATE TABLE has (
                     amenityId mediumint(8) unsigned NOT NULL auto_increment,
                     listingId varchar(255),
                     PRIMARY KEY (amenityId, listingId)
);

DROP TABLE IF EXISTS amenities;
CREATE TABLE amenities (
                           uid char(36) NOT NULL primary key,
                           amenities varchar(255)
);

DROP TABLE IF EXISTS rented;
CREATE TABLE rented (
                        uid char(36) NOT NULL primary key ,
                        comments varchar(8) default NULL,
                        `start-date` varchar(255) default NULL,
                        `end-date` varchar(100) default NULL,
                        rating INTEGER(5) default NULL,
                        status varchar(255) default NULL
);

# status: pending, ongoing, cancelled


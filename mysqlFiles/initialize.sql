DROP TABLE IF EXISTS owned,renter,host,listing,available,has,amenities,rented;

CREATE TABLE renter (
    uId char(36) primary key,
    name varchar(50) not null ,
    address varchar(225) not null ,
    date_of_birth DATE not null,
    occupation varchar(225) not null ,
    payment_info varchar(255) not null
);

CREATE TRIGGER renter_trigger
    BEFORE INSERT ON renter
    FOR EACH ROW
BEGIN
    IF new.uId IS NULL THEN
        SET new.uId = uuid();
    END IF;
    IF (YEAR(CURDATE()) - YEAR(new.date_of_birth)) < 18 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'need be at least 18 years old!';
    END IF;
END;

CREATE TABLE host (
                        uId char(36) primary key,
                        name varchar(50) not null ,
                        address varchar(225) not null ,
                        date_of_birth DATE not null,
                        occupation varchar(225) not null
);

CREATE TRIGGER host_trigger
    BEFORE INSERT ON host
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


CREATE TABLE amenities (
                           uid char(36) NOT NULL primary key,
                           amenity varchar(255)
);

CREATE TABLE has (
                     amenityId char(36) NOT NULL,
                     listingId char(36) NOT NULL,
                     PRIMARY KEY (amenityId, listingId),
                     foreign key (amenityId) references amenities(uid),
                     foreign key (listingId) references listing(uid)
);
CREATE TABLE rented (
                        listingId char(36) NOT NULL,
                        renterId char(36) NOT NULL,
                        comments varchar(8) default NULL,
                        `start-date` varchar(255) default NULL,
                        `end-date` varchar(100) default NULL,
                        rating INTEGER(5) default NULL,
                        status varchar(255) default NULL,
                        primary key (listingId, renterId),
                        foreign key (listingId) references listing(uid),
                        foreign key (renterId) references Renter(uid)
);

# status: pending, ongoing, cancelled

CREATE TABLE owned (
                       uId char(36) not null ,
                       lId char(36) not null ,
                       FOREIGN KEY (uId)
                           REFERENCES renter(uId)
                           ON UPDATE CASCADE ON DELETE CASCADE,
                       FOREIGN KEY (lId)
                           REFERENCES listing(uId)
                           ON UPDATE CASCADE ON DELETE CASCADE
);


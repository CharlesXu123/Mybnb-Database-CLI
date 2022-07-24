DROP TABLE IF EXISTS owned,renter,host,listing,available,has,amenity,rented;

CREATE TABLE renter (
    uId char(36) primary key,
    name varchar(50) not null ,
    address varchar(225) not null,
    date_of_birth DATE not null,
    occupation varchar(225) not null,
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

CREATE TRIGGER renter_delete_trigger
    BEFORE DELETE ON renter
    FOR EACH ROW
BEGIN
    UPDATE rented SET canceled = true WHERE rented.rId = OLD.uId AND rented.end_date > CURDATE();
end;

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

CREATE TRIGGER host_delete_trigger
    BEFORE DELETE ON host
    FOR EACH ROW
BEGIN
    DELETE FROM listing WHERE listing.lId IN (SELECT lId FROM owned WHERE owned.uId = OLD.uId);
    UPDATE rented SET canceled = true WHERE rented.hId = OLD.uId AND rented.end_date > CURDATE();
end;

################# LISTINGS, RENTED, AVAILABLE, AMENITY #############################################
################# LISTINGS, RENTED, AVAILABLE, AMENITY #############################################
################# LISTINGS, RENTED, AVAILABLE, AMENITY #############################################

CREATE TABLE listing (
                         lId char(36) NOT NULL primary key ,
                         type varchar(255) default NULL check ( type IN ('full house','apartment','room') ),
                         latitude double default NULL,
                         longitude  double default NULL,
                         postal_code varchar(10) default null,
                         city varchar(64) default null,
                         country varchar(32) default null
);
CREATE TRIGGER listing_trigger
    BEFORE INSERT ON listing
    FOR EACH ROW
BEGIN
    IF new.lId IS NULL THEN
        SET new.lId = uuid();
    end if;

end;
# insert into listing values(1,NULL,3,4,NULL,NULL, NULL);
# type: full house, apartment, room
CREATE TABLE available (
                           lId char(36) NOT NULL,
                           query_date date NOT NULL,
                           available boolean default FALSE,
                           price double not null default 0 check ( price >= 0 ),
                           primary key(lId, query_date),
                           FOREIGN KEY (lId)
                            REFERENCES listing(lId)
                            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE amenity (
                           aId char(36) NOT NULL primary key,
                           amenity varchar(255)
);

CREATE TABLE has (
                     lId char(36) NOT NULL,
                     aId char(36) NOT NULL,
                     PRIMARY KEY (lId,aId),
                     foreign key (aId) references amenity(aId) on update cascade on delete cascade ,
                     foreign key (lId) references listing(lId) on update cascade on delete cascade
);

################# owned, rented #############################################
################# owned, rented #############################################
################# owned, rented #############################################

CREATE TABLE owned (
                       uId char(36) not null ,
                       lId char(36) not null ,
                       PRIMARY KEY (uId, lId),
                       FOREIGN KEY (uId)
                           REFERENCES host(uId)
                           ON UPDATE CASCADE ON DELETE CASCADE ,
                       FOREIGN KEY (lId)
                           REFERENCES listing(lId)
                           ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE rented (
    rentedId char(36),
    rId char(36),
    lId char(36),
    hId char(36),
    start_date DATE not null,
    end_date DATE not null,
    canceled BOOLEAN default FALSE,
    host_rating INTEGER default null check (host_rating >= 0 AND host_rating <= 5),
    renter_rating INTEGER default null check (renter_rating >= 0 AND renter_rating <= 5),
    host_comments varchar(255) default null,
    renter_comments varchar(255) default null,
    primary key (rentedId),
    FOREIGN KEY (rId)
        REFERENCES renter(uId)
        ON UPDATE CASCADE ON DELETE SET NULL ,
    FOREIGN KEY (hId)
        REFERENCES host(uid)
        ON UPDATE CASCADE ON DELETE SET NULL ,
    FOREIGN KEY (lId)
        REFERENCES listing(lId)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TRIGGER rented_insert_trigger
    BEFORE INSERT ON rented
    FOR EACH ROW
BEGIN
    IF NEW.rentedId IS NULL THEN
        SET NEW.rentedId = uuid();
    end if;
END;

# CREATE TRIGGER rented_update_trigger
#     BEFORE UPDATE ON rented
#     FOR EACH ROW
# BEGIN
#     IF NEW.lId IS NUlL OR NEW.hId IS NULL OR NEW.rId IS NULL THEN
#     SET NEW.canceled = true;
# end if;
#
# end;

# CREATE TRIGGER rented_cleanup_trigger
#     AFTER UPDATE ON rented
#     FOR EACH ROW
# BEGIN
#     IF OLD.lId IS NULL AND OLD.hId IS NULL AND OLD.rId IS NULL THEN
#         DELETE FROM rented WHERE rented.rentedId = OLD.rentedId;
#     end if;
# end;
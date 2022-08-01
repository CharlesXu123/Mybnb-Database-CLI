INSERT INTO Renter (name, address, date_of_birth, occupation)
VALUES
    ('Genevieve Mcintyre','P.O. Box 676, 8686 Vitae, Road','2020-05-','Vestibulum Incorporated');

INSERT INTO Owned(uId)
VALUES ('78053aa0-061c-11ed-b1e1-692fb2ef982d');

INSERT INTO has
VALUES ('kjsd','393');

insert into rented
values ('j','9','neque. Nullam','2023-09','2022-06-11',1,'id,');

SELECT `COLUMN_NAME`
FROM `INFORMATION_SCHEMA`.`COLUMNS`
WHERE `TABLE_SCHEMA`='c43Project'
  AND `TABLE_NAME`='renter';

INSERT host
values (1,'Raven Baker','552-2512 Ut, Rd.','2000-02-19','Vitae Dolor Institute');

INSERT owned
values ('1','a');

DELETE FROM host
WHERE host.uId = '1';

DELETE FROM renter
WHERE renter.uId = '1';

SELECT * FROM renter WHERE renter.uId = '1';

DELETE FROM listing WHERE listing.lId IN (SELECT lId FROM owned WHERE owned.uId = '1');

CREATE TRIGGER update_rented_trigger
    BEFORE UPDATE ON rented
    FOR EACH ROW
    BEGIN
        IF NEW.end_date < CURDATE() THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'you can not cancel complete booking!';
        end if;
        UPDATE available SET available=true WHERE available.lId = NEW.lId AND available.query_date >= NEW.start_date AND available.query_date<= NEW.end_date;
    end;

UPDATE rented SET canceled=false WHERE rentedId='3_r';

DROP TRIGGER update_rented_trigger;

UPDATE rented SET canceled=true WHERE rentedId='2_r';

CREATE TRIGGER insert_listing_trigger
    AFTER INSERT ON listing
    FOR EACH ROW
BEGIN
    INSERT INTO owned VALUES (NEW.lId, '2');
end;

INSERT INTO owned VALUES ('10i', 2);

INSERT INTO listing(type,latitude,longitude,postal_code,city,country)
VALUES ('room','123','123','123','toronto','canada');

DROP TRIGGER insert_listing_trigger;

select listing.* from listing,owned where 1=1;

INSERT INTO has VALUES ('2i',(SELECT aId FROM amenities WHERE amenity='a'));

DELETE FROM has WHERE lId='2i';

UPDATE available SET price='1.1ab' WHERE true;

SELECT price*2 from available;

CREATE TRIGGER delete_listing_trigger
    BEFORE DELETE ON listing
    FOR EACH ROW
    BEGIN
        UPDATE rented SET canceled = true WHERE rented.lId = OLD.lId;
    end;

DELETE FROM listing WHERE lId='10i';

DROP TRIGGER delete_listing_trigger;

CREATE TRIGGER create_booking_trigger
    BEFORE INSERT ON rented
    FOR EACH ROW
BEGIN
    IF NEW.start_date > NEW.end_date THEN #OR NEW.start_date < CURDATE() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'rent date invalid';
    END IF;
    IF EXISTS (SELECT * FROM available WHERE available.lId='1i' AND available.query_date<=NEW.start_date AND available.query_date>=NEW.end_date AND available=false) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'listing unavailable during this period';
    end if;
    UPDATE available SET available=false WHERE available.lId='1i' AND available.query_date<=NEW.start_date AND available.query_date>=NEW.end_date;
END;

UPDATE available set available=true where lId='1i';

INSERT INTO rented(rId, lId, hId, start_date, end_date) values ('1','1i',(SELECT uId FROM owned WHERE owned.lId='1i'),'2022-05-24','2022-05-24');

DROP TRIGGER create_booking_trigger;

SELECT * FROM available WHERE available.lId='1i' AND available.query_date<='2022-05-24' AND available.query_date>='2022-05-24' AND available=false;

DROP TRIGGER IF EXISTS update_available_trigger;
CREATE TRIGGER update_available_trigger
    BEFORE UPDATE ON available
    FOR EACH ROW
    BEGIN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'listing was booked during this period!';
        IF EXISTS(SELECT * FROM rented WHERE rented.lId=NEW.lId AND ((start_date between '' AND '') OR (end_date between '' AND ''))) THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'listing was booked during this period!';
        end if;
    end;

UPDATE available SET price='200' WHERE lId = '2i' AND query_date >= '2021-02-02' AND query_date <= '2021-02-02';

SELECT * FROM rented WHERE rented.lId='2i' AND ((start_date between '2021-02-02' AND '2021-02-02') OR (end_date between '2021-02-02' AND '2021-02-02')) AND canceled=false;

UPDATE rented SET canceled=false where lId='5i';

INSERT INTO available(lid, query_date)
SELECT lId, query_date
from calendar, (select lId from listing where lId='1i') as tmp;

SELECT lId, query_date
from calendar, (select lId from listing where lId='1i') as tmp;

SELECT lId, query_date
from calendar, (select lId from listing where true) as tmp;

SELECT lId, query_date
from calendar, (select lId from listing) as tmp;

DROP TRIGGER IF EXISTS create_booking_trigger;
CREATE TRIGGER create_booking_trigger
    BEFORE INSERT ON rented
    FOR EACH ROW
BEGIN
    IF NEW.start_date > NEW.end_date OR NEW.start_date < CURDATE() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'rent date invalid';
    END IF;
    IF EXISTS (SELECT * FROM available WHERE available.lId=NEW.lId AND available.query_date>=NEW.start_date AND available.query_date<=NEW.end_date AND available=false) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'his period';
    end if;
    UPDATE available SET available=false WHERE available.lId=NEW.lId AND available.query_date>=NEW.start_date AND available.query_date<=NEW.end_date;
END;

INSERT INTO rented(rId, lId, hId, start_date, end_date) values ('2','2i',(SELECT uId FROM owned WHERE owned.lId='2i'),'2022-09-03','2022-09-04');

DROP TRIGGER create_booking_trigger;

SELECT EXISTS(SELECT * FROM available WHERE available.lId='2i' AND available.query_date>='2022-09-01' AND available.query_date<='2022-09-02'AND available=false);

CREATE TRIGGER renter_comment_trigger
    BEFORE UPDATE ON rented
    FOR EACH ROW
BEGIN
    IF OLD.canceled = true THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'can not comment or rate on canceled bookings';
    end if;
end;

SELECT COUNT(*), city, postal_code
FROM rented r JOIN listing l on r.lId = l.lId
WHERE r.start_date >= '2001-01-01' AND r.end_date <= '2022-10-10' AND l.city = 'Llanquihue' AND l.postal_code = '735071'
GROUP BY city, postal_code;

SELECT l.lId, renter_comments
FROM rented r LEFT JOIN listing l on r.lId = l.lId
WHERE renter_comments IS NOT NULL
ORDER BY lId;
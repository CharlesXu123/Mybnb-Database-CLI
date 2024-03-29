# INSERT INTO Renter (name, address, date_of_birth, occupation)
# VALUES
#     ('Genevieve Mcintyre','P.O. Box 676, 8686 Vitae, Road','2020-05-','Vestibulum Incorporated');
#
# INSERT INTO Owned(uId)
# VALUES ('78053aa0-061c-11ed-b1e1-692fb2ef982d');
#
# INSERT INTO has
# VALUES ('kjsd','393');
#
# insert into rented
# values ('j','9','neque. Nullam','2023-09','2022-06-11',1,'id,');
#
# SELECT `COLUMN_NAME`
# FROM `INFORMATION_SCHEMA`.`COLUMNS`
# WHERE `TABLE_SCHEMA`='c43Project'
#   AND `TABLE_NAME`='renter';
#
# INSERT host
# values (1,'Raven Baker','552-2512 Ut, Rd.','2000-02-19','Vitae Dolor Institute');
#
# INSERT owned
# values ('1','a');
#
# DELETE FROM host
# WHERE host.uId = '1';
#
# DELETE FROM renter
# WHERE renter.uId = '1';
#
# SELECT * FROM renter WHERE renter.uId = '1';
#
# DELETE FROM listing WHERE listing.lId IN (SELECT lId FROM owned WHERE owned.uId = '1');
#
# CREATE TRIGGER update_rented_trigger
#     BEFORE UPDATE ON rented
#     FOR EACH ROW
#     BEGIN
#         IF NEW.end_date < CURDATE() THEN
#             SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'you can not cancel complete booking!';
#         end if;
#         UPDATE available SET available=true WHERE available.lId = NEW.lId AND available.query_date >= NEW.start_date AND available.query_date<= NEW.end_date;
#     end;
#
# UPDATE rented SET canceled=false WHERE rentedId='3_r';
#
# DROP TRIGGER update_rented_trigger;
#
# UPDATE rented SET canceled=true WHERE rentedId='2_r';
#
# CREATE TRIGGER insert_listing_trigger
#     AFTER INSERT ON listing
#     FOR EACH ROW
# BEGIN
#     INSERT INTO owned VALUES (NEW.lId, '2');
# end;
#
# INSERT INTO owned VALUES ('10i', 2);
#
# INSERT INTO listing(type,latitude,longitude,postal_code,city,country)
# VALUES ('room','123','123','123','toronto','canada');
#
# DROP TRIGGER insert_listing_trigger;
#
# select listing.* from listing,owned where 1=1;
#
# INSERT INTO has VALUES ('2i',(SELECT aId FROM amenities WHERE amenity='a'));
#
# DELETE FROM has WHERE lId='2i';
#
# UPDATE available SET price='1.1ab' WHERE true;
#
# SELECT price*2 from available;
#
# CREATE TRIGGER delete_listing_trigger
#     BEFORE DELETE ON listing
#     FOR EACH ROW
#     BEGIN
#         UPDATE rented SET canceled = true WHERE rented.lId = OLD.lId;
#     end;
#
# DELETE FROM listing WHERE lId='10i';
#
# DROP TRIGGER delete_listing_trigger;
#
# CREATE TRIGGER create_booking_trigger
#     BEFORE INSERT ON rented
#     FOR EACH ROW
# BEGIN
#     IF NEW.start_date > NEW.end_date THEN #OR NEW.start_date < CURDATE() THEN
#         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'rent date invalid';
#     END IF;
#     IF EXISTS (SELECT * FROM available WHERE available.lId='1i' AND available.query_date<=NEW.start_date AND available.query_date>=NEW.end_date AND available=false) THEN
#         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'listing unavailable during this period';
#     end if;
#     UPDATE available SET available=false WHERE available.lId='1i' AND available.query_date<=NEW.start_date AND available.query_date>=NEW.end_date;
# END;
#
# UPDATE available set available=true where lId='1i';
#
# INSERT INTO rented(rId, lId, hId, start_date, end_date) values ('1','1i',(SELECT uId FROM owned WHERE owned.lId='1i'),'2022-05-24','2022-05-24');
#
# DROP TRIGGER create_booking_trigger;
#
# SELECT * FROM available WHERE available.lId='1i' AND available.query_date<='2022-05-24' AND available.query_date>='2022-05-24' AND available=false;
#
# DROP TRIGGER IF EXISTS update_available_trigger;
# CREATE TRIGGER update_available_trigger
#     BEFORE UPDATE ON available
#     FOR EACH ROW
#     BEGIN
#         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'listing was booked during this period!';
#         IF EXISTS(SELECT * FROM rented WHERE rented.lId=NEW.lId AND ((start_date between '' AND '') OR (end_date between '' AND ''))) THEN
#             SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'listing was booked during this period!';
#         end if;
#     end;
#
# UPDATE available SET price='200' WHERE lId = '2i' AND query_date >= '2021-02-02' AND query_date <= '2021-02-02';
#
# SELECT * FROM rented WHERE rented.lId='2i' AND ((start_date between '2021-02-02' AND '2021-02-02') OR (end_date between '2021-02-02' AND '2021-02-02')) AND canceled=false;
#
# UPDATE rented SET canceled=false where lId='5i';
#
# INSERT INTO available(lid, query_date)
# SELECT lId, query_date
# from calendar, (select lId from listing where lId='1i') as tmp;
#
# SELECT lId, query_date
# from calendar, (select lId from listing where lId='1i') as tmp;
#
# SELECT lId, query_date
# from calendar, (select lId from listing where true) as tmp;
#
# SELECT lId, query_date
# from calendar, (select lId from listing) as tmp;
#
# DROP TRIGGER IF EXISTS create_booking_trigger;
# CREATE TRIGGER create_booking_trigger
#     BEFORE INSERT ON rented
#     FOR EACH ROW
# BEGIN
#     IF NEW.start_date > NEW.end_date OR NEW.start_date < CURDATE() THEN
#         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'rent date invalid';
#     END IF;
#     IF EXISTS (SELECT * FROM available WHERE available.lId=NEW.lId AND available.query_date>=NEW.start_date AND available.query_date<=NEW.end_date AND available=false) THEN
#         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'his period';
#     end if;
#     UPDATE available SET available=false WHERE available.lId=NEW.lId AND available.query_date>=NEW.start_date AND available.query_date<=NEW.end_date;
# END;
#
# INSERT INTO rented(rId, lId, hId, start_date, end_date) values ('2','2i',(SELECT uId FROM owned WHERE owned.lId='2i'),'2022-09-03','2022-09-04');
#
# DROP TRIGGER create_booking_trigger;
#
# SELECT EXISTS(SELECT * FROM available WHERE available.lId='2i' AND available.query_date>='2022-09-01' AND available.query_date<='2022-09-02'AND available=false);
#
# CREATE TRIGGER renter_comment_trigger
#     BEFORE UPDATE ON rented
#     FOR EACH ROW
# BEGIN
#     IF OLD.canceled = true THEN
#         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'can not comment or rate on canceled bookings';
#     end if;
# end;
#
# SELECT COUNT(*), city, postal_code
# FROM rented r JOIN listing l on r.lId = l.lId
# WHERE r.start_date >= '2001-01-01' AND r.end_date <= '2022-10-10' AND l.city = 'Llanquihue' AND l.postal_code = '735071'
# GROUP BY city, postal_code;
#
# SELECT l.lId, renter_comments
# FROM rented r LEFT JOIN listing l on r.lId = l.lId
# WHERE renter_comments IS NOT NULL
# ORDER BY lId;
#
# SELECT country, city, h.uId, name, count(*), (SELECT COUNT(*)
#                                               FROM listing
#                                               WHERE listing.country = l.country AND listing.city = l.city) totol
# FROM listing l JOIN owned o on l.lId = o.lId JOIN host h on h.uId = o.uId
# GROUP BY country, city, uId, name
# HAVING COUNT(*) > (SELECT COUNT(*)
#                    FROM listing
#                    WHERE listing.country = l.country AND listing.city = l.city)/10;

select count(*) count, name
from renter r join rented r2 on r.uId = r2.rId
group by rId
order by count(*) desc

select city, name, count(name)
from renter r join rented r2 on r.uId = r2.rId join listing l on r2.lId = l.lId
group by name,city;


select city, name, count(name) c
from renter r join rented r2 on r.uId = r2.rId join listing l on r2.lId = l.lId
where start_date >= 2022-01-01 and end_date <= 2022-12-31
group by city, name
having c >= 2
order by city, c desc;

select city, name, count(name) c
from renter r join rented r2 on r.uId = r2.rId join listing l on r2.lId = l.lId
where r2.start_date >= '2022-01-01' and r2.end_date <= '2022-12-31'
group by city, name
having c >= 2
order by city, c desc
SELECT @city := listing.city, @country := listing.country, @zip := listing.postal_code
FROM listing
WHERE lId='10i';

SELECT amenity, COUNT(*) / (SELECT COUNT(*)
                                 FROM listing
                                 WHERE city=@city AND country=@country AND SUBSTR(postal_code, 1, 3)=SUBSTR(@zip, 1, 3)) * 100
FROM amenity a JOIN has h on a.aId = h.aId JOIN listing l on l.lId = h.lId
WHERE city=@city AND country=@country AND SUBSTR(postal_code, 1, 3)=SUBSTR(@zip, 1, 3) AND NOT EXISTS(SELECT has.aId
                                                                                                      FROM has
                                                                                                      WHERE has.aId = a.aId AND has.lId = '1i')
GROUP BY h.aId
HAVING COUNT(*) > (SELECT COUNT(*)
                   FROM listing
                   WHERE city=@city AND country=@country AND SUBSTR(postal_code, 1, 3)=SUBSTR(@zip, 1, 3))/2;

SELECT COUNT(*) count, name
FROM renter r JOIN rented r2 on r.uId = r2.rId
GROUP BY rId
ORDER BY COUNT(*) desc;

CREATE table temp(a char(36)
                 check (a > 10 ));
INSERT INTO temp values (null);
SELECT a, null+10
FROM temp;
DROP table temp;

SELECT lst.lId, type, address, latitude,longitude, postal_code, city, country, avg(a.price)
FROM listing lst JOIN available a on lst.lId = a.lId
WHERE ((acos((sin(latitude * (PI() / 180))) * sin((66.8) * (PI() / 180)) + cos(latitude * (PI() / 180)) * cos((66.8) * (PI() / 180)) * cos((longitude * (PI() / 180) - (-36.7) * (PI() / 180))))) * 6371) <= 20
  and lst.lId not in (Select distinct lId
                      from available
                      where (available.query_date >= ('2022-02-02')
                                && available.query_date <= ('2022-07-05')
                                && available.available = false)
                                ||
                            (available.query_date >= ('2022-02-02')
                                && available.query_date <= ('2022-07-05')
                                && available.available = true
                                && (available.price < 0 || available.price > 900))
                            )
  and a.query_date >= ('2022-02-02') && a.query_date <= ('2022-07-05')
  and lst.lId in ((Select lId
                   from has
                   where has.lId = lst.lId && (has.aId =10) group by lId having count(lId) = 1))
GROUP BY a.lId
ORDER BY avg(a.price);


SELECT *
FROM listing JOIN has h on listing.lId = h.lId
WHERE 2=(SELECT COUNT(distinct has.aId)
        FROM has
        WHERE has.lId = listing.lId AND has.aId in ('10','12'));

UPDATE listing
SET country='Netherlands'
where lId='16i'


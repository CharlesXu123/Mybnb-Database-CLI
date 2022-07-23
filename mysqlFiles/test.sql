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

SELECT * FROM renter WHERE renter.uId = '1'

DELETE FROM listing WHERE listing.lId IN (SELECT lId FROM owned WHERE owned.uId = '1');

SELECT * from rented WHERE hId='2';

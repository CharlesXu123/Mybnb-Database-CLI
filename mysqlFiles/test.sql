INSERT INTO Renter (name, address, date_of_birth, occupation)
VALUES
    ('Genevieve Mcintyre','P.O. Box 676, 8686 Vitae, Road','2020-05-29','Vestibulum Incorporated');

INSERT INTO Owned(uId)
VALUES ('78053aa0-061c-11ed-b1e1-692fb2ef982d');

INSERT INTO has
VALUES ('kjsd','393');

SELECT date_of_birth
FROM host
GROUP BY date_of_birth
ORDER BY date_of_birth

insert into rented
values ('j','9','neque. Nullam','2023-09-28','2022-06-11',1,'id,');

SELECT `COLUMN_NAME`
FROM `INFORMATION_SCHEMA`.`COLUMNS`
WHERE `TABLE_SCHEMA`='c43Project'
  AND `TABLE_NAME`='renter';
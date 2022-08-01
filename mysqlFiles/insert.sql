INSERT INTO renter (uid, name,address,date_of_birth,occupation,payment_info)
VALUES
    ('1','Noble Payne','P.O. Box 962, 6664 Massa. Ave','1999-09-06','Aliquet Sem Institute','417500 492481 5732'),
    ('2','Quail Berg','Ap #754-6127 Erat, Ave','1999-06-20','Tincidunt Congue LLP','630 48734 41764 944'),
    ('3','Minerva Terrell','3927 Adipiscing St.','2000-02-16','Ut Quam Vel LLC','6334 1254 5373 4669'),
    ('4','Kaye Fowler','906-4772 Gravida Av.','1999-09-21','Id Blandit Consulting','675 98623 94577 759'),
    ('5','Patrick Ware','Ap #615-8048 Placerat Rd.','1999-12-26','Velit Cras Institute','525 72878 35683 676'),
    ('6','Zephr Church','Ap #761-1644 Ipsum Ave','1999-11-04','Mi Lacinia Industries','6304558847788882'),
    ('7','Wynne Mcintosh','Ap #216-2820 Sapien. St.','2000-03-05','Eget Foundation','4916228847584844'),
    ('8','Asher Harvey','Ap #381-9299 Justo Street','2000-02-19','Interdum Enim Incorporated','658839 2212381124'),
    ('9','Harlan Barton','P.O. Box 538, 5814 Ut Rd.','2000-10-22','Arcu Nunc Institute','6486247576722367'),
    ('10','Preston donnell','6728 Aliquam Avenue','1999-06-07','Vulputate Lacus Institute','670654 6557434452');

INSERT INTO host (uId,name,address,date_of_birth,occupation)
VALUES
    (1,'Raven Baker','552-2512 Ut, Rd.','2000-02-19','Vitae Dolor Institute'),
    (2,'Halee Wiley','5516 Mauris Street','1999-09-24','Eget PC'),
    (3,'Aimee Pittman','P.O. Box 906, 3685 Eros Street','1999-05-25','Neque In LLC'),
    (4,'Ryder Greene','410-9509 Urna St.','2000-09-15','Aliquam Enim LLP'),
    (5,'Quinn Miranda','985-6161 Nunc St.','2000-03-08','Natoque Corporation'),
    (6,'Eaton Watts','Ap #914-3802 Eu Road','2000-09-06','Lobortis PC'),
    (7,'Sara Lott','3366 Cursus Av.','2000-05-26','Nec Industries'),
    (8,'John Lane','487-5599 Ornare Avenue','2000-03-23','Ullamcorper Corporation'),
    (9,'Noble Blake','Ap #374-5673 Integer St.','2000-04-30','Nisl Corp.'),
    (10,'Amal Austin','P.O. Box 219, 1875 Tincidunt. Street','2000-07-30','Ut Cursus Limited');

INSERT INTO listing (lId,type,latitude,longitude,postal_code,city,country)
VALUES
    ('1i','apartment','-19.2761542656','-136.6154386432','735071','Llanquihue','Russian Federation'),
    ('2i','full house','5.1550667776','-85.7640810496','965519','Larvik','Peru'),
    ('3i','room','-41.0428909568','138.0606258176','65150-023','San Antonio','Russian Federation'),
    ('4i','apartment','-58.4752264192','148.8919666688','217978','Ravenstein','Philippines'),
    ('5i','room','71.386918912','151.5778917376','VN4I 6TP','Sokoto','Chile'),
    ('6i','apartment','-39.9093055488','-149.3089588224','44-25','Cochamó','Pakistan'),
    ('7i','full house','-10.3630294016','5.8111873024','47720','Pinetown','Brazil'),
    ('8i','full house','47.8830946304','10.1684512768','R6N 1NL','Hafizabad','Spain'),
    ('9i','room','-26.0834111488','111.6775187456','5548-2535','Fredericton','Russian Federation'),
    ('10i','room','66.8276508672','-36.7207641088','685635','Kollam','Netherlands');

INSERT INTO amenity
values ('1', 'Toilet paper'),
       ('2', 'Soap for hands and body'),
       ('3', 'One towel per guest'),
       ('4','Linens for each bed'),
       ('5', 'One pillow per guest'),
       ('6', 'Cleaning supplies'),
       ('7','A pool'),
       ('8', 'Wifi'),
       ('9', 'A kitchen'),
       ('10','Free parking'),
       ('11', 'A jacuzzi'),
       ('12', 'A washer or dryer'),
       ('13','Air conditioning or heating'),
       ('14', 'Self check-in'),
       ('15', 'Laptop-friendly workspace'),
       ('16','Pets allowed'),
       ('17','Carbon monoxide alarm'),
       ('18', 'Smoke alarm'),
       ('19', 'Fire extinguisher'),
       ('20','First-aid kit'),
       ('21','Emergency plan and local numbers'),
       ('22', 'Step-free entryway'),
       ('23', 'Wide entrances (at least 32” or 82 cm)'),
       ('24','Wide hallways (at least 36” or 92 cm)'),
       ('25','Accessible bathroom'),
       ('26', 'Extra toilet paper, linens, and towels'),
       ('27', 'Basic toiletries like shampoo and conditioner'),
       ('28','Dish soap and cleaning supplies'),
       ('29','Dining basics like a coffee maker, cooking utensils, dishes, and utensils'),
       ('30', 'Wine glasses'),
       ('31', 'Basic cooking supplies like salt, pepper, and oil'),
       ('32','Coffee, tea'),
       ('33','Light breakfast or snacks'),
       ('34', 'Hangers'),
       ('35', 'Adapters and chargers'),
       ('36','Fast and reliable wifi'),
       ('37','Laptop-friendly workspace'),
       ('38', 'Good lighting'),
       ('39', 'Fully equipped kitchens'),
       ('40','Office supplies'),
       ('41','A crib and high chair'),
       ('42', 'A bathtub'),
       ('43', 'Air conditioning'),
       ('44','A washer and/or dryer'),
       ('45', 'Extra cleaning supplies'),
       ('46','Furniture covers'),
       ('47', 'Bowls for pet food and water'),
       ('48','Towels to wipe off paws at the door');


INSERT INTO has (lId,aId)
VALUES
    ('1i',1),
    ('2i',2),
    ('3i',3),
    ('4i',4),
    ('5i',5),
    ('6i',6),
    ('7i',7),
    ('8i',8),
    ('9i',9),
    ('10i',10);

INSERT INTO calendar
select * from
    (select adddate('1970-01-01',t4*10000 + t3*1000 + t2*100 + t1*10 + t0) gen_date from
                                                                                        (select 0 t0 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,
                                                                                        (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,
                                                                                        (select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,
                                                                                        (select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,
                                                                                        (select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v
where gen_date between '2022-01-01' and '2022-12-31';

INSERT INTO available (lid, query_date)
SELECT lId, query_date
from calendar, (select lId from listing where TRUE) as tmp;

# INSERT INTO available (lId,query_date)
# VALUES
#     ('1i','2022-05-24'),
#     ('2i','2022-10-04'),
#     ('3i','2022-01-19'),
#     ('4i','2022-04-11'),
#     ('5i','2022-08-26'),
#     ('6i','2022-10-24'),
#     ('7i','2022-07-21'),
#     ('8i','2022-10-03'),
#     ('9i','2022-05-01'),
#     ('10i','2022-08-28');


INSERT INTO owned (lId,uId)
VALUES
    ('1i',1),
    ('2i',2),
    ('3i',3),
    ('4i',4),
    ('5i',5),
    ('6i',6),
    ('7i',7),
    ('8i',8),
    ('9i',9),
    ('10i',10);


INSERT INTO rented (rentedId,rId,lId,hId,start_date,end_date)
VALUES
    ('1_r',1,'1i',1,'2021-02-04','2022-09-25'),
    ('2_r',2,'2i',2,'2021-02-02','2022-01-06'),
    ('3_r',3,'3i',3,'2021-02-09','2023-03-03'),
    ('4_r',4,'4i',4,'2021-02-08','2022-02-07'),
    ('5_r',5,'5i',5,'2021-02-08','2022-12-01'),
    ('6_r',6,'6i',6,'2021-02-07','2022-09-04'),
    ('7_r',7,'7i',7,'2021-02-09','2022-10-07'),
    ('8_r',8,'8i',8,'2021-02-05','2022-03-19'),
    ('9_r',9,'9i',9,'2021-02-11','2022-05-20'),
    ('10_r',10,'10i',10,'2021-02-10','2021-10-05');

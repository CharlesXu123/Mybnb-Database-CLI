INSERT INTO renter (uid, name,address,date_of_birth,occupation,payment_info)
VALUES
    ('0','Preston donnell','6728 Aliquam Avenue','1999-06-07','Vulputate Lacus Institute','670654 6557434452'),
    ('1','Noble Payne','P.O. Box 962, 6664 Massa. Ave','1999-09-06','Aliquet Sem Institute','417500 492481 5732'),
    ('2','Quail Berg','Ap #754-6127 Erat, Ave','1999-06-20','Tincidunt Congue LLP','630 48734 41764 944'),
    ('3','Minerva Terrell','3927 Adipiscing St.','2000-02-16','Ut Quam Vel LLC','6334 1254 5373 4669'),
    ('4','Kaye Fowler','906-4772 Gravida Av.','1999-09-21','Id Blandit Consulting','675 98623 94577 759'),
    ('5','Patrick Ware','Ap #615-8048 Placerat Rd.','1999-12-26','Velit Cras Institute','525 72878 35683 676'),
    ('6','Zephr Church','Ap #761-1644 Ipsum Ave','1999-11-04','Mi Lacinia Industries','6304558847788882'),
    ('7','Wynne Mcintosh','Ap #216-2820 Sapien. St.','2000-03-05','Eget Foundation','4916228847584844'),
    ('8','Asher Harvey','Ap #381-9299 Justo Street','2000-02-19','Interdum Enim Incorporated','658839 2212381124'),
    ('9','Harlan Barton','P.O. Box 538, 5814 Ut Rd.','2000-10-22','Arcu Nunc Institute','6486247576722367');

INSERT INTO host (name,address,date_of_birth,occupation,uId)
VALUES
    ('Ulysses Cantrell','Ap #969-9742 Aliquam Road','1999-12-07','Velit Cras Limited',1),
    ('Griffith Snyder','479-3022 Lorem, Rd.','2000-08-24','Auctor Vitae Company',2),
    ('Wang Flynn','Ap #463-6521 Feugiat Rd.','2000-10-05','Iaculis Institute',3),
    ('Lance Payne','293-8490 Cras Avenue','2000-06-17','Dolor Corp.',4),
    ('Palmer Hodge','P.O. Box 826, 651 Sed Street','1999-11-11','Iaculis Foundation',5);


INSERT INTO  listing (lid,type,latitude,longitude,postal_code,city,country)
VALUES ('a','in',3,6,'379850','Tønsberg','Austria'),
       ('b','sem',8,5,'70384','Tranås','Ukraine'),
       ('c','ut',1,4,'89428','Warszawa','Italy'),
       ('d','enim.',1,4,'514518','South Burlington','Turkey'),
       ('e','facilisis',8,9,'60725','Hamburg','South Africa'),
       ('f','tristique',0,5,'743722','Uitenhage','New Zealand'),
       ('g','ut',3,6,'6294','Hengelo','Australia'),
       ('h','Proin',9,10,'8144 UM','Saint-LŽger','New Zealand'),
       ('i','scelerisque',2,10,'886384','Huntly','Spain'),
       ('j','vitae',7,8,'32G 4X5','Virginia','Chile');

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


INSERT INTO has (lId, aId)
VALUES ('a', '1'),
       ('a', '2'),
       ('a', '3'),
       ('b', '1'),
       ('c', '1'),
       ('d', '2'),
       ('e', '34'),
       ('e', '23'),
       ('a', '6'),
       ('h', '1'),
       ('h', '2'),
       ('h', '9');

insert into available
VALUES  ('a','2021-07-25',true,106),
        ('b','2021-07-25',true,86),
        ('c','2021-07-20',true,57),
        ('d','2021-07-28',true,92),
        ('e','2021-07-23',true,144),
        ('f','2021-07-21',false,100),
        ('g','2021-07-27',false,136),
        ('h','2021-07-23',false,183),
        ('i','2021-07-26',false,85),
        ('j','2021-07-27',false,182);

insert into rented(rentedId, rId, lId, hId, start_date, end_date)
values   (1,'a','0',1,'2021-07-24','2022-10-01'),
         (2,'b','1',2,'2021-07-21','2023-06-15'),
         (3,'c','2',3,'2021-07-26','2023-06-22'),
         (4,'d','3',4,'2021-07-26','2023-04-09'),
         (5,'e','4',5,'2021-07-23','2021-11-03');
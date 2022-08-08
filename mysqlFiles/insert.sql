INSERT INTO renter (uid, name, address, date_of_birth, occupation, payment_info)
VALUES ('1', 'Noble Payne', 'P.O. Box 962, 6664 Massa. Ave', '1999-09-06', 'Aliquet Sem Institute',
        '417500 492481 5732'),
       ('2', 'Quail Berg', 'Ap #754-6127 Erat, Ave', '1999-06-20', 'Tincidunt Congue LLP', '630 48734 41764 944'),
       ('3', 'Minerva Terrell', '3927 Adipiscing St.', '2000-02-16', 'Ut Quam Vel LLC', '6334 1254 5373 4669'),
       ('4', 'Kaye Fowler', '906-4772 Gravida Av.', '1999-09-21', 'Id Blandit Consulting', '675 98623 94577 759'),
       ('5', 'Patrick Ware', 'Ap #615-8048 Placerat Rd.', '1999-12-26', 'Velit Cras Institute', '525 72878 35683 676'),
       ('6', 'Zephr Church', 'Ap #761-1644 Ipsum Ave', '1999-11-04', 'Mi Lacinia Industries', '6304558847788882'),
       ('7', 'Wynne Mcintosh', 'Ap #216-2820 Sapien. St.', '2000-03-05', 'Eget Foundation', '4916228847584844'),
       ('8', 'Asher Harvey', 'Ap #381-9299 Justo Street', '2000-02-19', 'Interdum Enim Incorporated',
        '658839 2212381124'),
       ('9', 'Harlan Barton', 'P.O. Box 538, 5814 Ut Rd.', '2000-10-22', 'Arcu Nunc Institute', '6486247576722367'),
       ('10', 'Preston donnell', '6728 Aliquam Avenue', '1999-06-07', 'Vulputate Lacus Institute', '670654 6557434452');

INSERT INTO host (uId, name, address, date_of_birth, occupation)
VALUES (1, 'Raven Baker', '552-2512 Ut, Rd.', '2000-02-19', 'Vitae Dolor Institute'),
       (2, 'Halee Wiley', '5516 Mauris Street', '1999-09-24', 'Eget PC'),
       (3, 'Aimee Pittman', 'P.O. Box 906, 3685 Eros Street', '1999-05-25', 'Neque In LLC'),
       (4, 'Ryder Greene', '410-9509 Urna St.', '2000-09-15', 'Aliquam Enim LLP'),
       (5, 'Quinn Miranda', '985-6161 Nunc St.', '2000-03-08', 'Natoque Corporation'),
       (6, 'Eaton Watts', 'Ap #914-3802 Eu Road', '2000-09-06', 'Lobortis PC'),
       (7, 'Sara Lott', '3366 Cursus Av.', '2000-05-26', 'Nec Industries'),
       (8, 'John Lane', '487-5599 Ornare Avenue', '2000-03-23', 'Ullamcorper Corporation'),
       (9, 'Noble Blake', 'Ap #374-5673 Integer St.', '2000-04-30', 'Nisl Corp.'),
       (10, 'Amal Austin', 'P.O. Box 219, 1875 Tincidunt. Street', '2000-07-30', 'Ut Cursus Limited');

INSERT INTO listing (lId, type, latitude, longitude, postal_code, city, country)
VALUES ('1i', 'apartment', '-19.2761542656', '-136.6154386432', '735071', 'Llanquihue', 'Russian Federation'),
       ('2i', 'full house', '5.1550667776', '-85.7640810496', '965519', 'Larvik', 'Peru'),
       ('3i', 'room', '-41.0428909568', '138.0606258176', '65150-023', 'San Antonio', 'Russian Federation'),
       ('4i', 'apartment', '-58.4752264192', '148.8919666688', '217978', 'Ravenstein', 'Philippines'),
       ('5i', 'room', '71.386918912', '151.5778917376', 'VN4I 6TP', 'Sokoto', 'Chile'),
       ('6i', 'apartment', '-39.9093055488', '-149.3089588224', '44-25', 'Cochamó', 'Pakistan'),
       ('7i', 'full house', '-10.3630294016', '5.8111873024', '47720', 'Pinetown', 'Brazil'),
       ('8i', 'full house', '47.8830946304', '10.1684512768', 'R6N 1NL', 'Hafizabad', 'Spain'),
       ('9i', 'room', '-26.0834111488', '111.6775187456', '5548-2535', 'Fredericton', 'Russian Federation'),
       ('10i', 'room', '66.8276508672', '-36.7207641088', '685635', 'Kollam', 'Netherlands'),
       ('11i', 'room', '66.8276508672', '-36.7207641088', '685635', 'Kollam', 'Netherlands'),
       ('12i', 'room', '66.8276508672', '-36.7207641088', '685635', 'Kollam', 'Netherlands'),
       ('13i', 'room', '66.8276508672', '-36.7207641088', '685635', 'Kollam', 'Netherlands'),
       ('14i', 'room', '66.8276508672', '-36.7207641088', '685635', 'Kollam', 'Russian Federation'),
       ('15i', 'apartment', '-58.4752264192', '148.8919666688', '217978', 'Ravenstein', 'Philippines');



INSERT INTO amenity
values ('1', 'Toilet paper'),
       ('2', 'Soap for hands and body'),
       ('3', 'One towel per guest'),
       ('4', 'Linens for each bed'),
       ('5', 'One pillow per guest'),
       ('6', 'Cleaning supplies'),
       ('7', 'A pool'),
       ('8', 'Wifi'),
       ('9', 'A kitchen'),
       ('10', 'Free parking'),
       ('11', 'A jacuzzi'),
       ('12', 'A washer or dryer'),
       ('13', 'Air conditioning or heating'),
       ('14', 'Self check-in'),
       ('15', 'Laptop-friendly workspace'),
       ('16', 'Pets allowed'),
       ('17', 'Carbon monoxide alarm'),
       ('18', 'Smoke alarm'),
       ('19', 'Fire extinguisher'),
       ('20', 'First-aid kit'),
       ('21', 'Emergency plan and local numbers'),
       ('22', 'Step-free entryway'),
       ('23', 'Wide entrances (at least 32” or 82 cm)'),
       ('24', 'Wide hallways (at least 36” or 92 cm)'),
       ('25', 'Accessible bathroom'),
       ('26', 'Extra toilet paper, linens, and towels'),
       ('27', 'Basic toiletries like shampoo and conditioner'),
       ('28', 'Dish soap and cleaning supplies'),
       ('29', 'Dining basics like a coffee maker, cooking utensils, dishes, and utensils'),
       ('30', 'Wine glasses'),
       ('31', 'Basic cooking supplies like salt, pepper, and oil'),
       ('32', 'Coffee, tea'),
       ('33', 'Light breakfast or snacks'),
       ('34', 'Hangers'),
       ('35', 'Adapters and chargers'),
       ('36', 'Fast and reliable wifi'),
       ('37', 'Laptop-friendly workspace'),
       ('38', 'Good lighting'),
       ('39', 'Fully equipped kitchens'),
       ('40', 'Office supplies'),
       ('41', 'A crib and high chair'),
       ('42', 'A bathtub'),
       ('43', 'Air conditioning'),
       ('44', 'A washer and/or dryer'),
       ('45', 'Extra cleaning supplies'),
       ('46', 'Furniture covers'),
       ('47', 'Bowls for pet food and water'),
       ('48', 'Towels to wipe off paws at the door');

INSERT INTO listing (lid, type, latitude, longitude, address, postal_code, city, country)
VALUES ('a', 'in', 3, 6, 'what', '379850', 'Tønsberg', 'Austria'),
       ('b', 'sem', 8, 5, 'what', '70384', 'Tranås', 'Ukraine'),
       ('c', 'ut', 1, 4, 'what', '89428', 'Warszawa', 'Italy'),
       ('d', 'enim.', 1, 4, 'what', '514518', 'South Burlington', 'Turkey'),
       ('e', 'facilisis', 8, 9, 'what', '60725', 'Hamburg', 'South Africa'),
       ('f', 'tristique', 0, 5, 'what', '743722', 'Uitenhage', 'New Zealand'),
       ('g', 'ut', 3, 6, 'what', '6294', 'Hengelo', 'Australia'),
       ('h', 'Proin', 9, 10, 'what', '8144 UM', 'Saint-LŽger', 'New Zealand'),
       ('i', 'scelerisque', 2, 10, 'what', '886384', 'Huntly', 'Spain'),
       ('j', 'vitae', 7, 8, 'what', '32G 4X5', 'Virginia', 'Chile'),
       ('k', 'vitae', 43.784635, -79.189862, 'what', '32G 4X5', 'Virginia', 'Chile'),
       ('l', 'vitae', 43.778657, -79.251816, 'what', '32G 4X5', 'Virginia', 'Chile'),
       ('m', 'vitae', 43.800476, -79.340542, 'what', '32G 4X5', 'Virginia', 'Chile'),
       ('n', 'vitae', 43.829009, -79.161257, 'what', '32G 4X5', 'Virginia', 'Chile'),
       ('o', 'vitae', 43.829009, -79.161257, 'screw driver', '32G 4X5', 'Virginia', 'Chile');

INSERT INTO has (lId, aId)
VALUES ('1i', 1),
       ('2i', 2),
       ('3i', 3),
       ('4i', 4),
       ('5i', 5),
       ('6i', 6),
       ('7i', 7),
       ('8i', 8),
       ('9i', 9),
       ('10i', 10),
       ('j', 1),
       ('j', 2),
       ('j', 3),
       ('j', 4),
       ('k', 1),
       ('k', 2),
       ('k', 3),
       ('k', 4),
       ('11i', 10),
       ('12i', 10),
       ('13i', 10),
       ('14i', 10),
       ('15i', 10),
       ('11i', 11),
       ('12i', 12),
       ('13i', 13),
       ('14i', 14),
       ('15i', 15);

INSERT INTO calendar
select *
from (select adddate('1970-01-01', t4 * 10000 + t3 * 1000 + t2 * 100 + t1 * 10 + t0) gen_date
      from (select 0 t0
            union
            select 1
            union
            select 2
            union
            select 3
            union
            select 4
            union
            select 5
            union
            select 6
            union
            select 7
            union
            select 8
            union
            select 9) t0,
           (select 0 t1
            union
            select 1
            union
            select 2
            union
            select 3
            union
            select 4
            union
            select 5
            union
            select 6
            union
            select 7
            union
            select 8
            union
            select 9) t1,
           (select 0 t2
            union
            select 1
            union
            select 2
            union
            select 3
            union
            select 4
            union
            select 5
            union
            select 6
            union
            select 7
            union
            select 8
            union
            select 9) t2,
           (select 0 t3
            union
            select 1
            union
            select 2
            union
            select 3
            union
            select 4
            union
            select 5
            union
            select 6
            union
            select 7
            union
            select 8
            union
            select 9) t3,
           (select 0 t4
            union
            select 1
            union
            select 2
            union
            select 3
            union
            select 4
            union
            select 5
            union
            select 6
            union
            select 7
            union
            select 8
            union
            select 9) t4) v
where gen_date between '2022-01-01' and '2022-12-31';

INSERT INTO available (lid, query_date)
SELECT lId, query_date
from calendar,
     (select lId from listing where TRUE) as tmp;

# INSERT INTO available (lid, query_date, available)
# VALUES ('j', '2022-01-01', 1);

UPDATE available
SET available = 1
WHERE lid = 'j'
  and query_date = '2022-01-01'
;

UPDATE available
SET available = 1
WHERE lid = 'k'
  and query_date = '2022-01-01'
;
UPDATE available
SET available = 1
WHERE lid = 'l'
  and query_date = '2022-01-01'
;
UPDATE available
SET available = 1
WHERE lid = 'm'
  and query_date = '2022-01-01'
;
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


INSERT INTO owned (lId, uId)
VALUES ('1i', 1),
       ('2i', 2),
       ('3i', 3),
       ('4i', 4),
       ('5i', 5),
       ('6i', 6),
       ('7i', 7),
       ('8i', 8),
       ('9i', 9),
       ('10i', 10),
       ('11i', 7),
       ('12i', 7),
       ('13i', 7),
       ('14i', 3),
       ('15i', 3);



INSERT INTO rented (rentedId, rId, lId, hId, start_date, end_date, renter_comments)
VALUES ('1_r', 1, '1i', 1, '2022-05-25', '2023-02-28', 'est, vitae sodales nisi magna sed dui. Fusce aliquam, enim'),
       ('2_r', 2, '2i', 2, '2022-08-13', '2023-06-29', 'porta elit, a feugiat tellus lorem eu metus. In lorem. Donec'),
       ('3_r', 3, '3i', 3, '2022-11-09', '2022-01-25',
        'porttitor scelerisque neque. Nullam nisl. Maecenas malesuada fringilla est. Mauris eu turpis. Nulla aliquet. Proin velit. Sed malesuada augue ut lacus. Nulla tincidunt, neque vitae semper egestas,'),
       ('4_r', 4, '4i', 4, '2022-02-09', '2023-02-22',
        'Nam ac nulla. In tincidunt congue turpis. In condimentum. Donec at arcu. Vestibulum ante ipsum primis in faucibus orci'),
       ('5_r', 5, '5i', 5, '2022-06-14', '2023-01-20', 'sem, vitae aliquam eros turpis non enim.'),
       ('6_r', 6, '6i', 6, '2022-03-29', '2021-11-24',
        'id enim. Curabitur massa. Vestibulum accumsan neque et nunc. Quisque ornare tortor at risus. Nunc ac sem'),
       ('7_r', 7, '7i', 7, '2022-06-20', '2022-07-14', 'vehicula et, rutrum eu, ultrices sit amet, risus. Donec nibh'),
       ('8_r', 8, '8i', 8, '2022-09-05', '2022-08-14', 'diam vel arcu. Curabitur ut odio'),
       ('9_r', 9, '9i', 9, '2022-02-25', '2022-09-13',
        'velit in aliquet lobortis, nisi nibh lacinia orci, consectetuer euismod est arcu ac orci. Ut semper'),
       ('10_r', 10, '10i', 10, '2022-07-04', '2021-10-14',
        'rutrum. Fusce dolor quam, elementum at, egestas a, scelerisque sed, sapien. Nunc pulvinar arcu et pede. Nunc sed');
INSERT INTO rented (rentedId, rId, lId, hId, start_date, end_date, canceled, renter_comments)
VALUES ('11_r', 1, '1i', 1, '2022-09-14', '2021-11-16', 1, 'fermentum convallis ligula. Donec'),
       ('12_r', 2, '2i', 2, '2022-04-08', '2022-03-30', 1,
        'facilisis non, bibendum sed, est. Nunc laoreet lectus quis massa. Mauris vestibulum, neque sed dictum eleifend, nunc risus varius orci, in consequat'),
       ('13_r', 3, '3i', 3, '2022-09-05', '2023-03-22', 1,
        'egestas. Duis ac arcu. Nunc mauris. Morbi non sapien molestie orci tincidunt adipiscing. Mauris'),
       ('14_r', 4, '4i', 4, '2022-07-11', '2021-11-01', 1, 'Nulla dignissim. Maecenas ornare'),
       ('21_r', 4, '4i', 4, '2022-07-11', '2021-11-01', 1, 'Nulla dignissim. Maecenas ornare'),
       ('22_r', 4, '4i', 4, '2022-07-11', '2021-11-01', 1, 'Nulla dignissim. Maecenas ornare'),
       ('23_r', 4, '4i', 4, '2022-07-11', '2021-11-01', 1, 'Nulla dignissim. Maecenas ornare'),
       ('24_r', 4, '4i', 4, '2022-07-11', '2021-11-01', 1, 'Nulla dignissim. Maecenas ornare'),
       ('15_r', 5, '5i', 5, '2022-10-31', '2022-07-07', 1, 'porttitor interdum. Sed auctor odio a'),
       ('16_r', 6, '6i', 6, '2022-10-01', '2021-07-30', 1,
        'Suspendisse sed dolor. Fusce mi lorem, vehicula et, rutrum eu, ultrices sit'),
       ('17_r', 7, '7i', 7, '2022-06-30', '2021-12-18', 1,
        'non, lacinia at, iaculis quis, pede. Praesent eu dui. Cum sociis natoque penatibus et magnis'),
       ('18_r', 8, '8i', 8, '2022-09-07', '2021-12-04', 1,
        'Donec tincidunt. Donec vitae erat vel pede blandit congue. In scelerisque scelerisque dui. Suspendisse ac metus vitae velit egestas lacinia. Sed congue, elit sed consequat auctor,'),
       ('19_r', 9, '9i', 9, '2022-03-03', '2021-08-15', 1,
        'sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum'),
       ('20_r', 10, '10i', 10, '2022-09-22', '2022-12-03', 1,
        'dolor sit amet, consectetuer adipiscing elit. Aliquam auctor, velit eget laoreet posuere, enim nisl elementum purus, accumsan interdum libero'),
       ('25_r', 9, '15i', 9, '2022-03-03', '2021-08-15', 1,
        'sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum'),
       ('26_r', 9, '15i', 9, '2022-03-03', '2021-08-15', 1,
        'sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum'),
       ('27_r', 9, '15i', 9, '2022-03-03', '2021-08-15', 1,
        'sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum'),
       ('28_r', 9, '15i', 9, '2022-03-03', '2021-08-15', 1,
        'sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum'),
       ('29_r', 9, '15i', 9, '2022-03-03', '2021-08-15', 1,
        'sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum'),
       ('30_r', 9, '15i', 9, '2022-03-03', '2021-08-15', 1,
        'sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum'),
       ('31_r', 9, '15i', 9, '2022-03-03', '2021-08-15', 1,
        'sed leo. Cras vehicula aliquet libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum');



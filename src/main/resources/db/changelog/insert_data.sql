INSERT INTO vehicle_type ("NAME") VALUES ('Car'),('Bus'),('Motorbike');

INSERT INTO vehicle_brand ("NAME") VALUES('Skoda'),('Ikarus'),('Fiat'),('Yamaha'),('BMW'),('Honda');

INSERT INTO VEHICLE ("VEHICLE_TYPE_ID", "VEHICLE_BRAND_ID", "MODEL", "YEAR_OF_ISSUE", "GAS_TYPE") VALUES
(1, 1, 'Rapid', 2008, 'PETROL'),
(2, 2, 'Ikarus-489', 2016, 'PROPANE'),
(2, 2, 'Ikarus-435T', 2020, 'DIESEL'),
(3, 4, 'R1', 2020, 'PETROL'),
(3, 4, 'R1M', 2021, 'PETROL'),
(3, 4, 'R6 RACE', 2021, 'PETROL'),
(1, 5, 'M3', 2021, 'PETROL'),
(1, 5, 'x5', 2010, 'DIESEL'),
(1, 5, 'x6', 2012, 'DIESEL'),
(2, 3, 'Ducato', 2014, 'PROPANE'),
(1, 6, 'Civic', 2013, 'PETROL');

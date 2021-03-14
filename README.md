## Demo car-company project

### Tech stack:  

* Java: 1.8
* Spring-boot: 1.5.22.RELEASE
* Database: h2 embedded
* Liquibase: 1.16.22
* Specification-arg-resolver: 1.1.1


### Liquibase migration:

###### vehicle_type

id | name
---|---
1 | Car
2 | Bus
3 | Motorbike


###### vehicle_brand

id | name
---|---
1 | Skoda
2 | Ikarus
3 | Fiat
4 | Yamaha
5 | BMW
6 | Honda

###### vehicle

id | vehicle_type_id | vehicle_brand_id | model | year_of_issue | gas_type
---|-----------------|------------------|-------|---------------|---------
1 |1 |1 |'Rapid' |2008 |'PETROL'
2 |2 |2 |'Ikarus-489' |2016 |'PROPANE'
3 |2 |2 |'Ikarus-435T' |2020 |'DIESEL'
4 |3 |4 |'R1' |2020 |'PETROL'
5 |3 |4 |'R1M' |2021 |'PETROL'
6 |3 |4 |'R6 RACE' |2021 |'PETROL'
7 |1 |5 |'M3' |2021 |'PETROL'
8 |1 |5 |'x5' |2010 |'DIESEL'
9 |1 |5 |'x6' |2012 |'DIESEL'
10 |2 |3 |'Ducato' |2014 |'PROPANE'
11 |1 |6 |'Civic' |2013 |'PETROL'

## How to build the application:

From project base folder execute:

`mvn clean package`

It will build .jar and put it into the same folder. Next execute:  

`java -jar car-company-service.jar`

------
Server starts at port `8080` and the only one endpoint is available (*GET method*):  
> localhost:8080/vehicle  

----
To filter by value use next syntax:

> localhost:8080/vehicle?gasType=propane

or by multiple values:

> localhost:8080/vehicle?gasType=propane&vehicleBrand.name=Ikarus

###### Supported filter values:

* vehicleType.name
* vehicleBrand.name
* model
* yearOfIssue
* gasType

----
To sort by property use next syntax:  

> localhost:8080/vehicle?sortBy=vehicleBrand.name:asc

`asc` direction is by default, so it's equaled to:  

> localhost:8080/vehicle?sortBy=vehicleBrand.name

To sort by multiple properties:

> localhost:8080/vehicle?sortBy=vehicleBrand.name,yearOfIssue:desc


It can be combined (sorting and filtering):

> localhost:8080/vehicle?vehicleType.name=car&sortBy=vehicleBrand.name:desc,model

###### Supported sorting values:

* vehicleType.name
* vehicleBrand.name
* model
* yearOfIssue
* gasType


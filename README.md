### What can be optimized?

* Add a cache
* Implement a rate limiting system to address issues with multiple calls. This could involve using IP addresses or an API key.
* Consider scaling the database. In our case, we are using MariaDB, so we need to think about partitioning.
* Add a Docker container that is not dependent on the environment.
* Consider a retention policy. Do we need to store all information?
* Consider using test containers with MariaDB instead of H2 memory.
* Add an index on the code field.
* Consider using a load balancer to distribute incoming requests across multiple instances
* Consider CDN & Replication
* Implement a logging system
* Add liquibase

## Import CSV
```
LOAD DATA LOCAL INFILE 'PATH'
INTO TABLE nace_details
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@Order, @Level, @Code, @Parent, @Description, @Includes, @AlsoIncludes,
@Rulings,@Excludes, @ISIC4) set order_priority = @Order, level =
@Level, code = @Code, parent = @Parent, description = @Description,includes =
@Includes, also_includes = @AlsoIncludes, rulings = @Rulings,excludes =
@Excludes, reference_to_isic_rev4 = @ISIC4;
```

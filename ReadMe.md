## Setup Instructions

Configure the MySQL database connection by setting the following environment variables

### Configuration Details

The application relies on the following environment variables for database connectivity:

- MYSQL_DRIVER: The fully qualified class name of the MySQL JDBC driver.

- MYSQL_URL: The connection URL

- MYSQL_USERNAME: The MySQL user with access to the database.

- Also set MYSQL_PASSWORD for the database password (ensure it is kept secure).
  Ensure these variables are set correctly to avoid connection issues.
  
```
MYSQL_DRIVER=com.mysql.cj.jdbc.Driver;MYSQL_URL=jdbc:mysql://cobmysql.uhcl.edu/<database_name>?useSSL=false;MYSQL_USERNAME=<your_username>;MYSQL_PASSWORD=<your_password>
```

#### Follow the format below:

For Windows
```
set MYSQL_DRIVER=com.mysql.cj.jdbc.Driver
set MYSQL_URL=jdbc:mysql://localhost:3306/friendbookdb?useSSL=false
set MYSQL_USERNAME=root
set MYSQL_PASSWORD=tiger
```

For Mac
```
export MYSQL_DRIVER=com.mysql.cj.jdbc.Driver
export MYSQL_URL=jdbc:mysql://localhost:3306/friendbookdb?useSSL=false
export MYSQL_USERNAME=root
export MYSQL_PASSWORD=tiger
```

To execute navigate inside project folder and run following cmd
```
java -jar target/java-template-1.0-SNAPSHOT.jar
```

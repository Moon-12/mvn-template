## Setup Instructions

Configure the MySQL database connection by setting the following environment variables

### Configuration Details

The application relies on the following environment variables for database connectivity:

- MYSQL_DRIVER: The fully qualified class name of the MySQL JDBC driver.

- MYSQL_URL: The connection URL

- MYSQL_USERNAME: The MySQL user with access to the database.

- Also set MYSQL_PASSWORD for the database password (ensure it is kept secure).
  Ensure these variables are set correctly to avoid connection issues.

#### To set it run following CMD:

- For Windows

```
set MYSQL_DRIVER=com.mysql.cj.jdbc.Driver
set MYSQL_URL=jdbc:mysql://cobmysql.uhcl.edu/<database_name>?useSSL=false
set MYSQL_USERNAME=<your_username>
set MYSQL_PASSWORD=<your_password>
```

- For Mac

```
export MYSQL_DRIVER=com.mysql.cj.jdbc.Driver
export MYSQL_URL=jdbc:mysql://cobmysql.uhcl.edu/<database_name>?useSSL=false
export MYSQL_USERNAME=<your_username>
export MYSQL_PASSWORD=<your_password>
```

- To generate JAR please download maven from <a href="https://maven.apache.org/download.cgi">here</a>

- Now run clean package cmd like this

```
mvn clean install
```

To execute navigate inside project folder and run following cmd

```
java -jar target/java-template-1.0-SNAPSHOT.jar
```

### DDL Commands

Refer to the ```DDL_friendsbook.sql``` file for DDL commands, located at ```mvn-template/DDLs/``` in the project
directory.
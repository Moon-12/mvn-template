## Setup Instructions

### 1. Execute the DDL Commands to setup the database tables

Refer to the ```DDL_friendsbook.sql``` file for DDL commands, located at ```mvn-template/DDLs/``` in the project
directory.

### 2. Maven Installation

- To generate JAR please download maven from <a href="https://maven.apache.org/download.cgi">here</a>

- Now run clean package cmd like this

```
mvn clean install
mvn clean package
```

> **Note:** If you prefer not to install Maven, you can skip this step and proceed directly to step 3.

### 3. [Optional] Unzip target.zip

Unzip the target.zip file located at -
```mvn-template/target.zip```

> **Note:** Only if step 3 is skipped

### 4. Setup Environment

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

### 5. To execute

Navigate inside project folder and run following cmd

```
java -jar target/Friendsbook-1.0-SNAPSHOT.jar
```
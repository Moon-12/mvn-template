## Setup Instructions

Configure the MySQL database connection by setting the following environment variables

### Configuration Details

The application relies on the following environment variables for database connectivity:

- MYSQL_DRIVER: The fully qualified class name of the MySQL JDBC driver.

- MYSQL_URL: The connection URL

- MYSQL_USERNAME: The MySQL user with access to the database.

- Also set MYSQL_PASSWORD for the database password (ensure it is kept secure).
  Ensure these variables are set correctly to avoid connection issues.

#### Follow the format below:

```
MYSQL_DRIVER=com.mysql.cj.jdbc.Driver;MYSQL_URL=jdbc:mysql://cobmysql.uhcl.edu/<database_name>?useSSL=false;MYSQL_USERNAME=<your_username>;MYSQL_PASSWORD=<your_password>
```
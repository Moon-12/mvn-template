### This is what a simple flow would look like

## First few screens

```
********************************************************************************
*                                                                              *
*          Welcome to the School Management System!                            *
*                                                                              *
********************************************************************************

Main Menu:
1. Student Operations
2. Teacher Operations
3. Course Operations
4. Course Assignment (One Teacher, Many Students) & Marks
type exit to Quit Application

Please enter your choice (1-5): 

[User enters 1]

********************************************************************************
*                       Student Operations                                     *
********************************************************************************
a. Add a new student
b. View student details
c. Update student information
d. Delete a student
e. Back to Main Menu

Please enter your choice (a-e): 

[User enters 2]

********************************************************************************
*                       Teacher Operations                                     *
********************************************************************************
a. Add a new teacher
b. View teacher details
c. Update teacher information
d. Delete a teacher
e. Back to Main Menu

Please enter your choice (a-e): 

[User enters 3]

********************************************************************************
*                       Course Operations                                      *
********************************************************************************
a. Add a new course
b. View course details
c. Update course information
d. Delete a course
e. Back to Main Menu

Please enter your choice (a-e): 

[User enters 4]

********************************************************************************
*          Course Assignment & Marks (One Teacher, Many Students)             *
********************************************************************************
a. Assign a teacher to a course
b. Enroll students in a course
c. Record marks for a student in a course
d. View course assignment details
e. Back to Main Menu

Please enter your choice (a-e): 

[User enters 5]
Thank you for using the School Management System. Goodbye!

```

## All Student screen

```
********************************************************************************
*                                                                              *
*          Welcome to the School Management System!                            *
*                                                                              *
********************************************************************************

Main Menu:
1. Student Operations
2. Teacher Operations
3. Course Operations
4. Course Assignment (One Teacher, Many Students) & Marks
5. Exit

Please enter your choice (1-5): 1

********************************************************************************
*                       Student Operations                                     *
********************************************************************************
a. Add a new student
b. View student details
c. Update student information
d. Delete a student
e. Back to Main Menu

Please enter your choice (a-e): a

********************************************************************************
*                       Add a New Student                                      *
********************************************************************************
Enter Roll No: 101
Enter Name: John Doe

Student successfully added!
Press Enter to continue...
```

## Teacher Screens

```
********************************************************************************
*                                                                              *
*          Welcome to the School Management System!                            *
*                                                                              *
********************************************************************************

Main Menu:
1. Student Operations
2. Teacher Operations
3. Course Operations
4. Course Assignment (One Teacher, Many Students) & Marks
5. Exit

Please enter your choice (1-5): 2

********************************************************************************
*                       Teacher Operations                                     *
********************************************************************************
a. Add a new teacher
b. View teacher details
c. Update teacher information
d. Delete a teacher
e. Back to Main Menu

Please enter your choice (a-e): a

********************************************************************************
*                       Add a New Teacher                                      *
********************************************************************************
Enter Name: Alice Smith

Teacher successfully added!
Press Enter to continue...
```

## Course Screens

```
********************************************************************************
*                                                                              *
*          Welcome to the School Management System!                            *
*                                                                              *
********************************************************************************

Main Menu:
1. Student Operations
2. Teacher Operations
3. Course Operations
4. Course Assignment (One Teacher, Many Students) & Marks
5. Exit

Please enter your choice (1-5): 3

********************************************************************************
*                       Course Operations                                      *
********************************************************************************
a. Add a new course
b. View course details
c. Update course information
d. Delete a course
e. Back to Main Menu

Please enter your choice (a-e): a

********************************************************************************
*                       Add a New Course                                       *
********************************************************************************
Enter Name: Mathematics

Course successfully added!
Press Enter to continue...
```

## Teacher List Screens

```
********************************************************************************
*                                                                              *
*          Welcome to the School Management System!                            *
*                                                                              *
********************************************************************************

Main Menu:
1. Student Operations
2. Teacher Operations
3. Course Operations
4. Course Assignment (One Teacher, Many Students) & Marks
5. Exit

Please enter your choice (1-5): 2

********************************************************************************
*                       Teacher Operations                                     *
********************************************************************************
a. Add a new teacher
b. View teacher details
c. Update teacher information
d. Delete a teacher
e. Back to Main Menu

Please enter your choice (a-e): b

********************************************************************************
*                       Teacher List                                           *
********************************************************************************
Name
---------
Alice Smith
David Brown
Emma Wilson

Total Teachers: 3
Press Enter to continue...
```

## Student List Screen

```
********************************************************************************
*                                                                              *
*          Welcome to the School Management System!                            *
*                                                                              *
********************************************************************************

Main Menu:
1. Student Operations
2. Teacher Operations
3. Course Operations
4. Course Assignment (One Teacher, Many Students) & Marks
5. Exit

Please enter your choice (1-5): 1

********************************************************************************
*                       Student Operations                                     *
********************************************************************************
a. Add a new student
b. View student details
c. Update student information
d. Delete a student
e. Back to Main Menu

Please enter your choice (a-e): b

********************************************************************************
*                       Student List                                           *
********************************************************************************
Roll No  | Name
--------|---------
101     | John Doe
102     | Jane Smith
103     | Bob Johnson

Total Students: 3
Press Enter to continue...
```

## Course List

```
********************************************************************************
*                                                                              *
*          Welcome to the School Management System!                            *
*                                                                              *
********************************************************************************

Main Menu:
1. Student Operations
2. Teacher Operations
3. Course Operations
4. Course Assignment (One Teacher, Many Students) & Marks
5. Exit

Please enter your choice (1-5): 3

********************************************************************************
*                       Course Operations                                      *
********************************************************************************
a. Add a new course
b. View course details
c. Update course information
d. Delete a course
e. Back to Main Menu

Please enter your choice (a-e): b

********************************************************************************
*                       Course List                                            *
********************************************************************************

Course: Mathematics
Designated Teacher: Alice Smith
Enlisted Students and Marks:
Roll No  | Name         | Marks
--------|-------------|------
101     | John Doe    | 85
102     | Jane Smith  | 92
103     | Bob Johnson | 78
Total Students Enrolled: 3

---

Course: Physics
Designated Teacher: David Brown
Enlisted Students and Marks:
Roll No  | Name         | Marks
--------|-------------|------
101     | John Doe    | 88
102     | Jane Smith  | 95
Total Students Enrolled: 2

---

Course: Chemistry
Designated Teacher: Emma Wilson
Enlisted Students and Marks:
Roll No  | Name         | Marks
--------|-------------|------
103     | Bob Johnson | 82
Total Students Enrolled: 1

Total Courses: 3
Press Enter to continue...
```

Another class AppEntity as a parent class for all entities containing all common columns of each tables
like id, WHO columns

## DDL commands for FriendsBook project

```
CREATE TABLE `comment` (
`id` int AUTO_INCREMENT NOT NULL,
`content` varchar(100) DEFAULT NULL,
`post_id` int DEFAULT NULL,
`user_id` varchar(20) DEFAULT NULL,
`created_at` datetime DEFAULT NULL
);
```

```
CREATE TABLE `friend` (
`id` int AUTO_INCREMENT NOT NULL,
`sender_id` varchar(20) DEFAULT NULL,
`receiver_id` varchar(20) DEFAULT NULL,
`created_at` datetime NOT NULL,
`status` varchar(20) NOT NULL DEFAULT 'PENDING'
);

```

```
CREATE TABLE `message` (
  `id` int AUTO_INCREMENT NOT NULL,
  `content` varchar(100) DEFAULT NULL,
  `sender_id` varchar(20) DEFAULT NULL,
  `receiver_id` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'UNREAD'
)
```

```
CREATE TABLE `post` (
  `id` int AUTO_INCREMENT NOT NULL,
  `content` varchar(200) DEFAULT NULL,
  `hashtag_id` varchar(50) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
);
```

```
CREATE TABLE `user_profile` (
  `id` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `school` varchar(20) NOT NULL
);
```
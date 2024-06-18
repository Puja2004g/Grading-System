# Grading system

## concepts used-
1. Java swing
2. Java classes and objects
3. Exception handling
4. SQL (Structured Query Language)

## Features
1. Add Student Details: Input student name, roll number, and email.
2. Add Grades: Input student grades for various subjects and calculate the final grade.
3. Grade Calculation: Calculates the final grade based on the average of marks in different subjects.
4. Database Integration: Uses MySQL for storing student details and grades.

## Database Setup
* Start MySQL Server: Ensure your MySQL server is running.
* Database and Tables Creation:
The database and tables will be created automatically by running the DatabaseConnection class.

## Database Schema
#####  The database schema consists of three tables:

##### studentList:

1. SNo: INT (Primary Key)
2. name: VARCHAR(100)
3. Roll_No: INT
4. email: VARCHAR(100)

##### grades:

1. SNo: INT (Primary Key)
2. name: VARCHAR(100)
3. Roll_No: INT
4. Subjects: INT
5. DSA: DECIMAL(10,2)
6. MMI: DECIMAL(10,2)
7. NT_LAB: DECIMAL(10,2)
8. DBMS: DECIMAL(10,2)
9. Cloud_Computing: DECIMAL(10,2)
10. Sum: DECIMAL(10,2)
11. Grade_symbol: VARCHAR(2)

##### gradeTable:

1. SNo: INT (Primary Key)
2. Score_Range: VARCHAR(30)
3. Grade_Symbol: VARCHAR(2)
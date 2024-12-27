-- Create the database
CREATE DATABASE employee;
USE employee;

-- Create the department table
CREATE TABLE department (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL
);

-- Create the employee table
CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

-- Insert sample data into department table
INSERT INTO department (department_name) VALUES
('HR'),
('Engineering'),
('Finance');

-- Insert sample data into employee table
INSERT INTO employee (employee_name, salary, department_id) VALUES
('Akash', 60000, 1),
('Shyam', 75000, 2),
('Kunal', 50000, 1),
('Raunak', 80000, 2),
('Priyanshu', 70000, 3);
-- Query 1: Print employee_id, employee_name, and department_name
SELECT 
    employee.employee_id, 
    employee.employee_name, 
    department.department_name
FROM 
    employee
JOIN 
    department 
ON 
    employee.department_id = department.department_id;

-- Query 2: Print all employees and their salaries in descending order
SELECT 
    employee_name, 
    salary 
FROM 
    employee
ORDER BY 
    salary DESC;

-- Query 3: Print the average salary of employees in each department with the department name
SELECT 
    department.department_name, 
    AVG(employee.salary) AS average_salary
FROM 
    employee
JOIN 
    department 
ON 
    employee.department_id = department.department_id
GROUP BY 
    department.department_name;
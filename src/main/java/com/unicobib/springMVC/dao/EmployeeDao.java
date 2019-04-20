package com.unicobib.springMVC.dao;

import java.util.List;

import com.unicobib.springMVC.modal.Employee;

public interface EmployeeDao {

		Employee findById(int id);
		void saveEmployee(Employee employee);
		void deleteEmployeeBySsn(String ssn);
		List<Employee> findAllEmployee();
		Employee findAllEmployeeBySsn(String ssn);
}

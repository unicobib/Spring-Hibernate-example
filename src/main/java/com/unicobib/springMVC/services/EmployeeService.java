package com.unicobib.springMVC.services;

import java.util.List;

import com.unicobib.springMVC.modal.Employee;

public interface EmployeeService {
	
	Employee findById(int id);
	void saveEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployeeBySSN(String ssn);
	List<Employee> findAllEmployees();
	Employee findEmployeeBySSN(String ssn);
	boolean isEmployeeSSNUnique(Integer id, String ssn);
	
}

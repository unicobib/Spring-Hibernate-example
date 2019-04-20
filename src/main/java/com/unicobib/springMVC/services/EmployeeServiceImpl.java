package com.unicobib.springMVC.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicobib.springMVC.dao.EmployeeDao;
import com.unicobib.springMVC.modal.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee findById(int id) {

		return employeeDao.findById(id);
	}

	@Override
	public void saveEmployee(Employee employee) {

		employeeDao.saveEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee entity = employeeDao.findById(employee.getId());
		if(entity != null) {
			entity.setName(employee.getName());
			entity.setJoiningDate(employee.getJoiningDate());
			entity.setSalary(employee.getSalary());
			entity.setSsn(employee.getSsn());
		}
	}

	@Override
	public void deleteEmployeeBySSN(String ssn) {
		employeeDao.deleteEmployeeBySsn(ssn);

	}

	@Override
	public List<Employee> findAllEmployees() {
		
		return employeeDao.findAllEmployee();
	}

	@Override
	public Employee findEmployeeBySSN(String ssn) {
		
		return employeeDao.findAllEmployeeBySsn(ssn);
	}

	@Override
	public boolean isEmployeeSSNUnique(Integer id, String ssn) {
		Employee employee = findEmployeeBySSN(ssn);
		return ((employee == null)||(id != null)&&(employee.getId()==id));
	}

}

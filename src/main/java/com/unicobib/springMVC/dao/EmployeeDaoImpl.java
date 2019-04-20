package com.unicobib.springMVC.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.unicobib.springMVC.modal.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {

	@Override
	public Employee findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveEmployee(Employee employee) {

		persist(employee);
	}

	@Override
	public void deleteEmployeeBySsn(String ssn) {
		Query query = getSession().createQuery("delete from Employee where ssn = :ssn");
		query.setString("ssn", ssn);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAllEmployee() {
		Criteria criteria = createEntity();
		return (List<Employee>)criteria.list();
	}

	@Override
	public Employee findAllEmployeeBySsn(String ssn) {
		Criteria criteria = createEntity();
		criteria.add(Restrictions.eq("ssn", ssn));
		return (Employee) criteria.uniqueResult();
	}

}

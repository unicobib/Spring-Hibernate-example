package com.unicobib.springMVC.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unicobib.springMVC.modal.Employee;
import com.unicobib.springMVC.services.EmployeeService;

@Controller
@RequestMapping("/")
public class AppContoller {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value= {"/","/list"},method = RequestMethod.GET)
	public String listEmployee(ModelMap modelMap) {
		
		List<Employee> employees = employeeService.findAllEmployees();
		modelMap.addAttribute("employees",employees);
		return "allemployees";
	}
	
	@RequestMapping(value= {"/new"}, method = RequestMethod.GET)
	public String newEmployee(ModelMap modalMap) {
		Employee employee = new Employee();
		modalMap.addAttribute("employee", employee);
		modalMap.addAttribute("edit", false);
		return "registration";
	}
	
	@RequestMapping(value= {"/new_1"}, method = RequestMethod.GET)
	public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap modelMap) {
		
		if(result.hasErrors()) {
			return "registration";
		}
		
		if(!employeeService.isEmployeeSSNUnique(employee.getId(), employee.getSsn())) {
			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[] {employee.getSsn()},Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}
		
		employeeService.saveEmployee(employee);
		modelMap.addAttribute("success","Employee"+ employee.getName()+" registared successfully");
		return "success";
	}
	
	@RequestMapping(value= {"/update-{ssn}-employee"},method = RequestMethod.GET)
	public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap modelMap,@PathVariable String ssn) {
		if(result.hasErrors()) {
			return "registration";
		}
		if(!employeeService.isEmployeeSSNUnique(employee.getId(), employee.getSsn())) {
			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[] {employee.getSsn()},Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}
		
		employeeService.updateEmployee(employee);
		modelMap.addAttribute("success","Employee "+employee.getName()+" updated successfully");
		return "registration";
	}
	
	@RequestMapping(value= {"/edit-{ssn}-employee"}, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap modelMap) {
		Employee employee = employeeService.findEmployeeBySSN(ssn);
		modelMap.addAttribute("employee",employee);
		modelMap.addAttribute("edit", true);
		return "registration";
	}
	
	@RequestMapping(value= {"/delete-{ssn}-employee"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn) {
		employeeService.deleteEmployeeBySSN(ssn);
		return "redirect:/list";
	}
}

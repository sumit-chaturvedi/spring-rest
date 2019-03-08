package com.threshold.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.threshold.dao.EmployeeDao;


@Service(value = "employeeService")
public class EmployeeServiceImpl extends EmployeeService{
	
	@Autowired
	private EmployeeDao employeeDao;
	

	@Override
	public List<Map<String, Object>> getAllEmployee() {
		return employeeDao.getAllEmployee();
	}


	@Override
	public Map<String, Object> getEmployeeById(int id) {
		return employeeDao.getEmployeeById(id);
	}


	@Override
	public boolean deleteEmployeeById(int id) {
       return employeeDao.deleteEmployeeById(id);
	}


	@Override
	public boolean saveEmployee(String name, String email, String number) {
		return employeeDao.saveEmployee(name, email, number);
	}


	@Override
	public boolean employeeExistOrNot(String email, String password) {
		return employeeDao.EmployeeExistOrNot(email, password);
	}
    
	@Override
	public boolean emailExistOrNot(String email) {
		return employeeDao.emailExistOrNot(email);
	}


	@Override
	public boolean updateEmployeeById(String name, String email, String number, String pkId) {
		return employeeDao.updateEmployeeById(name, email, number, pkId);
	}
}

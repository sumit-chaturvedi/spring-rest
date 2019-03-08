package com.threshold.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;




public abstract class EmployeeService {
	/**
	 * This method is used to fetch employee record by Id.
	 * @param id
	 * @return Map
	 * @author SUMIT.C
	 * @since 2018-09-19  
	 */
	public abstract Map<String, Object> getEmployeeById(int id);

	public abstract List<Map<String, Object>> getAllEmployee();

	public abstract boolean deleteEmployeeById(int id);

	public List<Map<String, Object>> findAllEmployee() {
		List<Map<String,Object>> data =  getAllEmployee();
		/*System.out.println(data);
		Map<String,Object> size = new HashMap<String,Object>();
		size.put("size", data.size());
		data.add(size);*/
		return data;
	}

	public boolean saveEmployee(Map<String, Object> employee) {
		return saveEmployee(employee.get("name")+"", employee.get("email")+"", employee.get("number")+"");
	}
	
	/**
	 * This method is used to insert Employee record by Id.
	 * @param name
	 * @param email
	 * @param number
	 * @param password
	 * @return boolean
	 *  @author SUMIT.C
	 * @since 2018-09-19  
	 */
	public abstract boolean saveEmployee(String name, String email, String number) ;
	
	/**
	 * This method is used to validate employee exist or not in Database.
	 * @param email
	 * @param password
	 * @return boolean
	 * @author SUMIT.C
	 * @since 2018-09-19 
	 */
	public abstract boolean employeeExistOrNot(String email, String password);
	
	/**
	 * This method is used to validate email exist or not in Database.
	 * @param email
	 * @return boolean
	 * @author SUMIT.C
	 * @since 2018-09-19 
	 */
	public abstract boolean emailExistOrNot(String email);
	
	/**
	 * This method is used to updating employee record by Id.
	 * @param name
	 * @param email
	 * @param number
	 * @param password
	 * @param pkId
	 * @return boolean
	 * @author SUMIT.C
	 * @since 2018-09-19
	 */
	public abstract boolean updateEmployeeById(String name, String email, String number, String pkId);

	
}

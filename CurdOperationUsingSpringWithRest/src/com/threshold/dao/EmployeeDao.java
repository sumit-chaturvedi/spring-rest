package com.threshold.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "employeeDao")
public class EmployeeDao   {

	private static final String GET_ALL_EMPLOYEE = "SELECT pk_id uid, emp_name name, emp_email email, emp_number number FROM emp_detail where is_deleted = 0";
	private static final String GET_EMPLOYEE_BY_ID = "SELECT pk_id uid, emp_name name, emp_email email, emp_number number  FROM emp_detail WHERE pk_id = ?";
	private static final String UPDATE_BY_EMAIL = "UPDATE emp_detail SET emp_name=?,emp_email = ?, emp_number = ? WHERE pk_id = ?";
	private static final String DELETE_EMPLOYEE_BY_ID = "UPDATE emp_detail SET is_deleted = 1 WHERE pk_id = ?";
	private static final String SAVE_EMPLOYEE = "INSERT into emp_detail (emp_name, emp_email, emp_number) VALUES (?,?,?)";
	private static final String EMPLOYEE_EXIST_OR_NOT = "SELECT COUNT(*) count FROM emp_detail WHERE emp_email = ? AND emp_password = ?";
	private static final String CHECK_EMAIL = "SELECT COUNT(*) count FROM emp_detail WHERE emp_email = ?";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * This method is used to fetching all records from database.
	 * @author SUMIT.C
	 * @since2019-03-08
	 * @return List
	 */
	public List<Map<String, Object>> getAllEmployee() {
		return jdbcTemplate.queryForList(GET_ALL_EMPLOYEE);
	}
	
	/**
	 * This method is used to fetching record by EmployeeId.
	 * @author SUMIT.C
	 * @since2019-03-08
	 * @return Map
	 */
	public Map<String, Object> getEmployeeById(int id) {
		return jdbcTemplate.queryForMap(GET_EMPLOYEE_BY_ID, id);
	}
	
	/**
	 * This method is used to deleting record by EmployeeId.
	 * @author SUMIT.C
	 * @since2019-03-08
	 * @return boolean 
	 */
	public boolean deleteEmployeeById(int id) {
		int count = jdbcTemplate.update(DELETE_EMPLOYEE_BY_ID, id);
		return count > 0;
	}
	
	/**
	 * This method is used to inserting employee record on Database.
	 * @param name
	 * @param email
	 * @param number
	 * @param password
	 * @author SUMIT.C
	 * @since2019-03-08
	 * @return boolean
	 */
	public boolean saveEmployee(String name, String email, String number) {
		int count = jdbcTemplate.update(SAVE_EMPLOYEE, name, email, number);
		return count>0;
	}

	/**
	 * This method is used to validate employee exist or not on database.
	 * @param email
	 * @param password
	 * @author SUMIT.C
	 * @since2019-03-08
	 * @return boolean
	 */
	public boolean EmployeeExistOrNot(String email, String password) {
		int count = Integer.parseInt(jdbcTemplate.queryForMap(EMPLOYEE_EXIST_OR_NOT,new Object[] { email, password }).get("count")+"");
		return count > 0;
	}
	/**
	 * This method is used to validate email exist or not on database.
	 * @param email
	 * @author SUMIT.C
	 * @since2019-03-08
	 * @return boolean
	 */
	public boolean emailExistOrNot(String email) {
		int count = Integer.parseInt(jdbcTemplate.queryForMap(CHECK_EMAIL,new Object[] { email}).get("count")+"");
		if(count > 0)
			return false;
		else 
			return true;
	}
	
	/**
	 * This method is used to update employee Record by Id.
	 * @param name
	 * @param email
	 * @param number
	 * @param password
	 * @param pkId
	 * @author SUMIT.C
	 * @since2019-03-08
	 * @return boolean
	 */
	public boolean updateEmployeeById(String name, String email, String number, String pkId) {
		   int count = jdbcTemplate.update(UPDATE_BY_EMAIL, name, email, number, pkId);
		   return count > 0;
		}
	
	
}

package com.threshold.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.threshold.service.EmployeeService;

/**
 * This class is used to performing create, read, update, delete operation on DataBase.
 * @author SUMIT.C
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Scope(value="prototype") 
public class MyController extends BaseController{

	@Autowired
	private EmployeeService employeeService;

	/**
	 * @api {get} /CurdOperationUsingSpringWithRest/getEmployee
       @apiSuccessExample {json} Success-Response: 
       {
 	    "success": true,
	    "message": [
  	         {
			"pk_id": 2,
			"emp_name": "ram",
			"emp_email": "rajesh@gmail.com",
			"emp_number": 545445,
			"emp_password": "545"
			},
  			{
			"pk_id": 3,
			"emp_name": "karan",
			"emp_email": "karan@gmail.com",
			"emp_number": 564534,
			"emp_password": "karan"
			},
		],
	}
    @apiSuccessExample {json} Failure-Response: 
 	HTTP/1.1 200 OK
 	{
      "success": false,
   	  "message": "Please login"
	}
	 * @param session
	 * @param req
	 * @return ResponseEntity<Object>
	 * @author SUMIT.C
	 * @since 2019-03-07
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<Object> listAllEmployees(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> listOfEmployees = employeeService.findAllEmployee();
		result.put("success", true);
		result.put("message", listOfEmployees);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	/**
	 * @api {get} /CurdOperationUsingSpringWithRest/getEmployeeById/{Id}
       @apiSuccessExample {json} Success-Response: 
       {
 	    {
		 "success": true,
		  "employeeDetails": {
		    "pk_id": 5,
			"emp_name": "john12",
			"emp_email": "john12@gmail.com",
			"emp_number": 1232223,
			"emp_password": "fsdfsfs"
			}
		}
    @apiSuccessExample {json} Failure-Response: 
 	HTTP/1.1 200 OK
 	{
     "success": false,
   	 "message": "Please login"
	}
	 * @param id
	 * @return ResponseEntity<Object>
	 * @author SUMIT.C
	 * @since 2019-03-07
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> mapOfEachEmployee(@PathVariable("id") int id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> mapOfEmployee = employeeService.getEmployeeById(id);
		result.put("success", true);
		result.put("employeeDetails", mapOfEmployee);
		return new ResponseEntity<Object>( result, HttpStatus.OK);
	}

	/**
	 * @api {delete} /CurdOperationUsingSpringWithRest/deleteEmployeeById/{id}
	 * @apiSuccessExample {json} Success-Response: 
	 * 	{
     	"success": true,
		"message": "User deleted successfully"
		}
	  @apiSuccessExample {json} Failure-Response: 
	   HTTP/1.1 200 OK
	  {
		"success": false,
		"message": "User not deleted successfully"
	  }
	  @apiSuccessExample {json} Failure-Response: 
	  HTTP/1.1 200 OK
 	  {
    	"success": false,
   		"message": "Please login"
	  }
	 * @param id
	 * @return ResponseEntity<Object>
	 * @author SUMIT.C
	 * @since 2019-03-07
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Object> deleteEmployee(@PathVariable("id") int id) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean deleteEmployeeCount = employeeService.deleteEmployeeById(id);
		if(deleteEmployeeCount) {
			result.put("success", true);
			result.put("message", "User deleted successfully");
			return new ResponseEntity<Object>( result, HttpStatus.OK);
		} else {
			result.put("success", false);
			result.put("message", "User not deleted successfully");
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		}
		
	}

	/**
	 * @api {post} /CurdOperationUsingSpringWithRest/saveEmployee
	 *  @apiParamExample {json} Request-Payload:
	 * {
       	"name": "Mahesh",
       	"email": "Mahesh@gmail.com",
  		"number": "9887878",
  		"password": "123123"
	   }
       @apiSuccessExample {json} Success-Response: 
       {
         "success": true,
         "message": " Record are inserted"
       }
       @apiSuccessExample {json} Failure-Response: 
 	   HTTP/1.1 200 OK
 	   {
    	"success": false,
    	"message": "Email already exist"
       }
     @apiSuccessExample {json} Failure-Response: 
	 HTTP/1.1 200 OK
 	 {
      "success": false,
   	  "message": "Please login"
	 }
	 * @param session
	 * @param reqData
	 * @return ResponseEntity<Object>
	 * @author SUMIT.C
	 * @since 2019-03-07
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Object> saveEmployee(@RequestBody Map<String, Object> reqData) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean checkEmail = employeeService.emailExistOrNot(reqData.get("email") + " ");
		if(checkEmail) {
			boolean count = employeeService.saveEmployee(reqData);
			if(count) {
				result.put("success", true);
				result.put("message", " Record are inserted");
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				result.put("success", false);
				result.put("message", " Record are not inserted");
				return new ResponseEntity<Object>(result, HttpStatus.OK);
			}
		} else {
			result.put("success", false);
			result.put("message", "Email already exist");
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		}
	}
	/**
	 ** @api {post} /CurdOperationUsingSpringWithRest/updateEmployee
	 *  @apiParamExample {json} Request-Payload:
	 *{
  		"name": "ram",
  		"email": "rajesh@gmail.com",
 		"number": "545",
  		"password": "545",
	 	"pkId": "3"
	   }
       @apiSuccessExample {json} Success-Response: 
       {
         "success": true,
         "message": " employee record are updated successfully"
       }
       @apiSuccessExample {json} Failure-Response: 
 	   HTTP/1.1 200 OK
 	   {
    	"success": false,
    	"message": "employee record are not updated"
       }
     @apiSuccessExample {json} Failure-Response: 
	 HTTP/1.1 200 OK
 	 {
      "success": false,
   	  "message": "Please login"
	 }
	 * @param session
	 * @param reqData
	 * @return ResponseEntity<Object>
	 * @author SUMIT.C
	 * @since 2019-03-07
	 * @param update
	 * @return ResponseEntity<Object>
	 * @author SUMIT.C
	 * @since 2019-03-07
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Object> updateUser(@RequestBody Map<String, Object> update) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean updateEmployee = employeeService.updateEmployeeById(update.get("name") + "", update.get("email") + "", update.get("number") + "",update.get("pkId") + "");
		if(updateEmployee) {
			result.put("success", true);
			result.put("uid", update.get("pkId"));
			result.put("message", "employee record are updated successfully");
			return new  ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.put("success", "false");
			result.put("message", "employee record are not updated");
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
}

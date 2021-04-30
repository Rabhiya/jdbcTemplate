package com.dao;

import com.Employee.*;
import  java.util.List;

public interface DAOInterface {
	
	long addEmployee(Employee employeeDetails);

	Employee getEmployeeById(long id);

	boolean deleteEmployee(long id);

	long createId();

	boolean updateEmployeeName(String name, long id);

	public boolean updateEmployeeAge(int age, long id);

	boolean updateEmployeeAddress(String address, long id);

	List<Employee> find();


}

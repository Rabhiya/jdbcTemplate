package com.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Employee.*;

public class EmployeeRowMapper implements  RowMapper<Employee> {

		
		public Employee mapRow(ResultSet emp, int rowNumber) throws SQLException {

			return Employee.createNewEmployee(emp.getLong(1), emp.getString(2), emp.getInt(3), emp.getString(4));
		}
		

	}


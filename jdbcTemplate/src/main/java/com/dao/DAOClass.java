package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

//import com.dao.*;
import com.Employee.*;

public class DAOClass {


		private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
		private JdbcTemplate jdbcTemplate;

		public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
			this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		}

		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

		public long addEmployee(Employee employee) {
			try {
				final String INSERT_SQL = "INSERT INTO EMPLOYEE(ID,NAME,AGE,ADDRESS) VALUES(:ID, :NAME, :AGE, :ADDRESS)";
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("EMPLOYEEID", employee.getEmpId());
				parameters.put("EMPLOYEENAME", employee.getEmpName());
				parameters.put("EMPLOYEEAGE", employee.getEmpAge());
				parameters.put("EMPLOYEEADDRESS", employee.getEmpAddress());
				namedParameterJdbcTemplate.update(INSERT_SQL, parameters);

				return employee.getEmpId();

			} catch (Exception e) {
				System.out.println("Data not inserted into database " + e);

			}
			return 0;
		}

		public Employee getEmployeeById(long id) {

			try {
				final String GET_EMPLOYEE_BY_ID = "select * from Employee where ID = :ID";
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("EMPLOYEEID", id);
				List<Employee> results = namedParameterJdbcTemplate.query(GET_EMPLOYEE_BY_ID, parameters,
						new EmployeeRowMapper());
				if (null != results && !results.isEmpty()) {
					return results.get(0);
				}
			} catch (Exception e) {
				System.out.println("Something went wrong");
			}
			return null;
		}

		public boolean deleteEmployee(long id) {
			final String DELETE_EMPLOYEE = "delete from Employee where ID = :ID";
			try {

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("ID", id);
				namedParameterJdbcTemplate.update(DELETE_EMPLOYEE, parameters);
				return true;
			} catch (Exception e) {
				System.out.println("Data not deleted from database");
			}
			return false;
		}

		public long createId() {
			final String LAST_ID = "select * from Employee where rownum=1 order by ID desc";
			try {
				List<Employee> results = namedParameterJdbcTemplate.query(LAST_ID, new EmployeeRowMapper());
				if (null != results && !results.isEmpty()) {
					return results.get(0).getEmpId();
				} else {
					return 10125;
				}

			} catch (Exception e) {
				System.out.println("Error Occurred During Id Creation");
				e.printStackTrace();
			}
			return 0;
		}

		public boolean updateEmployeeName(String name, long id) {
			try {
				final String UPDATE_EMPLOYEE_NAME_SQL = "update Employee set NAME = :NAME where ID = :ID";
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("NAME", name);
				parameters.put("ID", id);
				namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_NAME_SQL, parameters);
				return true;

			} catch (Exception e) {
				System.out.println("Name not updated into database");
			}
			return false;
		}

		public boolean updateEmployeeAge(int age, long id) {
			try {
				final String UPDATE_EMPLOYEE_AGE_SQL = "update Employee set AGE = :AGE where ID = :ID";
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("AGE", age);
				parameters.put("ID", id);
				namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_AGE_SQL, parameters);
				return true;
			} catch (Exception e) {
				System.out.println("Age not updated into database");
			}
			return false;
		}

		public boolean updateEmployeeAddress(String address, long id) {
			try {
				final String UPDATE_EMPLOYEE_ADDRESS_SQL = "update Employee  set ADDRESS = :ADDRESS where ID = :ID";
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("ADDRESS", address);
				parameters.put("ID", id);
				namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_ADDRESS_SQL, parameters);
				return true;

			} catch (Exception e) {
				System.out.println("Address not updated into database");
			}
			return false;
		}

		public List<Employee> getEmployee() {

			final String GET_ALL_EMPLOYEE = "select * from Employee ORDER BY ID";
			try {

				List<Employee> empList = jdbcTemplate.query(GET_ALL_EMPLOYEE, new EmployeeRowMapper());
				return empList;

			} catch (Exception e) {
				System.out.println("Error occured");
				return null;
			}

		}

	}


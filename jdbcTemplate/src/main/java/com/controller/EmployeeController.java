package com.controller;

import java.util.ListIterator;
import java.util.Scanner;

import com.Employee.*;
import com.service.*;

public class EmployeeController {

	private EmployeeService service;
	private Scanner scanner = new Scanner(System.in);

	public void setService(EmployeeService service) {
		this.service = service;
	}

	public void handleRequest() {
		boolean exit = false;
		displayMenu();
		System.out.println("\t\t\t\t\tWelcome to Employee Management System ");
		while (!exit) {
			System.out.print("\nType here: (Press 6 to Display Menu)" + "\n: ");
			String choice = scanner.nextLine();
			try {
				int intChoice = Integer.parseInt(choice);
				switch (intChoice) {
				case 0:
					System.out.println("Application closed successfully");
					exit = true;
					break;
				case 1:
					addEmployee();
					break;
				case 2:
					findEmployee();
					break;
				case 3:
					updateEmployee();
					break;

				case 4:
					deleteEmployee();
					break;

				case 5:
					searchEmployee();
					break;

				case 6:
					displayMenu();
					break;
				}

			} catch (Exception e) {
					exit = true;
				}
			}
		}



	public void addEmployee() {
		try {
			System.out.print("Enter the employee name: ");
			String name = scanner.nextLine();
			System.out.print("Enter employee age: ");
			int Age = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter employee address: ");
			String address = scanner.nextLine();
			Employee emp = Employee.createNewEmployee(0, name, Age, address);
			long id = service.addEmployee(emp);
			System.out.print("Your ID: " + id);
		} catch (Exception e) {
			System.out.println("Something went wrong to insert data...Try Again");
		}

	}

	public void deleteEmployee() {
		try {
			System.out.print("Please enter the id: ");
			String id = scanner.nextLine();
			int integerID = Integer.parseInt(id);
			if (service.isEmployeefind(integerID)) {
				Employee emp = service.searchEmployeeByID(integerID);

				if (emp.getEmpId() <= 0) {
					System.out.println("Record does not exists for ID: " + integerID);
				} else {
					boolean status = service.removeEmployee(integerID);
					if (!status) {
						System.out.println("Deletion not possible");
					} else {
						System.out.println("Deletion Successful");
					}
				}
			} else {
				System.out.println("Employee Details Not Found In The Database For ID " + integerID);
			}

		} catch (Exception e) {
			System.out.println("Error found in deletion");
		}

	}

	public void searchEmployee() {
		try {
			System.out.print("Please enter the id: ");
			String id = scanner.nextLine();
			int integerID = Integer.parseInt(id);
			Employee emp = service.searchEmployeeByID(integerID);

			if (emp == null) {
				System.out.println("Data not availabel for this id: " + integerID);
			} else {
				System.out.println("Employee Id: " + emp.getEmpId() + ", Employee Name: "
						+ emp.getEmpName() + ", Employee age: " + emp.getEmpAge()
						+ ", Employee Address: " + emp.getEmpAddress());

			}
		} catch (Exception e) {
			System.out.println("Something went wrong searching data");

		}

	}
	
	public void findEmployee() {
		ListIterator<Employee> empList = service.find().listIterator();

		while (empList.hasNext()) {
			System.out.println(empList.next().toString());
		}

	}


	
	public void displayMenu() {
		System.out.println("Press\n" + "Enter 0 to exit\n" + "Enter 1 to add employee \n"
				+ "Enter 2 to display employee d\n" + "Enter 3 to update employee\n"
				+ "Enter 4 to remove employee \n" + "Enter 5 to search employee by id\n"
				+ "Enter 6 to Display Menu");
	}

	private void updateEmployeeName() {
		long employeeID = 0;
		System.out.print("Please Enter the Employee Id to Update : ");
		String id = scanner.nextLine();
		try {
			employeeID = Long.parseLong(id);
		} catch (Exception e) {
			System.out.println("Please Enter value:");
		}
		if (service.isEmployeefind(employeeID)) {
			System.out.print("Please Enter updated name : ");
			String name = scanner.nextLine();
			boolean status = service.updateEmployeeName(name, employeeID);
			if (status) {
				System.out.println("Employee name updated for " + employeeID);
			} else {
				System.out.println("Cannot Update Employee Name");
			}

		} else {
			System.out.println("No Data Exist For ID " + employeeID);
		}

	}

	private void updateEmployeeAge() {
		long empID = 0;
		int intAge = 0;
		System.out.print("\nEnter updated age :- ");
		String age = scanner.nextLine();
		try {
			intAge = Integer.parseInt(age);
		} catch (Exception e) {
			System.out.println("Please Enter Valid Age");
		}
		System.out.print(" Enter the Employee Id to Update : ");
		String id = scanner.nextLine();
		try {
			empID = Long.parseLong(id);
		} catch (Exception e) {
			System.out.println("Type here:");
		}
		if (service.isEmployeefind(empID)) {
			boolean status = service.updateEmployeeAge(intAge, empID);
			if (status) {
				System.out.println("Employee name updated for " + empID);
			} else {
				System.out.println("Cannot Update Age");
			}
		} else {
			System.out.println("No Data Exist For ID " + empID);
		}

	}

	private void updateEmployeeAddress() {
		long empID = 0;
		System.out.print("\nEnter updated address :- ");
		String address = scanner.nextLine();
		System.out.print("Enter the Id to Update : ");
		String id = scanner.nextLine();
		try {
			empID = Long.parseLong(id);
		} catch (Exception e) {
			System.out.println("Please Enter Number:");
		}
		if (service.isEmployeefind(empID)) {
			boolean status = service.updateEmployeeAddress(address, empID);
			if (status) {
				System.out.println("Employee name updated for " + empID);
			} else {
				System.out.println("Something went wrong ");
			}
		} else {
			System.out.println("No Data Exist For ID " + empID);
		}

	}

	public void updateEmployee() {
		boolean flag = true;
		while (flag) {
			System.out.println("Select The Field You Want To Update\n");
			System.out.println("Press 1 To Update Name\n" + "Press 2 To Update Age\n" + "Press 3 To Update Address\n"
					+ "Press 0 To Go Back In Main Menu\n");
			System.out.print("\nPress: ");
			String Choice = scanner.nextLine();
			System.out.println("You entered the choice " + Choice);
			int intChoice;
			try {
				intChoice = Integer.parseInt(Choice);

				switch (intChoice) {
				case 1:
					updateEmployeeName();
					break;
				case 2:
					updateEmployeeAge();
					break;
				case 3:
					updateEmployeeAddress();
				case 0:
					flag = false;
					break;
				default:
					break;
				}
			} catch (Exception e) {
				System.out.println("You have Entered an Invalid Input." + "\nTo go Back Enter 1"
						+ "\n Enter 0 To Go Back To Main Menu");
				System.out.println("\nPress: ");
				Choice = scanner.nextLine();
				int IntChoice;
				try {
					IntChoice = Integer.parseInt(Choice);
					switch (IntChoice) {
					case 1:
						flag = true;
						break;
					case 0:
						flag = false;
						break;
					default:
						flag = false;
						break;
					}
				} catch (Exception exp) {
					flag = false;
				}

			}
		}
	}
	
}
	


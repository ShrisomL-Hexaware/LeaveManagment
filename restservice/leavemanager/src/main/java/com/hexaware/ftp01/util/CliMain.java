package com.hexaware.ftp01.util;
import java.util.Scanner;

import com.hexaware.ftp01.model.Employee;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. Apply For leave");
    System.out.println("4. Leave History");
    System.out.println("5. Pending Leave Status");
    System.out.println("6. Approve Or Deny");
    System.out.println("7. Exit");
    System.out.println("Enter your choice:");
    int menuOption = option.nextInt();
    mainMenuDetails(menuOption);
  }
  private void mainMenuDetails(final int selectedOption) {
    switch (selectedOption) {
      case 1:
        listEmployeesDetails();
        break;
      case 2:
        listEmployeeDetail();
        break;
      case 3:
        applyForLeave();
        break;
      case 4:
        listLeaveHistory();
        break;
      case 5:
        listPendingLeaveStatus();
        break;
      case 6:
        approveOrDenyLeave();
        break;
      case 7:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      default:
        System.out.println("Choose either 1, 2 or 3");
    }
    mainMenu();
  }
  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      System.out.println(employee.getEmpId() + " " + employee.getEmpName() + " " + employee.getEmpPhone() + " "
                         + employee.getEmpEmail() + " " + employee.getEmpDept() + " " + employee.getEmpManagerId() + " "
                         + employee.getEmpLeaveBalance() + " " + employee.getEmpDoj());
    }
  }
  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) {
      System.out.println(e.getEmpId() + " " + e.getEmpName() + " " + e.getEmpPhone() + " " + e.getEmpEmail() + " "
                         + e.getEmpDept() + " " + e.getEmpManagerId() + " " + e.getEmpLeaveBalance() + " "
                         + e.getEmpDoj());
    }
  }
  private void approveOrDenyLeave() {
    System.out.println("Enter Manager ID");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);

    if (employee == null) {
      System.out.println("No such employee");
  } else {
    LeaveDetails [] leaveDetails = LeaveDetails.listPendingApplication(empId);
    for (LeaveDetails ld : leaveDetails) {
      System.out.println(ld.toString());
    }
    System.out.println("1) Aprrove\n2) Deny");
    int menuOption = option.nextInt();
    menuDetails (menuOption);
  }
  public final void menuDetails( final int menuOption) {
    switch (menuOption) {
      case 1:
        approve();
        break;
      case 2:
        deny();
        break;
      default:
        System.out.println("Wrong Choice!! Choose either 1 or 2");
    }
    mainMenu();
  }
  public void approve() {
    System.out.println("Enter the leave ID that you want to approve: ");
    int leaveId = option.nextInt();

    LeaveDetails leaveDetails = LeaveDetails.listById(leaveId);
    if (leaveDetails == null) {
      System.out.println("Leave ID not found");
    } else {
      System.out.println("Enter your Comment here");
      String managerComments = option.nextLine();
      LeaveDetails.approveLeave(managerComments, leaveId);
    }
  }
  public void deny() {
    System.out.println("Enter the leave ID that you want to deny: ");
    int leaveId = option.nextInt();

    LeaveDetails leaveDetails = LeaveDetails.listById(leaveId);
    if (leaveDetails == null) {
      System.out.println("Leave ID not found");
    } else {
      System.out.println("Enter your Comment here");
      String managerComments = option.nextLine();
      LeaveDetails.denyLeave(managerComments, leaveId);
  }
  /**
   * The main entry point.
   * @param ar the list of arguments
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}

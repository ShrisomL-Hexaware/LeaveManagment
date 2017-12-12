package com.hexaware.ftp01.util;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.hexaware.ftp01.model.Employee;
import com.hexaware.ftp01.model.LeaveType;

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
  private void applyForLeave()  {
    System.out.println("Enter the empId");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      try {
        if (employee.getEmpLeaveBalance() == 0) {
          throw new IllegalArgumentException();
        } else {
          SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
          System.out.println("Enter Leave Type :");
          String levType  = option.next();
          LeaveType leaveType = LeaveType.valueOf(levType);
          System.out.println("Enter Starting Date :");
          String date1 = option.next();
          Date startDate = myFormat.parse(date1);
          System.out.println("Enter Ending Date :");
          String date2 = option.next();
          Date endDate = myFormat.parse(date2);
          long epochstartDate = myFormat.parse(date1).getTime() / 1000;
          long epochendDate = myFormat.parse(date2).getTime() / 1000;
          if ((epochendDate - epochstartDate) < 0) {
            throw new Exception("Sorry, end date is before start date");
          } else {
            System.out.println("Total Number of days :");
            int numberOfDays = option.nextInt();
            if (numberOfDays < 0) {
              throw new Exception("Enter positive value for number of days.");
            } else {
              System.out.println("Reason :");
              String leaveReason = option.next();
              employee.applyForLeave(leaveType, startDate, endDate, numberOfDays, leaveReason);
            }
          }
        }
      } catch (IllegalArgumentException e) {
        System.out.println("You dont have sufficient leave balance");
      } catch (ParseException e) {
        System.out.println(e);
      } catch (Exception e) {
        System.out.println("Enter correct data. " + e);
      }
    }
  }

  private void listLeaveHistory() {
    System.out.println("To see leave history wait till friday");
  }
  private void listPendingLeaveStatus() {
    System.out.println("To see leave status wait till friday");
  }
  private void approveOrDenyLeave() {
    System.out.println("To approve or deny wait till friday");
  }
  /**
   * The main entry point.
   * @param ar the list of arguments
   * @throws ParseException throw parseexception.
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}

package com.hexaware.ftp01.util;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;

import com.hexaware.ftp01.model.Employee;
import com.hexaware.ftp01.model.LeaveDetails;
import com.hexaware.ftp01.model.LeaveType;
import com.hexaware.ftp01.model.LeaveStatus;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() {

    boolean flag = false;
    while (!flag) {
      System.out.println("Leave Management System");
      System.out.println("-----------------------");
      System.out.println("1. List All Employees Info");
      System.out.println("2. Display Employee Info");
      System.out.println("3. Apply For leave");
      System.out.println("4. Leave History");
      System.out.println("5. Pending Leave Status");
      System.out.println("6. Approve Or Deny");
      System.out.println("7. Exit");
      try {
        System.out.println("Enter your choice:");
        int menuOption = option.nextInt();
        flag = true;
        mainMenuDetails(menuOption);
      } catch (InputMismatchException e) {
        System.out.println("Enter the value 1 - 7");
        flag = false;
        option.nextLine();
      }

    }
  }

  private void mainMenuDetails(final int selectedOption) {

    boolean flag = false;
    while (!flag) {
      try {
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
        flag = true;
      } catch (InputMismatchException e) {
        flag = false;
        option.nextLine();
      }

    }
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

  private void applyForLeave() {
    try {
      System.out.println("Enter the empId :");
      int empId = option.nextInt();
      Employee employee = Employee.listById(empId);
      if (employee == null) {
        System.out.println("Sorry, No such employee");
      } else {
        if (employee.getEmpLeaveBalance() == 0) {
          throw new IllegalArgumentException("You dont have sufficient leave balance");
        } else {
          System.out.println("Available leave Balance is :" + employee.getEmpLeaveBalance());
          SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
          System.out.println("Enter Leave Type :");
          String levType  = option.next();
          LeaveType leaveType = LeaveType.valueOf(levType);
          System.out.println("Enter Starting Date(yyyy/MM/dd) :");
          String date1 = option.next();
          myFormat.setLenient(false);
          System.out.println("Enter Ending Date(yyyy/MM/dd) :");
          String date2 = option.next();
          Date startDate = myFormat.parse(date1);
          Date endDate = myFormat.parse(date2);
          try {
            myFormat.parse(date1.trim());
            myFormat.parse(date2.trim());
          } catch (ParseException e) {
            System.out.println("Wrong Date!!");
            mainMenu();
          }
          System.out.println("Total Number of days :");
          int numberOfDays = option.nextInt();
          System.out.println("Reason :");
          String leaveReason = option.next();
          String applyMessage = employee.applyForLeave(leaveType, startDate, endDate, numberOfDays,
                                leaveReason, date1, date2);
          System.out.println(applyMessage);
        }
      }
    } catch (ParseException e) {
      System.out.println(e);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
  private void listLeaveHistory() {
    try {
      System.out.println("Enter the Employee  Id");
      int empId = option.nextInt();
      Employee employee = Employee.listById(empId);
      LeaveDetails[] leave = LeaveDetails.listLeaveDetailsById(empId);
      if (employee == null) {
        throw new IllegalArgumentException("Sorry, No such employee");
      } else {
        if (leave.length == 0) {
          throw new IllegalArgumentException("Employee " + empId + " has no  leave history");
        } else {
          LeaveDetails.listLeaveDetailsById(empId);
          System.out.println("leave id" + " " + "leave type" + " " + "start date"
                              + "   " + "end date" + "   " + "number of days" + "   " + "leave status"
                              + "   " +  "leave reason" + "   " + "leave applied on" + "   "
                              + "managerComments" + "   " + "empId");
          SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
          for (LeaveDetails ld : leave) {
            System.out.println(ld.getLeaveId() + " " + ld.getLeaveType() + " " + sf.format(ld.getStartDate())
                               + "   " + sf.format(ld.getEndDate()) + "   " + ld.getNumberOfDays()
                               + "   " + ld.getLeaveStatus() + "   " + ld.getLeaveReason()
                               + "   " + sf.format(ld.getLeaveAppliedOn()) + "   " + ld.getManagerComments()
                               + "   " + ld.getEmpId());
          }
        }
      }
    } catch (InputMismatchException e) {
      System.out.println("Enter Correct Employee Id");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }


  private void listPendingLeaveStatus() {
    try {
      System.out.println("Enter the manager Id");
      int empId = option.nextInt();
      Employee employee = Employee.listById(empId);
      if (employee == null) {
        System.out.println("Sorry, No such employee");
      } else {
        LeaveDetails[] leaveDetails = LeaveDetails.listPendingApplication(empId);
        System.out.println("leave id" + " " + "leave type" + " " + "start date"
                        + "   " + "end date" + "   " + "number of days" + "   " + "leave status"
                        + "   " +  "leave reason" + "   " + "leave applied on" + "   "
                        + "managerComments" + "   " + "empId");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        for (LeaveDetails leave : leaveDetails) {
          System.out.println(leave.getLeaveId() + "       " + leave.getLeaveType()
                          + "       " + sf.format(leave.getStartDate())
                          + "  " + sf.format(leave.getEndDate())
                          + "    " + leave.getNumberOfDays()
                          + "         " + leave.getLeaveStatus()
                          + "    " + leave.getLeaveReason()
                          + "      " + sf.format(leave.getLeaveAppliedOn())
                          + "      " + leave.getManagerComments()
                          + "       " + leave.getEmpId());
        }
      }
    } catch (InputMismatchException em) {
      System.out.println("Enter Correct Employee Id");
    }
  }

  private void approveOrDenyLeave() {
    System.out.println("Enter leave Id");
    int leaveId = option.nextInt();
    LeaveDetails leaveData = LeaveDetails.listById(leaveId);
    if (leaveData == null) {
      System.out.println("Sorry, No such Leave detail");
    } else {
      System.out.println(leaveData.toString());
      System.out.println("Either 'APPROVED','DENIED' or 'PENDING'");
      String select = option.next();
      String s = select.toUpperCase();
      LeaveStatus selectStatus = LeaveStatus.valueOf(s);
      option.nextLine();
      System.out.println("Enter comments");
      String managerComments = option.nextLine();
      leaveData.approveDeny(selectStatus, leaveId, managerComments);
    }
  }

  /**
   * The main entry point.
   * @param ar the list of arguments
   * @throws ParseException throw parseexception.
   */
  public static void main(final String[] ar) throws ParseException {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}

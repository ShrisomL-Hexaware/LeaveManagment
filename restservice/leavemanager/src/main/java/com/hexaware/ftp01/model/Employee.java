package com.hexaware.ftp01.model;

import com.hexaware.ftp01.persistence.DbConnection;
import com.hexaware.ftp01.persistence.EmployeeDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Objects;
import java.util.List;
import java.util.Date;

/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {

  /**
   * empId to store employee id.
   * empName to store employee name.
   * empPhone to store employee phone number.
   * empEmail to store employee email.
   * empDept to store employee department.
   * empManagerId to store manager id.
   * empLeaveBalance to store employee leave balance.
   * empDoj to store employee date of joining.
   */

  private int empId;
  private long empPhone;
  private int empManagerId;
  private int empLeaveBalance;
  private String empName;
  private String empEmail;
  private String empDept;
  private String empDoj;

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empId, emp.empId) && Objects.equals(empPhone, emp.empPhone)
        && Objects.equals(empName, emp.empName) && Objects.equals(empManagerId, emp.empManagerId)
        && Objects.equals(empLeaveBalance, emp.empLeaveBalance) && Objects.equals(empEmail, emp.empEmail)
        && Objects.equals(empDept, emp.empDept) && Objects.equals(empDoj, emp.empDoj)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId, empPhone, empManagerId, empLeaveBalance, empLeaveBalance, empName, empEmail,
                        empDept, empDoj);
  }
  @Override
  public final String toString() {
    return "emp id :" + empId + " " + "emp Name :" + empName + " " + "emp Phone :" + empPhone + " "
      + "emp Manager Id :" + empManagerId + " " + "emp Email :" + empEmail + " " + "emp Dept :" + empDept
      + " " +  "emp Leave Balance :" + empLeaveBalance + " " + "emp Doj :" + empDoj;
  }
  /**
   * @param argEmpId to initialize employee id.
   */
  public Employee(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * @param argEmpId to initialize employee id.
   * @param argEmpLeaveBalance to initialize employee leave balance.
   */
  public Employee(final int argEmpId, final int argEmpLeaveBalance) {
    this.empId = argEmpId;
    this.empLeaveBalance = argEmpLeaveBalance;
  }

  /**
   * @param argEmpId to initialize employee id.
   * @param argEmpName to initialize employee name.
   * @param argEmpDept to initialize employee department.
   * @param argEmpPhone to initialize employee Phone.
   * @param argEmpEmail to initialize employee Email.
   * @param argEmpDoj to initialize employee date of join.
   * @param argEmpLeaveBalance to initialize employee Leave balance.
   * @param argEmpManagerId to initialize employee manager id.
   */

  public Employee(final int argEmpId, final String argEmpName, final long argEmpPhone, final String argEmpEmail,
                  final String argEmpDept, final int argEmpManagerId, final int argEmpLeaveBalance,
                  final String argEmpDoj) {
    this.empId = argEmpId;
    this.empName = argEmpName;
    this.empDept = argEmpDept;
    this.empPhone = argEmpPhone;
    this.empEmail = argEmpEmail;
    this.empDoj = argEmpDoj;
    this.empLeaveBalance = argEmpLeaveBalance;
    this.empManagerId = argEmpManagerId;
  }
  /**
   * Gets the EmployeeId.
   * @return this Employee's ID.
   */
  public final int getEmpId() {
    return empId;
  }
  /**
   *
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   *
   * @param argEmpName to set employee name.
   */
  public final void setEmpName(final String argEmpName) {
    this.empName = argEmpName;
  }
   /**
   * Gets the EmployeeName.
   * @return this Employee's Name.
   */
  public final String getEmpName() {
    return empName;
  }

  /**
   * @param argEmpPhone to set employee Phone.
   */
  public final void setEmpPhone(final long argEmpPhone) {
    this.empPhone = argEmpPhone;
  }

  /**
   * Gets the EmployeePhone.
   * @return this Employee's Phone.
   */
  public final long getEmpPhone() {
    return empPhone;
  }

  /**
   *
   * @param argEmpEmail to set employee Email.
   */
  public final void setEmpEmail(final String argEmpEmail) {
    this.empEmail = argEmpEmail;
  }

   /**
   * Gets the EmployeeEmail.
   * @return this Employee's email.
   */
  public final String getEmpEmail() {
    return empEmail;
  }

  /**
   *
   * @param argEmpDept to set employee Dept.
   */
  public final void setEmpDept(final String argEmpDept) {
    this.empDept = argEmpDept;
  }

   /**
   * Gets the EmployeeDept.
   * @return this Employee's Dept.
   */
  public final String getEmpDept() {
    return empDept;
  }

  /**
   * Sets the ManagerId.
   * @param argEmpManagerId to set employee ManagerId.
   */
  public final void setEmpManagerId(final int argEmpManagerId) {
    this.empManagerId = argEmpManagerId;
  }

   /**
   * Gets the EmployeeManagerId.
   * @return this Employee's ManagerId.
   */
  public final int getEmpManagerId() {
    return empManagerId;
  }

  /**
   *
   * @param argEmpLeaveBalance to set employee LeaveBalance.
   */
  public final void setEmpLeaveBalance(final int argEmpLeaveBalance) {
    this.empLeaveBalance = argEmpLeaveBalance;
  }

   /**
   * Gets the EmployeeLeaveBalance.
   * @return this Employee's LeaveBalance.
   */
  public final int getEmpLeaveBalance() {
    return empLeaveBalance;
  }

  /**
   *
   * @param argEmpDoj to set employee Doj.
   */
  public final void setEmpDoj(final String argEmpDoj) {
    this.empDoj = argEmpDoj;
  }

   /**
   * Gets the EmployeeDoj.
   * @return this Employee's Doj.
   */
  public final String getEmpDoj() {
    return empDoj;
  }

  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[] listAll() {

    List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }

  /**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int empID) {
    return dao().find(empID);
  }
  /**
   * list employee leave balance.
   * @param levType to get type of leave.
   * @param levStartDate to get start date.
   * @param levEndDate to get end date.
   * @param levNumberOfDays to get total days.
   * @param levReason to get reason of leave.
   * @param date1 to get date in string.
   * @param date2 to get date in string.
   * @throws IllegalArgumentException to handle exception.
   * @throws ParseException to handle exception.
   */
  public final void applyForLeave(final LeaveType levType, final Date levStartDate, final Date levEndDate,
                                  final int levNumberOfDays, final String levReason, final String date1,
                                  final String date2) throws IllegalArgumentException, ParseException {

    Employee e = new Employee(empId);
    System.out.println("Your available leave balance was : " + empLeaveBalance);
    int empID = e.getEmpId();
    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
    long epochstartDate = myFormat.parse(date1).getTime() / 1000;
    long epochendDate = myFormat.parse(date2).getTime() / 1000;
    Date levAppliedOn = new Date();
    if (levNumberOfDays < 0) {
      throw new IllegalArgumentException("Enter positive value for number of days.");
    } else {
      if ((epochendDate - epochstartDate) < 0) {
        throw new IllegalArgumentException("Sorry, end date is before start date");
      } else if (levNumberOfDays > (epochendDate - epochstartDate)) {
        throw new IllegalArgumentException("Enter correct Number of days for leave!");
      } else {
        if (empLeaveBalance > levNumberOfDays) {
          empLeaveBalance = empLeaveBalance - levNumberOfDays;
          System.out.println("Leave applied for : " + levNumberOfDays + " Days");
          System.out.println("Your updated Leave Balance is : " + empLeaveBalance);
          LeaveDetails.dao().insert(levType, levStartDate, levEndDate, levNumberOfDays, levReason,
                                   levAppliedOn, empID);
          dao().updateLeaveBalance(empLeaveBalance, empID);
        } else {
          throw new IllegalArgumentException("You dont have expected leave balance.!!");
        }
      }
    }
  }
}

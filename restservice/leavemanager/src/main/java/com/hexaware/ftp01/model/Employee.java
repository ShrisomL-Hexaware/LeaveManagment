package com.hexaware.ftp01.model;

import com.hexaware.ftp01.persistence.DbConnection;
import com.hexaware.ftp01.persistence.EmployeeDAO;

import java.util.Objects;
import java.util.List;

/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {

  /**
   * empId to store employee id.
     empName to store employee name.
     empPhone to store employee phone number.
     empEmail to store employee email.
     empDept to store employee department.
     empManagerId to store manager id.
     empLeaveBalance to store employee leave balance.
     empDoj to store employee date of joining.
   */

  private int empId;
  private int empPhone;
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
    if (Objects.equals(empId, emp.empId)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId);
  }

  /**
   * @param argEmpId to initialize employee id.
   */
  public Employee(final int argEmpId) {
    this.empId = argEmpId;
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
   *
   * @param argEmpPhone to set employee Phone.
   */
  public final void setEmpPhone(final int argEmpPhone) {
    this.empPhone = argEmpPhone;
  }

 /**
   * Gets the EmployeePhone.
   * @return this Employee's Phone.
   */
  public final int getEmpPhone() {
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
   *
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

}

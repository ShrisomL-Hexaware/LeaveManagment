package com.hexaware.ftp01.persistence;

import com.hexaware.ftp01.model.Employee;
import com.hexaware.ftp01.model.LeaveType;
import java.util.Date;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);

  /**
   * Insert all the details to leave table.
   * @param levType to get the type of leave.
   * @param levStartDate the starting date of leave.
   * @param levEndDate the ending date of leave.
   * @param levNumberOfDays the total days for leave.
   * @param levReason the reason for leave.
   * @param levAppliedOn to get the date.
   * @param empID the employee Id.
   */
  @SqlUpdate("insert into LEAVE_HISTORY "
             +
             " (LEAVE_TYPE, "
             +
             " START_DATE, "
             +
             " END_DATE, "
             +
             " NUMBER_OF_DAYS, "
             +
             " LEAVE_REASON, "
             +
             " LEAVE_APPLIED_ON, "
             +
             " EMP_ID) "
             +
             "values ("
             +
             " :levType, "
             +
             " :levStartDate, "
             +
             " :levEndDate, "
             +
             " :levNumberOfDays, "
             +
             " :levReason, "
             +
             " :levAppliedOn, "
             +
             " :empID) ")
void insert(@Bind("levType") LeaveType levType,
                @Bind("levStartDate") Date levStartDate,
                @Bind("levEndDate") Date levEndDate,
                @Bind("levNumberOfDays") int levNumberOfDays,
                @Bind("levReason") String levReason,
                @Bind("levAppliedOn")Date levAppliedOn,
                @Bind("empID") int empID);

  /**
   * Update empLeaveBalance.
   * @param empLeaveBalance to update the available balance.
   * @param empID to get employee id.
   */
  @SqlUpdate("UPDATE EMPLOYEE SET EMP_LEAVE_BALANCE = :empLeaveBalance WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  void updateLeaveBalance(@Bind("empLeaveBalance") int empLeaveBalance,
                          @Bind("empID") int empID);

  /**
  * close with no args is used to close the connection.
  */
  void close();
}

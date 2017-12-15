package com.hexaware.ftp01.persistence;

import com.hexaware.ftp01.model.LeaveDetails;

import com.hexaware.ftp01.model.LeaveType;
import com.hexaware.ftp01.model.LeaveDetails;
import java.util.Date;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import com.hexaware.ftp01.model.LeaveStatus;

/**
 * The DAO class for employee.
 */
public interface LeaveDetailsDAO  {

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

<<<<<<< HEAD
 /**
  * return all the details of the selected employee.
  *
  * @param empId the id of the employee
  * @return the employee object
  */
=======
  /**
   * return all the details of the selected employee.
   * @param empId the id of the employee.
   * @return the employee object.
   */
>>>>>>> 75e16b0... changes
  @SqlQuery("SELECT * "
            +
            "FROM LEAVE_HISTORY "
            +
            "INNER JOIN EMPLOYEE ON EMPLOYEE.EMP_ID = LEAVE_HISTORY.EMP_ID "
            +
            "WHERE "
            +
            "EMPLOYEE.EMP_MANAGER_ID = :empId "
            +
            "AND "
            +
            "LEAVE_HISTORY.LEAVE_STATUS = 'PENDING'")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> finds(@Bind("empId") int empId);

  /**
<<<<<<< HEAD
   * return all the details of all the employee's leave details.
   * @param empID the id of the employee
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> list(@Bind("empID") int empID);

  /**
   * return all the leave history of the selected employee.
   * @param empID the id of the employee
   * @return the leave history object
   */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY WHERE EMP_ID = :empID")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails find(@Bind("empID") int empID);

    /**
   * return all the leave details of the selected employee.
   * @param leaveId the id of the employee
   * @return the leave detail object
   */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY WHERE LEAVE_ID = :leaveId")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails fetch(@Bind("leaveId") int leaveId);

  /**
   * Update all the leave status of the selected leave id.
   * @param leaveStatus the status of leave id
   * @param leaveId the leave id
   * @param managerComments the manager comments
   */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET LEAVE_STATUS = :leaveStatus, "
            + "MANAGER_COMMENTS = :managerComments WHERE LEAVE_ID= :leaveId")
  void update(@Bind("leaveStatus") LeaveStatus leaveStatus,
              @Bind("leaveId") int leaveId,
              @Bind("managerComments") String managerComments);

  /**
   * Update the number of days after deny of the selected leave id.
   * @param leaveId the leave id
   */
  @SqlUpdate("UPDATE EMPLOYEE INNER JOIN LEAVE_HISTORY "
            + "ON EMPLOYEE.EMP_ID = LEAVE_HISTORY.EMP_ID "
            + "SET EMPLOYEE.EMP_LEAVE_BALANCE = EMPLOYEE.EMP_LEAVE_BALANCE + "
            + "LEAVE_HISTORY.NUMBER_OF_DAYS "
            + "WHERE LEAVE_HISTORY.LEAVE_ID= :leaveId")
  void increase(@Bind("leaveId") int leaveId);
     /**
     * close with no args is used to close the connection.
     */
=======
   * close with no args is used to close the connection.
   */
>>>>>>> 75e16b0... changes
  void close();
}

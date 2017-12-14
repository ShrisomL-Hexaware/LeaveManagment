package com.hexaware.ftp01.persistence;

import com.hexaware.ftp01.model.LeaveType;
import com.hexaware.ftp01.model.LeaveDetails;
import java.util.Date;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

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

 /**
  * return all the details of the selected employee.
  *
  * @param empId the id of the employee
  * @return the employee object
  */
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
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY WHERE EMP_ID = :empID")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails find(@Bind("empID") int empID);

  /**
  * close with no args is used to close the connection.
  */
    /**
     * @param mgrComments the id of the employee
     * @param levId the id of the Leave application
     * @param status is the status of application
     */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET"
            +
            "    LEAVE_MNGR_COMMENTS = :mgrComments, "
            +
            "    LEAVE_STATUS = :status"
            +
            "    WHERE LEAVE_ID = :levId")
    void approve(@Bind("mgrComments") String mgrComments, @Bind("status") String status, @Bind("levId") int levId);

    /**
     * @param mgrComments the id of the employee.
     * @param levId the id of the Leave application.
     * @param status is the status of application.
     */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET"
            +
            "    LEAVE_MNGR_COMMENTS = :mgrComments, "
            +
            "    LEAVE_STATUS = :status"
            +
            "    WHERE LEAVE_ID = :levId")
    void deny(@Bind("mgrComments") String mgrComments, @Bind("status") String status, @Bind("levId") int levId);

    /**
     * @param newavailLeave display new avail leave.
     * @param empId the id of the Leave application
     */
  @SqlUpdate("UPDATE EMPLOYEE SET"
            +
            " EMP_AVAIL_LEAVE_BAL = :newavailLeave "
            +
            " WHERE EMP_ID = :empId")
    void leaveBal(@Bind("newavailLeave") int newavailLeave, @Bind("empId") int empId);
     /**
     * close with no args is used to close the connection.
     */
  void close();
}


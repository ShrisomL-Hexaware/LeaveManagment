package com.hexaware.ftp01.persistence;

import com.hexaware.ftp01.model.LeaveType;
import java.util.Date;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

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
  * close with no args is used to close the connection.
  */
  void close();
}

package com.hexaware.ftp01.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import com.hexaware.ftp01.model.LeaveDetails;
import com.hexaware.ftp01.model.LeaveStatus;
import com.hexaware.ftp01.model.LeaveType;

/**
 * Mapper class to map from result set to employee object.
 */
public class LeaveDetailsMapper implements ResultSetMapper<LeaveDetails> {
   /**
   * @param idx the index
   * @param rs the resultset
   * @param ctx the context
   * @return the mapped employee object
   * @throws SQLException in case there is an error in fetching data from the resultset
   */
  public final LeaveDetails map(final int idx, final ResultSet rs, final      StatementContext ctx)
  throws SQLException {
    String s = rs.getString("LEAVE_TYPE");
    LeaveType lt = LeaveType.valueOf(s);

    String s1 = rs.getString("LEAVE_STATUS");
    LeaveStatus lt1 = LeaveStatus.valueOf(s1);

    /**
     * @return Leave Details
     */
    return new LeaveDetails(rs.getInt("LEAVE_ID"), lt, rs.getDate("START_DATE"),
                        rs.getDate("END_DATE"), rs.getInt("NUMBER_OF_DAYS"), lt1,
                        rs.getString("LEAVE_REASON"), rs.getDate("lEAVE_APPLIED_ON"), rs.getString("MANAGER_COMMENTS"),
                        rs.getInt("EMP_ID"));
  }
}

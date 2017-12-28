package com.hexaware.ftp01.util;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.ftp01.model.LeaveDetails;

/**
 * This class provides a REST interface for the leave details entity.
 */
@Path("/leavedetails")
public class LeaveDetailsRest {

  /**
   * Returns a specific leave details's details.
   * @param id the id of the employee
   * @return the leave details
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] listLeaveDetailsById(@PathParam("id") final int id) {
    final LeaveDetails[] ld = LeaveDetails.listLeaveDetailsById(id);
    if (ld == null) {
      throw new NotFoundException("No such Employee ID: " + id);
    }
    return ld;
  }
  /**
   * Returns a specific leave details's details.
   * @param empId the empId of the employee
   * @return the leave details
   */
  @GET
  @Path("{empId}/empId")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] listPendingApplication(@PathParam("empId") final int empId) {
    final LeaveDetails[] leavedetails = LeaveDetails.listPendingApplication(empId);
    if (leavedetails == null) {
      throw new NotFoundException("No such Employee ID: " + empId);
    }
    return leavedetails;
  }
}

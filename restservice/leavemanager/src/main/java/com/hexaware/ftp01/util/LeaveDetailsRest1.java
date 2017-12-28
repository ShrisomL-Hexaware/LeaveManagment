package com.hexaware.ftp01.util;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.ftp01.model.LeaveDetails;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/LeaveDetails")
public class LeaveDetailsRest1 {

   /**
   * Returns a specific Leave details.
   * @param leaveId the id of the LeaveDetails
   * @return the Leave details
   */
  @GET
  @Path("{leaveId}/LeaveDetails")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails listLeaveDetailsById(@PathParam("leaveId")final int leaveId) {
    final LeaveDetails leaveDetails = LeaveDetails.listById(leaveId);
    return leaveDetails;
  }

  /**
   * Returns a specific employee's details.
   * @param leaveId the id of the employee
   * @return the employee details
   */
  @GET
  @Path("{id}/empId")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails listById(@PathParam("leaveId") final int leaveId) {
    final LeaveDetails lev = LeaveDetails.listById(leaveId);
    if (lev == null) {
      throw new NotFoundException("No such leave ID: " + leaveId);
    }
    return lev;
  }


}
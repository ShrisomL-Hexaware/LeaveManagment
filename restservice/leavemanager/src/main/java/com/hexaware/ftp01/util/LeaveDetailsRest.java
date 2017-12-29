package com.hexaware.ftp01.util;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.hexaware.ftp01.model.LeaveDetails;
import com.hexaware.ftp01.model.ManagerResponse;
import com.hexaware.ftp01.model.LeaveStatus;


/**
 * This class provides a REST interface for the leave details entity.
 */
@Path("/leavedetails")
public class LeaveDetailsRest {

  /**
   * Returns a specific leave details's details.
   * @param id the id of the employee.
   * @return the leave details.
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
  @Path("{empId}/listpendingdetails")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] listPendingApplication(@PathParam("empId") final int empId) {
    final LeaveDetails[] leavedetails = LeaveDetails.listPendingApplication(empId);
    if (leavedetails == null) {
      throw new NotFoundException("No such Employee ID: " + empId);
    }
    return leavedetails;

  /**
   * Returns a specific leave details's details.
   * @param leaveId to select leave Id.
   * @param mr for the manager response for the employee's leave.
   * @return the leave details.
   */
  @POST
  @Path("{leaveId}/approveDeny")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String approveDeny(@PathParam("leaveId") final int leaveId, final ManagerResponse mr) {
    LeaveDetails leave = LeaveDetails.listById(leaveId);
    String result = leave.approveDeny(LeaveStatus.valueOf(mr.getStatus()), leaveId, mr.getResponse());
    return "Manager Response:" + result + "  ";
  }
}

package com.hexaware.ftp01.model;

import java.util.Objects;
import java.util.Date;

 /**
  * LeaveDetails class to store leave details.
  * @author hexware
  */
public class LeaveDetails {

  /**
   * leaveId to store leave id.
     leaveType to store leave type.
     startDate to store start date.
     endDate to store end date.
     numberOfDays to store number of days.
     leaveStatus to store leave status.
     leaveReason to store leave reason.
     leaveAppliedOn to store leave applied on.
     managerComments to store manager comments.
     empId to store employee id.
   */

  private int leaveId;
  private LeaveType leaveType;
  private Date startDate;
  private Date endDate;
  private int numberOfDays;
  private LeaveStatus leaveStatus;
  private String leaveReason;
  private Date leaveAppliedOn;
  private String managerComments;
  private int empId;

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    LeaveDetails lev = (LeaveDetails) obj;
    if (Objects.equals(leaveId, lev.leaveId)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(leaveId);
  }

  /**
   * @param argLeaveId to initialize leave id.
   * @param argLeaveType to initialize leave type.
   * @param argStartDate to initialize start date.
   * @param argEndDate to initialize end date.
   * @param argNumberOfDays to initialize number of days.
   * @param argLeaveStatus to initialize leave status.
   * @param argLeaveReason to initialize leave reason.
   * @param argLeaveAppliedOn to initialize leave applied on.
   * @param argManagerComments to initialize manager comments.
   * @param argEmpId to initialize employee id.
   */

  public LeaveDetails(final int argLeaveId, final LeaveType argLeaveType, final Date argStartDate,
                      final Date argEndDate, final int argNumberOfDays, final LeaveStatus argLeaveStatus,
                      final String argLeaveReason, final Date argLeaveAppliedOn, final String argManagerComments,
                      final int argEmpId) {
    this.leaveId = argLeaveId;
    this.leaveType = argLeaveType;
    this.startDate = argStartDate;
    this.endDate = argEndDate;
    this.numberOfDays = argNumberOfDays;
    this.leaveStatus = argLeaveStatus;
    this.leaveReason = argLeaveReason;
    this.leaveAppliedOn = argLeaveAppliedOn;
    this.managerComments = argManagerComments;
    this.empId = argEmpId;
  }

 /**
  * Gets the LeaveId.
  * @return this Leave ID.
  */
  public final int getLeaveId() {
    return leaveId;
  }
 /**
  *
  * @param argLeaveId to set leave id.
  */
  public final void setLeaveId(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }

 /**
  * Gets the LeaveType.
  * @return this Leave Type.
  */
  public final LeaveType getLeaveType() {
    return leaveType;
  }
 /**
  *
  * @param argLeaveType to set leave type.
  */
  public final void setLeaveType(final LeaveType argLeaveType) {
    this.leaveType = argLeaveType;
  }

 /**
  * Gets the StartDate.
  * @return this Start Date.
  */
  public final Date getStartDate() {
    return startDate;
  }
 /**
  *
  * @param argStartDate to set start date.
  */
  public final void setStartDate(final Date argStartDate) {
    this.startDate = argStartDate;
  }

 /**
  * Gets the EndDate.
  * @return this End Date.
  */
  public final Date getEndDate() {
    return endDate;
  }
 /**
  *
  * @param argEndDate to set end date.
  */
  public final void setEndDate(final Date argEndDate) {
    this.endDate = argEndDate;
  }

 /**
  * Gets the NumberOfDays.
  * @return this Number Of Days.
  */
  public final int getNumberOfDays() {
    return numberOfDays;
  }
 /**
  *
  * @param argNumberOfDays to set number of days.
  */
  public final void setNumberOfDays(final int argNumberOfDays) {
    this.numberOfDays = argNumberOfDays;
  }

  /**
  * Gets the LeaveStatus.
  * @return this Leave Status.
  */
  public final LeaveStatus getLeaveStatus() {
    return leaveStatus;
  }
 /**
  *
  * @param argLeaveStatus to set leave status.
  */
  public final void setLeaveStatus(final LeaveStatus argLeaveStatus) {
    this.leaveStatus = argLeaveStatus;
  }

 /**
  * Gets the LeaveReason.
  * @return this Leave Reason.
  */
  public final String getLeaveReason() {
    return leaveReason;
  }
 /**
  *
  * @param argLeaveReason to set leave reason.
  */
  public final void setLeaveReason(final String argLeaveReason) {
    this.leaveReason = argLeaveReason;
  }

 /**
  * Gets the LeaveAppliedOn.
  * @return this Leave Applied On.
  */
  public final Date getLeaveAppliedOn() {
    return leaveAppliedOn;
  }
 /**
  *
  * @param argLeaveAppliedOn to set leave applied on.
  */
  public final void setLeaveAppliedOn(final Date argLeaveAppliedOn) {
    this.leaveAppliedOn = argLeaveAppliedOn;
  }

 /**
  * Gets the ManagerComments.
  * @return this Manager Comments.
  */
  public final String getManagerComments() {
    return managerComments;
  }
 /**
  *
  * @param argManagerComments to set manager comments.
  */
  public final void setManagerComments(final String argManagerComments) {
    this.managerComments = argManagerComments;
  }

  /**
  * Gets the EmpId.
  * @return this Emp ID.
  */
  public final int getEmpId() {
    return empId;
  }
 /**
  *
  * @param argEmpId to set emp id.
  */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }
}
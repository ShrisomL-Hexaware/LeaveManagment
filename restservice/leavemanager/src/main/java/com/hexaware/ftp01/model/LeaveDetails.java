package com.hexaware.ftp01.model;

import java.util.Objects;
import java.util.Date;

import java.util.List;

import com.hexaware.ftp01.persistence.DbConnection;
import com.hexaware.ftp01.persistence.LeaveDetailsDAO;



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
    if (Objects.equals(leaveId, lev.leaveId) && Objects.equals(leaveType, lev.leaveType)
        && Objects.equals(startDate, lev.startDate) && Objects.equals(endDate, lev.endDate)
        && Objects.equals(numberOfDays, lev.numberOfDays) && Objects.equals(leaveStatus, lev.leaveStatus)
        && Objects.equals(leaveReason, lev.leaveReason) && Objects.equals(leaveAppliedOn, lev.leaveAppliedOn)
        && Objects.equals(managerComments, lev.managerComments) && Objects.equals(empId, lev.empId)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(leaveId, leaveType, startDate, endDate, numberOfDays, leaveStatus, leaveReason,
                        leaveAppliedOn, managerComments, empId);
  }
  @Override
  public final String toString() {
    return "leave id :" + leaveId + " " + "leave type :" + leaveType + " " + "start date :" + startDate + " "
      + "end date :" + endDate + " " + "number of days :" + numberOfDays + " " + "leave status :" + leaveStatus
      + " " +  "leave reason :" + leaveReason + " " + "leave applied on :" + leaveAppliedOn + " "
      + "managerComments :" + managerComments + " " + "empId :" + empId;
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
    this.startDate = new Date(argStartDate.getTime());
    this.endDate = new Date(argEndDate.getTime());
    this.numberOfDays = argNumberOfDays;
    this.leaveStatus = argLeaveStatus;
    this.leaveReason = argLeaveReason;
    this.leaveAppliedOn = new Date(argLeaveAppliedOn.getTime());
    this.managerComments = argManagerComments;
    this.empId = argEmpId;
  }
  /** 
   * The dao for leave details.
   * @return Leave details object.
   */
  public static LeaveDetailsDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDetailsDAO.class);
  }

 /**
  * The dao for leave details.
  * @return Leave details.
  */
  public static LeaveDetailsDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDetailsDAO.class);
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
  * @return this EmpID.
  */
  public final int getEmpId() {
    return empId;
  }
 /**
  *
  * @param argEmpId to set empid.
  */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }
 /**
  * list employee's leave details by id.
  * @param empId id to get employee's leave details.
  * @return LeaveDetails.
  */
  public static LeaveDetails[] listPendingApplication(final int empId) {
    List<LeaveDetails> l = dao().finds(empId);
    return l.toArray(new LeaveDetails[l.size()]);
  }

/**
   * update leave status.
   * @param leaveStatus id to update leave status.
   * @param leaveId to update leave Id.
   * @param managerComments to update Manager Comments.
   */
  public static void status(final LeaveStatus leaveStatus, final int leaveId, final String managerComments) {
    dao().update(leaveStatus, leaveId, managerComments);
  }

  /**
   * update number od days after deny.
   * @param leaveId to update leave Id.
   */
  public static void increment(final int leaveId) {
    dao().increase(leaveId);
  }
/**
 * list all leave details.
 * @param empId for emp id
 * @return all leave details
 */
  public static LeaveDetails[] listLeaveDetails(final int empId) {
    List<LeaveDetails> ls = dao().list(empId);
    return ls.toArray(new LeaveDetails[ls.size()]);
  }
/**
  * list leave details by id.
  * @param leaveId id to get employee details.
  * @return Employee
  */
  public static LeaveDetails listAll(final int leaveId) {
    return dao().fetch(leaveId);
  }
  /**
   * list leave details by id.
   * @param leaveId id to get leave details.
   * @return Employee
   */
  public static LeaveDetails listById(final int leaveId) {
    return dao().fetch(leaveId);
  }
}

 /** 
  * list pending leave details.
  * @param empId id to get employee details.
  * @return LeaveDetails array.
  */
  public static LeaveDetails[] listPendingApplication(final int empId) {
    List<LeaveDetails> l = dao().finds(empId);
    return l.toArray(new LeaveDetails[l.size()]);
  }
}

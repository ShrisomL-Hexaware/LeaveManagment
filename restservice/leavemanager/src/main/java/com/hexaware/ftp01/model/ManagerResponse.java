package com.hexaware.ftp01.model;

/**
 * ManagerResponse class to store manager response.
 * @author hexware
 */
public class ManagerResponse {
  /**
   * response to store manager comments.
   * status to store leave status.
   */
  private String response;
  private String status;

  /**
   * The default constructor.
   */
  public ManagerResponse() {
  }

  /**
   * @param argResponse to initialize response.
   * @param argStatus to initialize status.
   */
  public ManagerResponse(final String argStatus, final String argResponse) {
    this.status = argStatus;
    this.response = argResponse;
  }

 /**
  * Gets the Response.
  * @return this response.
  */
  public final String getResponse() {
    return response;
  }

 /**
  *
  * @param argResponse to set leave id.
  */
  public final void setResponse(final String argResponse) {
    this.response = argResponse;
  }

 /**
  * Gets the Status.
  * @return this status.
  */
  public final String getStatus() {
    return status;
  }

 /**
  *
  * @param argStatus to set status.
  */
  public final void setStatus(final String argStatus) {
    this.status = argStatus;
  }
}
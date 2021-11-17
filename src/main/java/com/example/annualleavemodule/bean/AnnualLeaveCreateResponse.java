package com.example.annualleavemodule.bean;

public class AnnualLeaveCreateResponse {

  private static final String DEFAULT_MESSAGE =
      "Your request has been received. Waiting for admin approval. requestId: ";

  private Long requestId;

  private String message = "";

  public AnnualLeaveCreateResponse(Long requestId) {
    this.requestId = requestId;
    this.message = String.format("%s%d", DEFAULT_MESSAGE, requestId);
  }

  public Long getRequestId() {
    return requestId;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

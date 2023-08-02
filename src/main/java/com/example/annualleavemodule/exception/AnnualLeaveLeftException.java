package com.example.annualleavemodule.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class AnnualLeaveLeftException extends Exception {

  public AnnualLeaveLeftException(Integer requestedDate) {
    super(getLocalizedErrorMessage(requestedDate));
  }

  private static String getLocalizedErrorMessage(Integer requestedDate) {
    Locale locale = Locale.getDefault();
    ResourceBundle messages = ResourceBundle.getBundle("messages", locale);

    String message = messages.getString("annual.leave.left");
    return MessageFormat.format(message, requestedDate);
  }
}

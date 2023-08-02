package com.example.annualleavemodule.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class EmployeeCreatedBeforeException extends Exception {

  public EmployeeCreatedBeforeException(String email) {
    super(getLocalizedErrorMessage(email));
  }

  private static String getLocalizedErrorMessage(String email) {
    Locale locale = Locale.getDefault();
    ResourceBundle messages = ResourceBundle.getBundle("messages", locale);

    String message = messages.getString("employee.created.before");
    return MessageFormat.format(message, email);
  }
}


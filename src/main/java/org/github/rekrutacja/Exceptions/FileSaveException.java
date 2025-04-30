package org.github.rekrutacja.Exceptions;

import java.io.IOException;

public class FileSaveException extends IOException {

  public FileSaveException(final String message) {
    super(message);
  }

  public FileSaveException(final Throwable cause) {
    super(cause);
  }

}

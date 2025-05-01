package org.github.rekrutacja.post;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties (prefix = "file")
public class FileSaveConfiguration {
  private String outputFolder;
  private String extension;

  public String getOutputFolder() {
    return outputFolder;
  }

  public void setOutputFolder(final String outputFolder) {
    this.outputFolder = outputFolder;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(final String extension) {
    this.extension = extension;
  }
}

package org.example.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
    private String message;
    private int errorCode;
    private String documentation;

    public ErrorMessage() {
    }

    public ErrorMessage(String mesage, int errorCode, String documentation) {
        this.message = mesage;
        this.errorCode = errorCode;
        this.documentation = documentation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
}

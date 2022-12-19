package ua.pasta.pasteproj.exceptions;

public class ExceptionClass {
    private String message;
    private int status;

    public ExceptionClass() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

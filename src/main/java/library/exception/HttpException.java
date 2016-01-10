package library.exception;

public class HttpException {
    private String message;
    public HttpException(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

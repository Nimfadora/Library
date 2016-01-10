package library.exception;

public class DeleteBookException extends RuntimeException {
    public DeleteBookException() {}

    public DeleteBookException(String message){
        super(message);
    }

    public DeleteBookException(Throwable cause){
        super(cause);
    }

    public DeleteBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteBookException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

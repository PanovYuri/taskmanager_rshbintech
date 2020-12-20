package board;

public class IncorrectParameter extends Exception {
    public IncorrectParameter() {
        super();
    }

    public IncorrectParameter(String message) {
        super(message);
    }

    public IncorrectParameter(String message, Throwable e) {
        super(message, e);
    }
}

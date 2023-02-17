package exeptions;

public class IllegalParamExeption extends Exception{
    public IllegalParamExeption() {
    }

    public IllegalParamExeption(String message) {
        super(message);
    }

    public IllegalParamExeption(String message, Throwable cause) {
        super(message, cause);
    }
}

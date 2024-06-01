package Excepciones;

public class InvalidIntegerException extends Exception {
    public InvalidIntegerException(String s) {
        super(s);
    }
    
    public InvalidIntegerException() {
        super("Ingrese un entero");
    }
}

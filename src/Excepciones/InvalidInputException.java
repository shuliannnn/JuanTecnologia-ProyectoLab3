package Excepciones;

public class InvalidInputException extends Exception{
    public InvalidInputException(String s){
        super(s);
    }

    public InvalidInputException(){
        super("El dato ingresado es invalido");
    }
}

package de.neonique.exception;

public class IncorrectDataFormatException extends RuntimeException{
    public IncorrectDataFormatException(Throwable e){
        super(e);
    }
    public IncorrectDataFormatException(){
        super();
    }
}

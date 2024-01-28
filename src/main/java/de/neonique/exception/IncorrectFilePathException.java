package de.neonique.exception;

public class IncorrectFilePathException extends RuntimeException{
    public IncorrectFilePathException(Throwable e){
        super(e);
    }
    public IncorrectFilePathException(){
        super();
    }

}

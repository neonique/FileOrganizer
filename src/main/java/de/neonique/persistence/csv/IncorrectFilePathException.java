package de.neonique.persistence.csv;

public class IncorrectFilePathException extends RuntimeException{
    public IncorrectFilePathException(Throwable e){
        super(e);
    }
}

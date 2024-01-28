package de.neonique.persistence.csv;

public class IncorrectDataFormatException extends RuntimeException{
    public IncorrectDataFormatException(Throwable e){
        super(e);
    }
    public IncorrectDataFormatException(){
        super();
    }
}

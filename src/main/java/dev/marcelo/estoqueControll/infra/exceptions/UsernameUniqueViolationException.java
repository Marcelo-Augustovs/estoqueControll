package dev.marcelo.estoqueControll.infra.exceptions;

public class UsernameUniqueViolationException extends RuntimeException{

    public UsernameUniqueViolationException(String message){
        super(message);

    }
}

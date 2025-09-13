package dev.marcelo.estoqueControll.infra.exceptions;

public class PasswordInvalidException extends RuntimeException{

    public PasswordInvalidException(String message){
        super(message);
    }
}

package dev.marcelo.estoqueControll.infra.exceptions;

public class ApiNotFoundException extends RuntimeException{

    public ApiNotFoundException(String message){
        super(message);
    }
}

package br.com.antonio_victor.controle_estoque.exceptions;

public class InsufficientQuantityException extends RuntimeException{
    public InsufficientQuantityException(String message) {
        super(message);
    }
}

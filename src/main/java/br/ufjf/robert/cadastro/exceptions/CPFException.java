package br.ufjf.robert.cadastro.exceptions;

public class CPFException extends Exception {
    public CPFException() {
        super("CPF inválido! Digite de forma correta.");
    }    
}

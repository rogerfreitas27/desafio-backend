package br.com.banco.exception;

public class ObjetoNotFoundException  extends RuntimeException{

    public ObjetoNotFoundException(String mensagem){
        super(mensagem);
    }
}

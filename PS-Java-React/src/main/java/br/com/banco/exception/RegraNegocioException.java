package br.com.banco.exception;

public class RegraNegocioException  extends RuntimeException{

    public RegraNegocioException(String mensagem){
        super(mensagem);
    }
}

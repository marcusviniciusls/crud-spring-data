package br.com.alura.spring.data.exception;

public class NullSalvarBanco extends RuntimeException{

    public NullSalvarBanco(String msg){
        super(msg);
    }
}

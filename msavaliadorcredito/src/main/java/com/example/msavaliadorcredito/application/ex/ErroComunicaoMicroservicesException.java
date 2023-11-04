package com.example.msavaliadorcredito.application.ex;

import lombok.Getter;

public class ErroComunicaoMicroservicesException extends Exception{

    @Getter
    private Integer status;
    public ErroComunicaoMicroservicesException(String msg, Integer status){
        super(msg);
        this.status = status;

    }
}

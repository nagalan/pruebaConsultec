// Modelo del request del Microservicio.
package com.consultec.gestioncliente.dto;

import java.io.Serializable;

public class RequestModel implements Serializable
{
    
    private static final long serialVersionUID = 2L;
    private String data;

    public String getData(){
        return data;
    }

    public void setData(String dataIn){
        data = dataIn;
    }

    public String toString(){
        return "{ data : " + null==data?"":data+ "}";
    }
}
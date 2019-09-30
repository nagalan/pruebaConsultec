// modelo del response del Microservicio.
package com.consultec.gestioncliente.dto;

import java.io.Serializable;

public class ResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Object data;
    private String codErrorInterno;
    private String messageError;

    public void responsePorBien(Object dataRes) {
        this.data = dataRes;
        this.codErrorInterno = "0";
    }

    public void responsePorMal(Object dataRes, String codErrorRes, String messageErrorRes) {
        this.data = dataRes;
        this.codErrorInterno = codErrorRes;
        this.messageError = messageErrorRes;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object dataIn) {
        data = dataIn;
    }

    public String getCodErrorInterno() {
        return codErrorInterno;
    }

    public void setCodErrorInterno(String codError) {
        this.codErrorInterno = codError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

}
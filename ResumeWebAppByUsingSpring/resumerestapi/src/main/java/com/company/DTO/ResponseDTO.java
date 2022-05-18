package com.company.DTO;

public class ResponseDTO {
    private Integer errorCode;
    private String errorMessage;
    private String succesMessage;
    private Object obj;

    private ResponseDTO() {

    }

    public static ResponseDTO of(Object obj) {
        ResponseDTO resp = new ResponseDTO();
        resp.setObj(obj);
        return resp;
    }

    public static ResponseDTO of(Object obj, String succesMessage) {
        ResponseDTO resp = new ResponseDTO();
        resp.setObj(obj);
        resp.setSuccesMessage(succesMessage);
        return resp;
    }

    public static ResponseDTO of(Object obj, Integer errorCode, String errorMessage) {
        ResponseDTO resp = new ResponseDTO();
        resp.setObj(obj);
        resp.setErrorMessage(errorMessage);
        resp.setErrorCode(errorCode);
        return resp;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccesMessage() {
        return succesMessage;
    }

    public void setSuccesMessage(String succesMessage) {
        this.succesMessage = succesMessage;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}

package mcs.mcsfinal2100005222.Domain.entities;

import java.util.Date;

public class GenericResponse<D>{
    private Date timeStamp;
    private int code;
    private String message;
    private D response;

    public GenericResponse(){}
    public GenericResponse(D response){
        this.timeStamp = new Date();
        this.response = response;
    }
    public GenericResponse(int code,D response){
        this.timeStamp = new Date();
        this.code = code;
        this.response = response;
        switch(code) {
            case 200:
                this.message = "Success";
                break;
            case 201:
                this.message = "Created";
                break;
            case 202:
                this.message = "Accepted";
                break;
            case 204:
                this.message = "No Content";
                break;
            case 400:
                this.message = "Bad Request";
                break;
            case 401:
                this.message = "Unauthorized";
                break;
            case 403:
                this.message = "Forbidden";
                break;
            case 404:
                this.message = "Not Found";
                break;
            case 500:
                this.message = "Server Error";
                break;
            case 502:
                this.message = "Bad Gateway";
                break;
            case 503:
                this.message = "Service Unavailable";
                break;
            default:
                this.message = "Unknown Error";
                break;
        }
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getResponse() {
        return response;
    }

    public void setResponse(D response) {
        this.response = response;
    }
}

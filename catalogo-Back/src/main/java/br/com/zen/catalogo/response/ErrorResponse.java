package br.com.zen.catalogo.response;

public class ErrorResponse {

    private Boolean success;
    private int statusCode;
    private String errorMessage;


    public ErrorResponse() {
    }

    public ErrorResponse(int statusCode, String errorMessage) {
        this.success = false;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "{" +
            " success='" + isSuccess() + "'" +
            ", statusCode='" + getStatusCode() + "'" +
            ", errorMessage='" + getErrorMessage() + "'" +
            "}";
    }

}
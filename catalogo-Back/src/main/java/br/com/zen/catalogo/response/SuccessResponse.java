package br.com.zen.catalogo.response;

public class SuccessResponse {

    private Boolean success;
    private int statusCode;
    private String message;

    public SuccessResponse() {
    }

    public SuccessResponse(int statusCode, String message) {
        this.success = true;
        this.statusCode = statusCode;
        this.message = message;
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

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
            " success='" + isSuccess() + "'" +
            ", statusCode='" + getStatusCode() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }

}
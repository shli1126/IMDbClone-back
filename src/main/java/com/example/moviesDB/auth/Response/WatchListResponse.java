package com.example.moviesDB.auth.Response;

public class WatchListResponse {

    String message;
    Boolean status;

    public WatchListResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public WatchListResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WatchListResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}

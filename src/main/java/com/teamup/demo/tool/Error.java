package com.teamup.demo.tool;

public class Error {
    private String status;
    private String mes;
    private String from;

    public Error(String status, String mes, String from) {
        this.status = status;
        this.mes = mes;
        this.from = from;
    }

    @Override
    public String toString() {
        return "Error{" +
                "status=" + status +
                ", mes='" + mes + '\'' +
                ", from='" + from + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}

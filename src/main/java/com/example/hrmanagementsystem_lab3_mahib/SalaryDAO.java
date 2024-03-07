package com.example.hrmanagementsystem_lab3_mahib;
public class SalaryDAO {
    private int salaryId;
    private int userId;
    private Float amount;
    private String date;
    public SalaryDAO(int salaryId, int userId, Float amount, String date){
        this.salaryId=salaryId;
        this.userId=userId;
        this.amount=amount;
        this.date=date;
    }
    public int getSalaryId() {return salaryId;}
    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

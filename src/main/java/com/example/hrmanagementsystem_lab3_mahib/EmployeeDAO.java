package com.example.hrmanagementsystem_lab3_mahib;

public class EmployeeDAO {
private int userId;
private String Name;
private String Email;

public EmployeeDAO(int userId, String Name, String Email){
    this.userId=userId;
    this.Name=Name;
    this.Email=Email;
}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

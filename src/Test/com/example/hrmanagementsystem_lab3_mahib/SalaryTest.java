package com.example.hrmanagementsystem_lab3_mahib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryTest {

    @Test
    void totalSalary() {
        AdminController x=new AdminController();
        assertEquals(x.totalSalary(5000), 60000);
    }
}
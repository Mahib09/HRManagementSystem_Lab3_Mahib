package com.example.hrmanagementsystem_lab3_mahib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryTest {

    @Test
    void totalSalary() {
        SalaryController x=new SalaryController();
        assertEquals(x.totalSalary(5000), 60000);
    }
}
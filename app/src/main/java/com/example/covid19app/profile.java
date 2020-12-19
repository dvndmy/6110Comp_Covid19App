package com.example.covid19app;

public class profile {
    private String fullname, email, medication, medicalCondition, gender;
    private int hospitalised, smoker, age, weight, securityCode;



    public profile(String fullname, String email, int hospitalised, int smoker, String medication, String medicalCondition, int age, int weight, int securityCode, String gender){
    this.fullname=fullname;
    this.email=email;
    this.hospitalised=hospitalised;
    this.smoker=smoker;
    this.medication=medication;
    this.medicalCondition=medicalCondition;
    this.age=age;
    this.weight=weight;
    this.securityCode=securityCode;
    this.gender=gender;
}

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getMedication() {
        return medication;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public int getHospitalised() {
        return hospitalised;
    }

    public int getSmoker() {
        return smoker;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public String getGender() {
        return gender;
    }
}

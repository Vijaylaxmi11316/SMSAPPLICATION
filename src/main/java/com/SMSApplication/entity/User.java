package com.SMSApplication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    // Roles: STUDENT / STAFF / ADMIN
    private String role;
    
    private String department;
    private String yearSemester;
    private int rollNumber;// used in controller/profile update
    private String dob;
    private String fatherName;
    private String address;
    private String phone;
    private String gender;
    private String designation;
    private String StaffId;
    
    

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role.toUpperCase(); // store roles in uppercase
    }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getYearSemester() { return yearSemester; }
    public void setYearSemester(String yearSemester) { this.yearSemester = yearSemester; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public int getRollNumber() { return rollNumber ; }
    public void setRollNumber(Integer rollNumber) { this.rollNumber = rollNumber;}
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

	public String getDesignation() {return designation;}
	public void setDesignation(String designation) {this.designation = designation;}
	
    public String getStaffId() {return StaffId;}
    public void setStaffId(String StaffId) {this.StaffId = StaffId;}
	
}

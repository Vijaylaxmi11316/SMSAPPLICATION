package com.SMSApplication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_requests")
public class StudentRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String studentEmail;      // relation to Student.email
    private String studentName;       // denormalised for convenience

    @Column(columnDefinition = "TEXT")
    private String body;              // request details

    private String studentComment;    // optional
    private String staffComment;      // staff response/comment

    private String status = "Waiting"; // Waiting / Approved / Rejected / Closed

    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();

    public StudentRequest() {}
    // getters/setters...

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentComment() {
		return studentComment;
	}

	public void setStudentComment(String studentComment) {
		this.studentComment = studentComment;
	}

	public String getStaffComment() {
		return staffComment;
	}

	public void setStaffComment(String staffComment) {
		this.staffComment = staffComment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.time.LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.time.LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
}

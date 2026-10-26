package com.SMSApplication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String studentEmail;

    @Column(length = 1000)
    private String body;

    private String studentComment;
    private String staffComment;

    private String status; // Waiting / Approved / Rejected / Closed
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	public String getBody() {
	    return body;
	}

	public void setBody(String body) {
	    this.body = body;
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

    // getters & setters
}

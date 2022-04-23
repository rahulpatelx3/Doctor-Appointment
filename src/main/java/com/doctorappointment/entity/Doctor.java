package com.doctorappointment.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int doctorId;
	private String doctorName;
	private String doctorEmail;
	private String doctorPassword;
	private int doctorAge;
	private String doctorAddress;
}

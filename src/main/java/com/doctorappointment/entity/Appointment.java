package com.doctorappointment.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appointmentId;
	private int doctorId;
	private int patientId;
	private String appointmentName;
	private String appointmentEmail;
	private byte appointmentAge;
	private String appointmentGender;
	private String appointmentPurpose;
	private String appointmentAddress;
	private String appointmentDateTime;
}

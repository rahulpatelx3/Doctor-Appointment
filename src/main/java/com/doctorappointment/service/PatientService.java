package com.doctorappointment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorappointment.entity.Patient;
import com.doctorappointment.repository.PatientDao;

import java.util.*;

@Service
public class PatientService {
	
	@Autowired
	private PatientDao patientDao;
	
	//get all patient
	public List<Patient> getAllPatient(){
		return patientDao.findAll();
	}
	
	
	//get patient by id
	public Optional<Patient> getPatientById(int id) {
		return patientDao.findById(id);
	}
	
	//get patient by email
	public Patient getPatientByEmail(String email) {
		return patientDao.findByPatientEmail(email);
	}
	
	//set patient
	public Patient setPatient(Patient p) {
		return patientDao.save(p);
	}
	
	//update patient
	public Patient updatePatient(Patient p,int id) {
		Optional<Patient> optional=this.patientDao.findById(id);
		Patient patient=optional.get();
		patient.setPatientName(p.getPatientName());
		patient.setPatientEmail(p.getPatientEmail());
		patient.setPatientPassword(p.getPatientPassword());
		patient.setPatientAge(p.getPatientAge());
		patient.setPatientAddress(p.getPatientAddress());
		return patient;
	}
	
	//delete patient by id
	public void deletePatientById(int id) {
		patientDao.deleteById(id);
	}
	
	//delete all patient
	public void deleteAllPatient() {
		patientDao.deleteAll();
	}
	
}

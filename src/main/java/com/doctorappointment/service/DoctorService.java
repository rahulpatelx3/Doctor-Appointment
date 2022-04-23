package com.doctorappointment.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doctorappointment.entity.Doctor;
import com.doctorappointment.entity.Patient;
import com.doctorappointment.repository.DoctorDao;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorDao doctorDao;
	
	//get all doctor
		public List<Doctor> getAllDoctor(){
			return doctorDao.findAll();
		}
		
		
		//get doctor by id
		public Optional<Doctor> getDoctorById(int id) {
			return doctorDao.findById(id);
		}
		
		//get patient by email
		public Doctor getDoctorByEmail(String email) {
			return doctorDao.findByDoctorEmail(email);
		}
		
		
		//get patient by name
		public Doctor getDoctorByName(String name) {
			return doctorDao.findByDoctorName(name);
		}
		
		
		//set doctor
		public Doctor setDoctor(Doctor d) {
			return doctorDao.save(d);
		}
		
		//update doctor
		public Doctor updateDoctor(Doctor d,int id) {
			Optional<Doctor> optional=this.doctorDao.findById(id);
			Doctor doctor=optional.get();
			doctor.setDoctorName(d.getDoctorName());
			doctor.setDoctorEmail(d.getDoctorEmail());
			doctor.setDoctorPassword(d.getDoctorPassword());
			doctor.setDoctorAge(d.getDoctorAge());
			doctor.setDoctorAddress(d.getDoctorAddress());
			return doctor;
		}
		
		//delete doctor by id
		public void deleteDoctorById(int id) {
			doctorDao.deleteById(id);
		}
		
		//delete all doctor
		public void deleteAllDoctor() {
			doctorDao.deleteAll();
		}
	
}

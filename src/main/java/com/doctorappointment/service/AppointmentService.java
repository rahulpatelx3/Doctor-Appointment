package com.doctorappointment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorappointment.entity.Appointment;
import com.doctorappointment.repository.AppointmentDao;

import java.util.*;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	//get all appointment
	public List<Appointment> getAllAppointment(){
		return appointmentDao.findAll();
	}
	
	
	//get appointment by id
	public Optional<Appointment> getAppointmentById(int id) {
		return appointmentDao.findById(id);
	}
	
	//get appointment by doctorId
		public List<Appointment> getAllAppointmentByDoctorId(int id) {
			return appointmentDao.findAllByDoctorId(id);
		}
		
	//get appointment by patientId
		public List<Appointment> getAppointmentByPatientId(int id) {
			return appointmentDao.findAllByPatientId(id);
		}	
	
	//set appointment
	public Appointment setAppointment(Appointment a) {
		return appointmentDao.save(a);
	}
	
	//update appointment
	public Appointment updatePatient(Appointment a,int id) {
		Optional<Appointment> optional=this.appointmentDao.findById(id);
		Appointment appointment=optional.get();
		appointment.setDoctorId(a.getDoctorId());
		appointment.setPatientId(a.getPatientId());
		appointment.setAppointmentName(a.getAppointmentName());
		appointment.setAppointmentEmail(a.getAppointmentEmail());
		appointment.setAppointmentAge(a.getAppointmentAge());
		appointment.setAppointmentGender(a.getAppointmentGender());
		appointment.setAppointmentPurpose(a.getAppointmentPurpose());
		appointment.setAppointmentAddress(a.getAppointmentAddress());
		return appointment;
	}
	
	//delete appointment by id
	public void deleteAppointmentById(int id) {
		appointmentDao.deleteById(id);
	}
	
	//delete all appointment
	public void deleteAllAppointment() {
		appointmentDao.deleteAll();
	}
	
}

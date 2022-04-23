package com.doctorappointment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.doctorappointment.entity.Appointment;
import com.doctorappointment.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

@RestController
@CrossOrigin(origins = {"*"})
@Slf4j
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("/appointment")
	public List<Appointment> getAllAppointment() {
		List<Appointment> list=appointmentService.getAllAppointment();
		log.info("Getting All The Appointments");
		return list;
	}
	
	@GetMapping("/appointment/{id}")
	public Appointment getAppointmentById(@PathVariable("id") int id) {
		Optional<Appointment> optional=appointmentService.getAppointmentById(id);
		Appointment a=(Appointment) optional.get();
		log.info("Getting Patient By Id "+id);
		return a;
	}
	
	@PostMapping("/appointment")
	public Appointment setAppointment(@RequestBody Appointment appointment) {
		return appointmentService.setAppointment(appointment);
	}

}

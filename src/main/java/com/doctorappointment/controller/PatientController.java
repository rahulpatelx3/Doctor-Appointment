package com.doctorappointment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.doctorappointment.entity.Patient;
import com.doctorappointment.service.PatientService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/register")
	public String patientRegisterPage() {
		return "patientRegister";
	}
	
	@PostMapping("/RT")
	public String patientRegisterForm(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("age") int age,
			@RequestParam("address") String address) {
		Patient p=new Patient();
		p.setPatientName(name);
		p.setPatientEmail(email);
		p.setPatientPassword(password);
		p.setPatientAge(age);
		p.setPatientAddress(address);
		this.patientService.setPatient(p);
		log.info("Patient Register Successfully");
		return "home";
	}

}

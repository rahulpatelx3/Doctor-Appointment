package com.doctorappointment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.doctorappointment.entity.Doctor;
import com.doctorappointment.service.DoctorService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/register")
	public String doctorRegisterPage() {
		log.info("Doctor Registration");
		return "doctorRegister";
	}
	
	@PostMapping("/RT")
	public String doctorRegisterForm(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("age") int age,
			@RequestParam("address") String address) {
		Doctor d=new Doctor();
		d.setDoctorName(name);
		d.setDoctorEmail(email);
		d.setDoctorPassword(password);
		d.setDoctorAge(age);
		d.setDoctorAddress(address);
		this.doctorService.setDoctor(d);
		log.info("Doctor Register Successfully");
		return "home";
	}

}

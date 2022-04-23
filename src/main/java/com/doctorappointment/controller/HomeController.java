package com.doctorappointment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.doctorappointment.entity.*;
import com.doctorappointment.service.*;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

@Controller
@Slf4j
public class HomeController {
	
	private boolean pLogin=false;
	private boolean dLogin=false;
	private String email;
	private String password;
	Doctor d=null;
	Patient p=null;
	List<Appointment> a=null;
	
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	@RequestMapping("/")
	public String index(Model m) {
		m.addAttribute("patient",p);
		m.addAttribute("doctor",d);
		a=this.appointmentService.getAllAppointment();
		m.addAttribute("appointment",a);
		if(this.pLogin==true) {
			log.warn("Patient Already Loggedin");

			a=appointmentService.getAppointmentByPatientId(p.getPatientId());
			
			HashMap<Appointment,String> h=new HashMap<>();
			for(Appointment ap : a) {
				Doctor d=this.doctorService.getDoctorById(ap.getDoctorId()).get();
				h.put(ap,d.getDoctorName());
			}
			m.addAttribute("appointment",h);
			
			return "patient/patientDashboard";
		}
		else if(this.dLogin==true) {
			log.warn("Doctor Already Loggedin");
			return "doctor/doctorDashboard";
		}
		else {
			return "home";
		}
	}
	
	
	@PostMapping("/login")
	public String loginForm(@RequestParam("email") String email,
			@RequestParam("password") String password,Model m) {
		
		p=patientService.getPatientByEmail(email);
		d=doctorService.getDoctorByEmail(email);
		
		if(p==null && d==null) {
			log.error("No such user found !!!");
			m.addAttribute("emailHelp","Email does not exist Register First...");
			return "home";
		}
		else if(p!=null) {
			if(p.getPatientEmail().equals(email) && p.getPatientPassword().equals(password)) {
				this.email=email;
				this.password=password;
				log.info("Patient login credentials matched");
				this.pLogin=true;
				a=appointmentService.getAppointmentByPatientId(p.getPatientId());
				
				HashMap<Appointment,String> h=new HashMap<>();
				for(Appointment ap : a) {
					Doctor d=this.doctorService.getDoctorById(ap.getDoctorId()).get();
					h.put(ap,d.getDoctorName());
				}
				System.out.println(h);
				m.addAttribute("patient",p);
				//m.addAttribute("appointment",a);
				m.addAttribute("appointment",h);
				return "patient/patientDashboard";
			}
			else {
				log.info("Patient login credentials not matched");
				m.addAttribute("passwordHelp","Patient Password is incorrect...");
				return "home";
			}
		}
		else if(d!=null) {
			if(d.getDoctorEmail().equals(email) && d.getDoctorPassword().equals(password)) {
				this.email=email;
				this.password=password;
				log.info("Doctor login credentials matched");
				this.dLogin=true;
				a=appointmentService.getAllAppointmentByDoctorId(d.getDoctorId());
				m.addAttribute("doctor",d);
				m.addAttribute("patient",p);
				m.addAttribute("appointment",a);
				return "doctor/doctorDashboard";
			}
			else {
				log.info("Doctor login credentials not matched");
				m.addAttribute("passwordHelp","Doctor Password is incorrect...");
				return "home";
			}
		}
		else {
			log.error("!!! Exception !!!");
			return "home";
		}
	}
	
	@PostMapping("/editAppointmentBtn")
	public String editAppointmentPage(@RequestParam("appointmentId") int appointmentId,Model m){
		List<Doctor> list = this.doctorService.getAllDoctor();
		m.addAttribute("patient",p);
		m.addAttribute("doctor",list);
		m.addAttribute("appointment",a);
		Optional<Appointment> optional=this.appointmentService.getAppointmentById(appointmentId);
		Appointment app=optional.get();
		m.addAttribute("editAppo",app);
		log.debug("Appointment Edit Page");
		//System.out.println(app);
		return "patient/editAppointment";
	}
	
	@PostMapping("/editAppointment")
	public String editAppointmentForm(@RequestParam("appointmentId") int appointmentId,
			@RequestParam("pid") int pid,
			@RequestParam("doctorName") String doctorName,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("age") byte age,
			@RequestParam("gender") String gender,
			@RequestParam("purpose") String purpose,
			@RequestParam("dateTime") String dateTime,
			@RequestParam("address") String address,
			Model m) {
		Doctor did=this.doctorService.getDoctorByName(doctorName);
		Optional<Appointment> optional=this.appointmentService.getAppointmentById(appointmentId);
		Appointment app=optional.get();
		app.setPatientId(pid);
		app.setDoctorId(did.getDoctorId());
		app.setAppointmentName(name);
		app.setAppointmentEmail(email);
		app.setAppointmentAge(age);
		app.setAppointmentGender(gender);
		app.setAppointmentPurpose(purpose);
		app.setAppointmentDateTime(dateTime);
		app.setAppointmentAddress(address);
		this.appointmentService.setAppointment(app);
		
		//test
		a=this.appointmentService.getAppointmentByPatientId(p.getPatientId());
		
		HashMap<Appointment,String> h=new HashMap<>();
		for(Appointment ap : a) {
			Doctor d=this.doctorService.getDoctorById(ap.getDoctorId()).get();
			h.put(ap,d.getDoctorName());
		}
		m.addAttribute("patient",p);
		m.addAttribute("doctor",d);
		m.addAttribute("appointment",h);
		log.info("Appointment Edit Form Submission");
		//System.out.println(app);
		return "patient/patientDashboard";
	}
	
	@PostMapping("/deleteAppointment")
	public String deleteAppointmentForm(@RequestParam("appointmentId") int appointmentId,Model m) {
		this.appointmentService.deleteAppointmentById(appointmentId);
		
		//test
		a=this.appointmentService.getAppointmentByPatientId(p.getPatientId());
		
		m.addAttribute("patient",p);
		m.addAttribute("doctor",d);
		HashMap<Appointment,String> h=new HashMap<>();
		for(Appointment ap : a) {
			Doctor d=this.doctorService.getDoctorById(ap.getDoctorId()).get();
			h.put(ap,d.getDoctorName());
		}
		m.addAttribute("appointment",h);
		log.warn("Appointment Deleted... Id = "+appointmentId);
		return "patient/patientDashboard";
	}
	
	
	@PostMapping("/treatmentDone")
	public String appointmentDone(@RequestParam("appointmentId") int appointmentId,Model m) {
		this.appointmentService.deleteAppointmentById(appointmentId);
		//test
		a=this.appointmentService.getAllAppointmentByDoctorId(d.getDoctorId());
		m.addAttribute("patient",p);
		m.addAttribute("doctor",d);
		m.addAttribute("appointment",a);
		log.warn("Treatment Done... Appointment Id = "+appointmentId);
		return "doctor/doctorDashboard";
	}
	
	
	@GetMapping("/addAppointment")
	public String addAppointmentPage(Model m){
		List<Doctor> list = this.doctorService.getAllDoctor();
		m.addAttribute("patient",p);
		m.addAttribute("doctor",list);
		m.addAttribute("appointment",a);
		log.debug("New Appointment Page");
		if(this.pLogin==true) {
			log.warn("Patient Already Loggedin");
			return "patient/addAppointment";
		}
		else {
			return "home";
		}
	}
	
	@PostMapping("/addAppointment")
	public String addAppointmentForm(@RequestParam("pid") int pid,
			@RequestParam("doctorName") String doctorName,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("age") byte age,
			@RequestParam("gender") String gender,
			@RequestParam("purpose") String purpose,
			@RequestParam("dateTime") String dateTime,
			@RequestParam("address") String address,
			Model m) {
		Doctor did=this.doctorService.getDoctorByName(doctorName);
		Appointment app=new Appointment();
		app.setPatientId(pid);
		app.setDoctorId(did.getDoctorId());
		app.setAppointmentName(name);
		app.setAppointmentEmail(email);
		app.setAppointmentAge(age);
		app.setAppointmentGender(gender);
		app.setAppointmentPurpose(purpose);
		app.setAppointmentDateTime(dateTime);
		app.setAppointmentAddress(address);
		this.appointmentService.setAppointment(app);
		//test
		a=this.appointmentService.getAppointmentByPatientId(p.getPatientId());
		m.addAttribute("patient",p);
		m.addAttribute("doctor",d);
		HashMap<Appointment,String> h=new HashMap<>();
		for(Appointment ap : a) {
			Doctor d=this.doctorService.getDoctorById(ap.getDoctorId()).get();
			h.put(ap,d.getDoctorName());
		}
		m.addAttribute("appointment",h);
		log.info("New Appointment Added");
		//System.out.println(app);
		return "patient/patientDashboard";
	}
	
	@RequestMapping("/*")
	public String defaultMethod() {
		log.error("Page Not Found");
		return "error";
	}
		
	@GetMapping("/logout")
	public String logoutPage() {
		log.info("Logout Successfully");
		this.pLogin=false;
		this.dLogin=false;
		log.error("Logout Successfully !!!!!!!!!!!!!");
		return "home";
	}
}

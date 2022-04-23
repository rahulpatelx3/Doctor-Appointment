package com.doctorappointment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.doctorappointment.entity.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {
	public Patient findByPatientEmail(String patientEmail);
}

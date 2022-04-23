package com.doctorappointment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.doctorappointment.entity.Doctor;
import com.doctorappointment.entity.Patient;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, Integer> {
	public Doctor findByDoctorEmail(String doctorEmail);
	public Doctor findByDoctorName(String doctorName);
}

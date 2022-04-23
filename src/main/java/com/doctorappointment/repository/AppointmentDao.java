package com.doctorappointment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.doctorappointment.entity.Appointment;
import java.util.*;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Integer> {
	public List<Appointment> findAllByDoctorId(int doctorId);
	public List<Appointment> findAllByPatientId(int patientId);
}

window.history.forward();
function noBack() {
	window.history.forward();
}


//logout
let logout=document.getElementById("logout");
logout.addEventListener("click",() => {

	alert("Logout Successfully");
});


//treatmentDone
let treatmentDone=document.getElementById("treatmentDone");
treatmentDone.addEventListener("click",() => {
	alert('Treatment Done');
});


//deleteAppointment
let deleteAppointment=document.getElementById("deleteAppointment");
deleteAppointment.addEventListener("click",() => {
	alert('Appointment, Deleted Successfully');
});


//addAppointment
let addAppointment=document.getElementById("addAppointment");
addAppointment.addEventListener("click",() => {
	alert('Appointment, Added Successfully');
});


//editAppointment
let editAppointment=document.getElementById("editAppointment");
editAppointment.addEventListener("click",() => {
	alert('Appointment, Edited Successfully');
});


//patientRegister
let patientRegister=document.getElementById("patientRegister");
patientRegister.addEventListener("click",() => {
	alert('Patient, Registered Successfully');
});


//doctorRegister
let doctorRegister=document.getElementById("doctorRegister");
doctorRegister.addEventListener("click",() => {
	alert('Doctor, Registered Successfully');
});
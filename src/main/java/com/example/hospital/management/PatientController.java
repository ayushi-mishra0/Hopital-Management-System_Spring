package com.example.hospital.management;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

import java.security.Key;

@RestController
public class PatientController {
  HashMap<Integer,Patient> patientDb = new HashMap<>();
@PostMapping("/addPatient")
    public String addPatient(@RequestParam("patientId")Integer patientId,@RequestParam("name")String name,
                             @RequestParam("age")Integer age,@RequestParam("disease")String disease){

    Patient patient = new Patient(patientId,name,age,disease);
    patientDb.put(patientId,patient);

    return "Patient added successfully";
}
@PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient){

    int key = patient.getPatientId();

    patientDb.put(key,patient);
    return "Patient added via Request Body";
}

@GetMapping("/getPatientInfo")
    public Patient getPatient(@RequestParam("patientId")Integer patientId){
    Patient patient = patientDb.get(patientId);
    return patient;
}
@GetMapping("/getAllPatients")
    public List<Patient> getAllPatients(){
    List<Patient> patients = new ArrayList<>();
    for(Patient p : patientDb.values()){
        patients.add(p);

    }
    return patients;
}
@GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name")String name){
    for(Patient p:patientDb.values()){
        if(p.getName()==name){
            return p;
        }
    }
    return null;
}
@GetMapping("/getPatientsListGreaterThanAge")
    public List<Patient> getPatientGreaterThanAge(@RequestParam("age")Integer age){
    List<Patient> patients = new ArrayList<>();
    for(Patient p : patientDb.values()){
        if(p.getAge()>age){
            patients.add(p);
        }
    }
    return patients;
}
}

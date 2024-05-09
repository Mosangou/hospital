package com.mo;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.mo.entities.Patient;
import com.mo.repos.PatientRepository;

@SpringBootTest
class HopitalApplicationTests {
    
	@Autowired 
	public PatientRepository patientRepository;
	
	@Test
	public void testAjouterPatient() {
		
		patientRepository.save(new Patient("Sahada",new Date(),false,54));
		patientRepository.save(new Patient("Nourane",new Date(),false,4322));
		patientRepository.save(new Patient("Souweba",new Date(),false,545));
		patientRepository.save(new Patient("Ibrahim",new Date(),false,345));
		patientRepository.save(new Patient("Fadyle",new Date(),false,4322));
		patientRepository.save(new Patient("Ismail",new Date(),false,365));
         
    }
	
	
	
}

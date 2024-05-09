package com.mo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mo.entities.Patient;
import com.mo.repos.PatientRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
public class PatientController {
    
	@Autowired
	private PatientRepository patientRepository;
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/acceuil")
	  public String maPage(Model model,
			  @RequestParam(name="page",defaultValue="0") int p,
			  @RequestParam(name="size",defaultValue="4") int s,
	          @RequestParam(name="keyword",defaultValue="") String kw){
		Page<Patient> pagePatients=patientRepository.findByNomContains(kw,PageRequest.of(p,s));
		model.addAttribute("listPatients",pagePatients.getContent());
		model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage",p);
		model.addAttribute("keyword", kw);
	    return "patients";
	  }
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/delete")
	public String delete(Long id,String keyword,int page) {
		patientRepository.deleteById(id);
		return "redirect:/acceuil?page="+page+"&keyword="+keyword;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/formPatients")
	public String formPatient( Model model) {
		model.addAttribute("patient",new Patient());
		return "formPatients";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/savePatient")
	public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "formPatients";
		}
		patientRepository.save(patient);
		return "redirect:/acceuil?keyword="+patient.getNom();
	}
	
	
	
	@GetMapping("/admin/editPatient")
	@PreAuthorize("hasRole('ADMIN')")
	public String editPatient(Model model, @RequestParam(name= "id") Long id) {
		Patient patient = patientRepository.findById(id).get();
		model.addAttribute("patient",patient);
			return "editPatient";
	}

}

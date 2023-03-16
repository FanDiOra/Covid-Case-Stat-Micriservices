package com.agrosofttechnologies.covidcaseregistration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrosofttechnologies.covidcaseregistration.bean.ResponseError;
import com.agrosofttechnologies.covidcaseregistration.entities.Address;
import com.agrosofttechnologies.covidcaseregistration.entities.CovidCase;
import com.agrosofttechnologies.covidcaseregistration.repository.CovidCaseRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping({"/fd-etu/agrosoft/covid/cases","/agrosoft/covid/cases"})
public class CovidCaseRegistrationController {

	@Autowired
	CovidCaseRepository repository;
	
	@Autowired
	private ResponseError errorResponse;
	
	@PostMapping
	public ResponseEntity<Object> createCovidCase (@Valid @RequestBody CovidCase covidCase, 
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				String description = result.getAllErrors().get(0).getDefaultMessage();
				errorResponse.prepareBadRequestResponse(description);
				
				return ResponseEntity.badRequest().body(errorResponse);
			}
			
			Address address = covidCase.getAddress();
			address.setCovidCase(covidCase);
			CovidCase createdCovidCase = repository.save(covidCase);
			
			HashMap<String, Object> successResponse = new HashMap<>();
			successResponse.put("caseID", createdCovidCase.getCaseID());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
			
		} catch (Exception e) {
			errorResponse.prepareInternalServerErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> updateCovidCase (@Valid @RequestBody CovidCase covidCase, 
			BindingResult result) {
		try {
			if(covidCase.getCaseID() == null) {
				String description = "Case ID does is required !";
				errorResponse.prepareBadRequestResponse(description);
				
				return ResponseEntity.badRequest().body(errorResponse);
			}
			
			if (result.hasErrors()) {
				String description = result.getAllErrors().get(0).getDefaultMessage();
				errorResponse.prepareBadRequestResponse(description);
				
				return ResponseEntity.badRequest().body(errorResponse);
			}
			
			Optional<CovidCase> optional = repository.findById(covidCase.getCaseID()); 
			
			if (optional.isPresent()) {
				CovidCase updateCovidCase = optional.get();
					updateCovidCase.setSource(covidCase.getSource());
				    updateCovidCase.setCaseType(covidCase.getCaseType());
				    updateCovidCase.setFirstName(covidCase.getFirstName());
				    updateCovidCase.setLastName(covidCase.getLastName());
				    updateCovidCase.setPhone(covidCase.getPhone());
				    updateCovidCase.setEmail(covidCase.getEmail());
				    updateCovidCase.setDateOfBirth(covidCase.getDateOfBirth());
				    updateCovidCase.setNationalID(covidCase.getNationalID());
				    updateCovidCase.setNationalIDType(covidCase.getNationalIDType());
			    
			    Address updateAddress = updateCovidCase.getAddress();
			        updateAddress.setCity(covidCase.getAddress().getCity());
			        updateAddress.setStreet(covidCase.getAddress().getStreet());
			        updateAddress.setState(covidCase.getAddress().getState());
			        updateAddress.setPostal(covidCase.getAddress().getPostal());
			        updateAddress.setCountry(covidCase.getAddress().getCountry()); 
			          
			    updateCovidCase.setAddress(updateAddress);
			    
				CovidCase covidCaseUpdated = repository.save(updateCovidCase);
				HashMap<String, Object> successResponse = new HashMap<>();
				successResponse.put("caseID", covidCaseUpdated.getCaseID());
				
				return ResponseEntity.ok().body(successResponse);
			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

		} catch (Exception e) {
			errorResponse.prepareInternalServerErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@GetMapping("/{nationalID}")
	public ResponseEntity<Object> getCovidCasesByNationalID (@PathVariable("nationalID") String nationalID) {
  
	  try { 
		  List<CovidCase> listCovidCases = repository.findByNationalID(nationalID); 
		  
		  if (listCovidCases.isEmpty()) {
			  errorResponse.prepareNotFoundResponse("NationalID not exist !");
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		  return ResponseEntity.status(HttpStatus.OK).body(listCovidCases); 
	  } catch (Exception e) {
		  errorResponse.prepareInternalServerErrorResponse(e.getMessage());
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	  }
	}
	 
}

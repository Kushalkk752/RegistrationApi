package com.example.controller;


import com.example.entity.Registration;
import com.example.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.payload.RegistrationDto;

import java.util.List;


@RequestMapping("/api/v1/registration")
@RestController
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<?> createRegistration(
            @Valid @RequestBody RegistrationDto registrationDto,
            BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
        }
        RegistrationDto regDto = registrationService.createReg(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
        List<RegistrationDto> regDtos = registrationService.getAllRegs();
        return new ResponseEntity<>(regDtos,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam Long id){
        registrationService.delete(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> update(
            @PathVariable Long id,
            @RequestBody Registration registration){
        Registration reg = registrationService.update(id,registration);
        return new ResponseEntity<>(reg,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegById(
            @PathVariable Long id){
        RegistrationDto registrationDto = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(registrationDto,HttpStatus.OK);
    }
}

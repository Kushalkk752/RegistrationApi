package com.example.service;


import com.example.entity.Registration;
import com.example.exception.ResourceNotFoundException;
import com.example.payload.RegistrationDto;
import com.example.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public RegistrationDto createReg(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration savedReg = registrationRepository.save(registration);
        RegistrationDto regDto = mapToDto(savedReg);
        return regDto;
    }

    private RegistrationDto mapToDto(Registration savedReg) {
        RegistrationDto registrationDto = modelMapper.map(savedReg,RegistrationDto.class);
        return registrationDto;
    }

    private Registration mapToEntity(RegistrationDto registrationDto) {
        Registration registration = modelMapper.map(registrationDto,Registration.class);
        return registration;
    }

    public List<RegistrationDto> getAllRegs() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> regDtos = registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return regDtos;
    }


    public void delete(Long id) {
        registrationRepository.deleteById(id);
    }

    public Registration update(Long id, Registration registration) {
        Registration reg = registrationRepository.findById(id).get();
        reg.setFirstName(registration.getFirstName());
        reg.setEmail(registration.getEmail());
        reg.setMobile(registration.getMobile());
        Registration savedReg = registrationRepository.save(reg);
        return savedReg;
    }

    public RegistrationDto getRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Resource Not Found")
        );
        return mapToDto(registration);
    }
}

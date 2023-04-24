package com.taw.polybank.service;

import com.taw.polybank.dao.EmployeeRepository;
import com.taw.polybank.dto.EmployeeDTO;
import com.taw.polybank.entity.ClientEntity;
import com.taw.polybank.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> findAllClients(){
        List <EmployeeEntity> employeeEntityList= employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (EmployeeEntity employee: employeeEntityList) {
            employeeDTOList.add(employee.toDTO());
        }
        return employeeDTOList;
    }

    public EmployeeDTO findById(Integer employeeId) {
        EmployeeEntity employeeEntity = this.employeeRepository.findById(employeeId).orElse(null);

        if (employeeEntity != null) {
            return employeeEntity.toDTO();
        }

        return null;
    }

    public List<EmployeeDTO> findEmployeeWithMinimumChats() {
        return this.listToDTO(employeeRepository.findEmployeeWithMinimumChats());
    }

    protected List<EmployeeDTO> listToDTO(List<EmployeeEntity> employeeEntityList) {
        ArrayList employeeList = new ArrayList<EmployeeDTO>();
        employeeEntityList.forEach((final EmployeeEntity employeeEntity) -> employeeList.add(employeeEntity.toDTO()));

        return employeeList;
    }
}

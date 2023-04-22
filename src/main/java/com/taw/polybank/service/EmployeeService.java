package com.taw.polybank.service;

import com.taw.polybank.dao.EmployeeRepository;
import com.taw.polybank.dto.Employee;
import com.taw.polybank.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee findById(Integer employeeId) {
        EmployeeEntity employeeEntity = this.employeeRepository.findById(employeeId).orElse(null);

        if (employeeEntity != null) {
            return employeeEntity.toDTO();
        }

        return null;
    }

    public List<Employee> findEmployeeWithMinimumChats() {
        return this.listToDTO(employeeRepository.findEmployeeWithMinimumChats());
    }

    protected List<Employee> listToDTO (List<EmployeeEntity> employeeEntityList) {
        ArrayList employeeList = new ArrayList<Employee>();
        employeeEntityList.forEach((final EmployeeEntity employeeEntity) -> employeeList.add(employeeEntity.toDTO()));

        return employeeList;
    }
}

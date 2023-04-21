package com.taw.polybank.dao;

import com.taw.polybank.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query("select e from EmployeeDTO e where e.type = 'manager'")
    public List<EmployeeEntity> findAllManagers();

    @Query("select employee from EmployeeDTO employee where employee.dni like :dni")
    Optional<EmployeeEntity> findByDNI(@Param("dni") String dni);

    @Query("select employee from EmployeeDTO employee order by (select count(r) from RequestDTO r where r.employeeByEmployeeId = employee) asc")
    List<EmployeeEntity> findEmployeeWithMinimmumRequests();

}

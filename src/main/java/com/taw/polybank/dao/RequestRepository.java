package com.taw.polybank.dao;

import com.taw.polybank.entity.BankAccountEntity;
import com.taw.polybank.entity.EmployeeEntity;
import com.taw.polybank.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
    List<RequestEntity> findBySolvedAndAndEmployeeByEmployeeId(boolean solved, EmployeeEntity employee);

    List<RequestEntity> findByBankAccountByBankAccountIdAndSolved(BankAccountEntity bankAccount, boolean b);
}

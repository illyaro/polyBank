package com.taw.polybank.dao;

import com.taw.polybank.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    @Query("select c from CompanyDTO c where c.bankAccountByBankAccountId.clientByClientId.id = :id")
    CompanyEntity findCompanyRepresentedByClient(@Param("id") int id);

    @Query("select co from CompanyDTO co join co.bankAccountByBankAccountId bc join bc.authorizedAccountsById auth join auth.clientByClientId cli where cli.id = :id")
    CompanyEntity findCompanyRepresentedByClientUsingAuthAcc(@Param("id") int id);
}

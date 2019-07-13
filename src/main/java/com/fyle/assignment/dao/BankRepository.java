package com.fyle.assignment.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fyle.assignment.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query(nativeQuery = true, value = "select bank from bank where id = ?1")
    public Bank getBankByBankId(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update bank set delete_flag = true where id=?1")
    public void softDeleteBankDetails(Long id);

   // @Query(nativeQuery = true, value = "select bank from bank where ifsc=?1")
    @Query("select Bank from #{#entityName} Bank where ifsc=?1")
    public Bank getFetchBankDetailsByIFSC(String ifsc);

    @Query(nativeQuery = true, value = "select * from  Bank where bank_name=?1 and address->>'city'=?2 limit ?3 offset ?4")
   // @Query("select Bank from #{#entityName} Bank where bankName=?1 and address->>'city' = ?2")
    public List<Bank> getBankDetailsByTwoParam(String bankName, String city, Long limit, Long offset);

    

}

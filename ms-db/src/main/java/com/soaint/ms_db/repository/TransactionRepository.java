package com.soaint.ms_db.repository;

import com.soaint.ms_db.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByReference(BigDecimal reference);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Transaction t SET t.status = :status WHERE t.id = :id and t.reference = :reference")
    int updateStatus(@Param("id") Long id, @Param("reference") BigDecimal reference, @Param("status") String status);
}

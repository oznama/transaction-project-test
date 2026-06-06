package com.soaint.ms_db.mapper;

import com.soaint.ms_db.dto.TransactionRequest;
import com.soaint.ms_db.dto.TransactionResponse;
import com.soaint.ms_db.dto.TransactionsResponse;
import com.soaint.ms_db.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toEntity(TransactionRequest transactionRequest);

    @Mapping(source = "operation", target = "operacion")
    @Mapping(source = "status", target = "estatus")
    @Mapping(source = "reference", target = "referencia")
    TransactionResponse toDto(Transaction transaction);

    TransactionsResponse toTransactionsResponse(Transaction transaction);
}

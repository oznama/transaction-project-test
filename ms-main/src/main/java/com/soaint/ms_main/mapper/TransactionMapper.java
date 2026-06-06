package com.soaint.ms_main.mapper;

import com.soaint.ms_main.dto.TransactionRequest;
import com.soaint.ms_main.feing.client.dto.TransactionClientRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "operacion", target = "operation")
    @Mapping(source = "importe", target = "amount")
    @Mapping(source = "cliente", target = "customer")
    @Mapping(source = "secreto", target = "secret")
    TransactionClientRequest toClientRequest(TransactionRequest transactionRequest);
}

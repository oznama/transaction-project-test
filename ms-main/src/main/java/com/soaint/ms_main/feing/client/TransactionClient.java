package com.soaint.ms_main.feing.client;

import com.soaint.ms_main.dto.GenericResponse;
import com.soaint.ms_main.dto.TransactionResponse;
import com.soaint.ms_main.dto.TransactionUpdateStatusRequest;
import com.soaint.ms_main.dto.TransactionsResponse;
import com.soaint.ms_main.feing.client.dto.TransactionClientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-db", url = "http://localhost:8082/ms-db/api/v1/transactions")
public interface TransactionClient {

    @PostMapping
    TransactionResponse createPost(TransactionClientRequest transactionRequest);

    @PatchMapping
    GenericResponse updateTransactionStatus(TransactionUpdateStatusRequest updateStatusRequest);

    @GetMapping
    List<TransactionsResponse> getTransactions(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort
    );
}

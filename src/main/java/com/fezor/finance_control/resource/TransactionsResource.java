package com.fezor.finance_control.resource;

import com.fezor.finance_control.entity.Transaction;
import com.fezor.finance_control.service.TransactionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.util.List;

@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionsResource {
    @Inject
    TransactionService service;

    @GET
    public List<Transaction> list() {
        return service.listAll();
    }

    @POST
    public Transaction create(Transaction transaction){
        return service.create(transaction);
    }

    @GET
    @Path("/balance")
    public BigDecimal balance() {
        return service.getBalance();
    }
}

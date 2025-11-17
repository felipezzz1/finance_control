package com.fezor.finance_control.resource;

import com.fezor.finance_control.entity.Transaction;
import com.fezor.finance_control.entity.User;
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
        User user = User.findById(transaction.user);

        if(user == null){
            throw new WebApplicationException("User not found", 404);
        }

        Transaction t = new Transaction();

        t.description = transaction.description;
        t.amount = transaction.amount;
        t.date = transaction.date;
        t.user = transaction.user;

        return service.create(t);
    }

    @GET
    @Path("/balance")
    public BigDecimal balance() {
        return service.getBalance();
    }
}

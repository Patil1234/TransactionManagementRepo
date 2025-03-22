package com.transactions.controller;

import com.transactions.model.Transaction;
import com.transactions.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<Objects> createTransaction(@RequestBody Transaction transaction) {
        transactionService.createTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-all-transaction")
    public ResponseEntity<List<Transaction>> getTransactionsByCategory() {
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Transaction>> getTransactionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(transactionService.getTransactionsByCategory(category));
    }

    @GetMapping("/total-spend")
    public ResponseEntity<Map<String, Double>> getTotalSpendPerCategory() {
        return ResponseEntity.ok(transactionService.getTotalSpendPerCategory());
    }

    @GetMapping("/monthly-average/{category}")
    public ResponseEntity<Double> getMonthlyAverageSpend(@PathVariable String category) {
        return ResponseEntity.ok(transactionService.getMonthlyAverageSpend(category));
    }

    @GetMapping("/highest-spend/{category}/{year}")
    public ResponseEntity<Double> getHighestSpend(@PathVariable String category, @PathVariable int year) {
        return ResponseEntity.ok(transactionService.getHighestSpend(category, year));
    }

    @GetMapping("/lowest-spend/{category}/{year}")
    public ResponseEntity<Double> getLowestSpend(@PathVariable String category, @PathVariable int year) {
        return ResponseEntity.ok(transactionService.getLowestSpend(category, year));
    }

    @DeleteMapping("/delete-transaction/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

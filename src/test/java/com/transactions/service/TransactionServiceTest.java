package com.transactions.service;

import com.transactions.model.Transaction;
import com.transactions.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {


    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTransactionsByCategory() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L,LocalDate.of(2023, 6, 10), 100.0, "Groceries","",""),
                new Transaction(1L,LocalDate.of(2023, 6, 10), 100.0, "Groceries","","")
        );
        when(transactionRepository.findByCategoryOrderByTransactionDateDesc("Groceries")).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactionsByCategory("Groceries");
        assertEquals(2, result.size());
        assertEquals(100.0, result.get(0).getAmount());
    }

    @Test
    void getTotalSpendPerCategory() {
        when(transactionRepository.getTotalSpendPerCategory())
                .thenReturn(Arrays.asList(new Object[]{"Groceries", 150.0}, new Object[]{"Entertainment", 200.0}));

        var totalSpend = transactionService.getTotalSpendPerCategory();
        assertEquals(150.0, totalSpend.get("Groceries"));
        assertEquals(200.0, totalSpend.get("Entertainment"));
    }

}

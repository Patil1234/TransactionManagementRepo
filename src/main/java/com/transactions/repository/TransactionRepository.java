package com.transactions.repository;

import com.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCategoryOrderByTransactionDateDesc(String category);

    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t GROUP BY t.category")
    List<Object[]> getTotalSpendPerCategory();

    @Query("SELECT AVG(t.amount) FROM Transaction t WHERE t.category = :category")
    Double getMonthlyAverageSpend(String category);

    @Query("SELECT MAX(t.amount) FROM Transaction t WHERE t.category = :category AND YEAR(t.transactionDate) = :year")
    Double getHighestSpend(String category, int year);

    @Query("SELECT MIN(t.amount) FROM Transaction t WHERE t.category = :category AND YEAR(t.transactionDate) = :year")
    Double getLowestSpend(String category, int year);

}

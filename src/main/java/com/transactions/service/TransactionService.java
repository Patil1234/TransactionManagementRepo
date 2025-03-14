package com.transactions.service;

import com.transactions.exception.ResourceNotFoundException;
import com.transactions.model.Transaction;
import com.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {


    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByCategory(String category) {

         List<Transaction> transactionList=transactionRepository.findByCategoryOrderByTransactionDateDesc(category);

         if(transactionList.isEmpty()){
             throw new ResourceNotFoundException(
                     "No transactions found for category: " + category);
         }

        return transactionList;
    }

    public Map<String, Double> getTotalSpendPerCategory() {
        return transactionRepository.getTotalSpendPerCategory()
                .stream()
                .collect(Collectors.toMap(obj -> (String) obj[0], obj -> (Double) obj[1]));
    }

    public Double getMonthlyAverageSpend(String category) {
         Double average=transactionRepository.getMonthlyAverageSpend(category);
         if(average==null){
             throw new ResourceNotFoundException(
                     "No transactions found for category: " + category);
         }
        return transactionRepository.getMonthlyAverageSpend(category);
    }

    public Double getHighestSpend(String category, int year) {
        Double highest=transactionRepository.getHighestSpend(category, year);
        if(highest==null){
            throw new ResourceNotFoundException(
                    "No transactions found for category: " + category+" and year: "+year);
        }
        return highest;
    }

    public Double getLowestSpend(String category, int year) {
        Double lowest=transactionRepository.getLowestSpend(category, year);
        if(lowest==null){
            throw new ResourceNotFoundException(
                    "No transactions found for category: " + category+" and year: "+year);
        }
        return lowest;
    }
}

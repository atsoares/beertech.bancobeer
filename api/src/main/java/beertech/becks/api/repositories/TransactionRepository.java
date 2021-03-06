package beertech.becks.api.repositories;

import beertech.becks.api.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByAccountId(Long accountId);
}

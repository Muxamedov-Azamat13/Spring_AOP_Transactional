package ru.gb.spring_aop_transactional.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.spring_aop_transactional.model.Account;

@Repository
public interface TransferRepositories extends JpaRepository<Account,Long> {
}

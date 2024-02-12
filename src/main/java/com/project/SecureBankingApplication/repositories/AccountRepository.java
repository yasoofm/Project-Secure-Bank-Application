package com.project.SecureBankingApplication.repositories;

import com.project.SecureBankingApplication.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

}

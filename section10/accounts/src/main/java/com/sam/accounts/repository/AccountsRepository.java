package com.sam.accounts.repository;

import com.sam.accounts.entity.Accounts;
import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    Optional<Accounts> findByCustomerId(Long customerId);

    // since we are trying to modify data by inbuilt method apart from primaryKey we need to have two annotations for this
    //
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}

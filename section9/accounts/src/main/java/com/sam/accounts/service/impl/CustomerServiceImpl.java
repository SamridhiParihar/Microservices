package com.sam.accounts.service.impl;

import com.sam.accounts.dto.AccountsDto;
import com.sam.accounts.dto.CardsDto;
import com.sam.accounts.dto.CustomerDetailsDto;
import com.sam.accounts.dto.LoansDto;
import com.sam.accounts.entity.Accounts;
import com.sam.accounts.entity.Customer;
import com.sam.accounts.exception.ResourceNotFoundException;
import com.sam.accounts.mapper.AccountsMapper;
import com.sam.accounts.mapper.CustomerMapper;
import com.sam.accounts.repository.AccountsRepository;
import com.sam.accounts.repository.CustomerRepository;
import com.sam.accounts.service.ICustomerService;
import com.sam.accounts.service.client.CardsFeignClient;
import com.sam.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}

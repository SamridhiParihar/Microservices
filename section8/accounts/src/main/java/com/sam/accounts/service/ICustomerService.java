package com.sam.accounts.service;

import com.sam.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    /**
     *
     * @param mobileNumber- Input Mobile Number
     * @return Customer Details based on Mobile Number Provided
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}

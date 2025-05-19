package com.sam.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer ,Account , Cards and Loans information"
)
public class CustomerDetailsDto {
    @Schema(
            description = "Name of the customer",
            example = "Samridhi Parihar"
    )
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 2,max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;


    @Schema(
            description = "Email of the customer",
            example = "samridhi_parihar@epam.com"
    )
    @NotEmpty(message = "Email can't be null or empty")
    @Email(message = "It doesn't comply email format")
    private String email;

    @Schema(
            description = "Mobile Number of the customer",
            example = "9142675141"
    )
    @Pattern(regexp ="(^$|[0-9]{10})",message ="Mobile numbers must be 10 digits" )
    private String mobileNumber;

    @Schema(
            description = "Account Details of the customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Loan Details of the customer"
    )
    private LoansDto loansDto;

    @Schema(
            description = "Card Details of the customer"
    )
    private CardsDto cardsDto;


}

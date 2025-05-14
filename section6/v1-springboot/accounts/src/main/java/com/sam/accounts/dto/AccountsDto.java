package com.sam.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number of the customer",
            example = "1234567890"
    )
    @NotEmpty(message = "AccountNumber can't be null or empty!")
    @Pattern(regexp ="(^$|[0-9]{10})",message = "Account no must be of 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of SamBank",
            example = "Savings"
    )
    @NotEmpty(message = "AccountType can't be null or empty")
    private String accountType;

    @Schema(
            description = "Branch Address of SamBank",
            example="123 New York"
    )
    @NotEmpty(message = "BranchAddress can't be null or empty")
    private String branchAddress;

}

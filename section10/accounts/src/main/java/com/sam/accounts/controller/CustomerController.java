package com.sam.accounts.controller;

import com.sam.accounts.dto.CustomerDetailsDto;
import com.sam.accounts.dto.ErrorResponseDto;
import com.sam.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api",produces ={MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = " REST API for Customer in SamBank",
        description = "REST API to fetch customer details"
)
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private ICustomerService iCustomerService;

    @Autowired
    public CustomerController(ICustomerService iCustomerService){
        this.iCustomerService = iCustomerService;
    }

    @Operation(
            summary = "Fetch Customer Details Information",
            description = "REST API to get customer details based on mobileNumber "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    // older version ( I am making changes here for the additional header to reflect but depending upon requirements we can make change to other api as well )
//    @GetMapping("fetchCustomerDetails")
//    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam
//                                                                   @Pattern(regexp ="(^$|[0-9]{10})",message ="Mobile numbers must be 10 digits" )
//                                                                   String mobileNumber
//                                                                   ){
//        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(customerDetailsDto);
//    }
    @GetMapping("fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
                                                                    @RequestHeader("sambank-correlation-id")
                                                                    String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp ="(^$|[0-9]{10})",message ="Mobile numbers must be 10 digits" )
                                                                   String mobileNumber
    ){
        logger.debug("samBank-correlation-id found: {}",correlationId);
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDetailsDto);
    }
}

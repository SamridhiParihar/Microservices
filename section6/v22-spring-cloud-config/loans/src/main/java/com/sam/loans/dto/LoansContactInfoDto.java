package com.sam.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;
import java.util.List;

@ConfigurationProperties(value = "loans")
@Getter
@Setter
public class LoansContactInfoDto{
    private String message;
    private Map<String , String> contactDetails;
    private List<String> onCallSupport;
}

package com.sam.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;
import java.util.List;

@ConfigurationProperties(value = "loans")
public record LoansContactInfoDto(String message, Map<String, String> contactDetails , List<String> onCallSupport) {
}

package com.soict.code.responses;

import lombok.Data;

@Data
public class SandboxResponse {
    private String status;
    private String output;
    private String error;
    private Double executionTime;
}

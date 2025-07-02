package com.paybank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @PreAuthorize("hasAnyRole('customer', 'default-roles-paybank', 'offline_access', 'uma_authorization', 'manage-account', 'view-profile')")
    //@PreAuthorize("hasAnyRole('default-roles-paybank')")
    @GetMapping
    public ResponseEntity<List<String>> getUserTransactions(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(List.of("Transaction 1 for " + username, "Transaction 2 for " + username));
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllTransactions() {
        return ResponseEntity.ok(List.of("Admin Transaction 1", "Admin Transaction 2"));
    }
}

package com.rca.ac.rw.ne.backend.api.controller.auth;

import com.rca.ac.rw.ne.backend.api.model.BankingModel;
import com.rca.ac.rw.ne.backend.model.Banking;
import com.rca.ac.rw.ne.backend.service.BankingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/banking")
public class BankingController {


    @Autowired
    private BankingService bankingService;

    public BankingController(BankingService bankingService){
        this.bankingService=bankingService;

    }

    @PostMapping(value="/save",produces = "application/json")
    public ResponseEntity<?> saveTransaction(@RequestBody BankingModel bankingModel) {
        try {
            Banking savedTransaction = bankingService.saveTransaction(bankingModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Transaction successful", savedTransaction));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Transaction failed: " + e.getMessage()));
        }
    }

    // Inner class for API response
    public static class ApiResponse {
        private boolean success;
        private String message;
        private Banking banking;

        public ApiResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public ApiResponse(boolean success, String message, Banking banking) {
            this.success = success;
            this.message = message;
            this.banking = banking;
        }

        // Getters and Setters


        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Banking getBanking() {
            return banking;
        }

        public void setBanking(Banking banking) {
            this.banking = banking;
        }
    }
}


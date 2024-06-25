package com.rca.ac.rw.ne.backend.api.controller.auth;


import com.rca.ac.rw.ne.backend.api.model.RegistrationModel;
import com.rca.ac.rw.ne.backend.exception.UserExistsException;
import com.rca.ac.rw.ne.backend.model.LocalUser;
import com.rca.ac.rw.ne.backend.service.UserServices;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserServices userServices;

    public AuthenticationController(UserServices userServices){
        this.userServices = userServices;
    }

    //router for registering a user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationModel registrationModel) {

        try {
            userServices.registerUser(registrationModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Customer successfully created"));
        }catch (UserExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Error creating customer: " + e.getMessage()));
        }

    }


    // Inner class for API response
    public static class ApiResponse {
        private boolean success;
        private String message;
        private LocalUser user;

        public ApiResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public ApiResponse(boolean success, String message, LocalUser user) {
            this.success = success;
            this.message = message;
            this.user = user;
        }

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

        public LocalUser getUser() {
            return user;
        }

        public void setUser(LocalUser user) {
            this.user = user;
        }
    }



}

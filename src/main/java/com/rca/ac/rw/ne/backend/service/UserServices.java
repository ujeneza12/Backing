package com.rca.ac.rw.ne.backend.service;

import com.rca.ac.rw.ne.backend.api.model.RegistrationModel;
import com.rca.ac.rw.ne.backend.exception.UserExistsException;
import com.rca.ac.rw.ne.backend.model.LocalUser;
import com.rca.ac.rw.ne.backend.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserServices {


    private  LocalUserDAO localUserDAO;

    public UserServices(LocalUserDAO localUserDAO) {
        this.localUserDAO= localUserDAO;
    }

    @Transactional
    public LocalUser registerUser( RegistrationModel registrationModel)  throws UserExistsException {

        LocalUser user= new LocalUser();
        user.setFirstname(registrationModel.getFirstname());
        user.setLastname(registrationModel.getLastname());
        user.setAccount(registrationModel.getAccount());
        user.setDob(registrationModel.getDob());
        user.setBalance(registrationModel.getBalance());
        user.setEmail(registrationModel.getEmail());
        user.setPhone(registrationModel.getPhone());
        user.setLast_update_time(registrationModel.getLast_update_time());

        return localUserDAO.save(user);

    }
}

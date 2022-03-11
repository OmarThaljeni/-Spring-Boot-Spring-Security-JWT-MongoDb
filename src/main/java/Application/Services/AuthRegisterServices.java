package Application.Services;


import Application.DataAccessObject.AuthRegisterDAO;
import Application.Models.Requests.LoginRequest;
import Application.Models.Requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class AuthRegisterServices {

    @Autowired
    AuthRegisterDAO authRegisterDAO;



    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authRegisterDAO.authenticateUser(loginRequest);
    }

    }

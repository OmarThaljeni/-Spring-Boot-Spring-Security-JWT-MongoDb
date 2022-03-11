package Application.Controllers;


import Application.Models.Requests.LoginRequest;
import Application.Models.Requests.RegisterRequest;
import Application.Services.AuthRegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/GestionAchat")
public class AuthRegisterController {


    @Autowired
    AuthRegisterServices authRegiserService;


    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authRegiserService.authenticateUser(loginRequest);
    }

}

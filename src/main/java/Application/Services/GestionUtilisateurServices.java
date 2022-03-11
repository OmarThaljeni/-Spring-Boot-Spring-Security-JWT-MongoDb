package Application.Services;


import Application.DataAccessObject.GestionUtilisateurDAO;
import Application.Models.Requests.RegisterRequest;
import Application.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;

@Service
public class GestionUtilisateurServices {

    @Autowired
    private GestionUtilisateurDAO gestionUtilisateurDAO;


    public Collection<User> RecupererlisterUsers() {
    return gestionUtilisateurDAO.RecupererlisterUsers();
    }

    public ResponseEntity<?> AjouterUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return gestionUtilisateurDAO.AjouterUser(registerRequest);
    }

    public ResponseEntity<?> ModifierUser(@PathVariable("id") String id, @RequestBody User user) {
        return gestionUtilisateurDAO.ModifierUser(id,user);
    }

    public ResponseEntity<?> SupprimerUser(@PathVariable("id") String id) {
        return this.gestionUtilisateurDAO.SupprimerUser(id);
    }











    }

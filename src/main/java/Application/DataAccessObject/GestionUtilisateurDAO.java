package Application.DataAccessObject;


import Application.Models.ERole;
import Application.Models.Requests.RegisterRequest;
import Application.Models.Role;
import Application.Models.User;
import Application.Repository.RoleRepository;
import Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class GestionUtilisateurDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    public Collection<User> RecupererlisterUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<?> AjouterUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Erreur: l'e-mail est déjà utilisé");
        }

        User user = new User(
                registerRequest.getNom(),
                registerRequest.getPrenom(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                registerRequest.getAdress(),
                registerRequest.getNumTel()
        );

        String roles1 = registerRequest.getRoles();
        Set<String> strRoles = new HashSet<>();
        strRoles.add(roles1);

        Set<Role> roles = new HashSet<>();
        if (roles1 == null) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "Demandeur":
                        Role demaRole = roleRepository.findByName(ERole.ROLE_DEMANDEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(demaRole);

                        break;
                    case "Signateur":
                        Role SignRole = roleRepository.findByName(ERole.ROLE_SIGNATEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(SignRole);
                        break;
                    case "financier":
                        Role FinaRole = roleRepository.findByName(ERole.ROLE_FINANCIER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(FinaRole);
                        break;

                    case "dGenerale":
                        Role DGeneRole = roleRepository.findByName(ERole.ROLE_DGENERALE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(DGeneRole);
                        break;

                    default:
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

    }

    public ResponseEntity<?> ModifierUser(@PathVariable("id") String id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User U = userData.get();
            U.setNom(user.getNom());
            U.setPrenom(user.getPrenom());
            U.setEmail(user.getEmail());
            U.setPassword(encoder.encode(user.getPassword()));
            U.setAdress(user.getAdress());
            U.setNumTel(user.getNumTel());
            return new ResponseEntity<>(userRepository.save(U), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> SupprimerUser(@PathVariable("id") String id) {
            try {
                userRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }









}

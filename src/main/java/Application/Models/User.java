package Application.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class User {
        @Id
        private String id;

        @NotNull
        private String nom;

        @NotNull
        private String prenom;

        @Email
        private String email;

        private String password;

        private String adress;

        private String numTel;

        @DBRef
        private Set<Role> roles = new HashSet<>();

        public User(String nom, String prenom, String email, String password, String adress, String numTel) {
                this.nom = nom;
                this.prenom = prenom;
                this.email = email;
                this.password = password;
                this.adress = adress;
                this.numTel = numTel;
        }
}

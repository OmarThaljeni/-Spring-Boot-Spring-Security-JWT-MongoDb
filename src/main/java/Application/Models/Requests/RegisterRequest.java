package Application.Models.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor @Getter @Setter
public class RegisterRequest {

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String adress;

    @Size(max = 8,min = 8)
    @NotNull
    private String numTel;


    private String roles;




}

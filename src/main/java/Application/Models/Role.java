package Application.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Role {
    private String id;
    private ERole name;

}

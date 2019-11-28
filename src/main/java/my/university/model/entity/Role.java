package my.university.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "role")
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "role")
    private String role;

    public Role(String role) {
        this.role = role;
    }



    public String getRole() {
        return role.toUpperCase();

    }
}
package my.university.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role1 = (Role) o;
        return Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
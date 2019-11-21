package my.university.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "userEntity")
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    List<ExamResultEntity> examResults;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")

    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_speciality")
    private SpecialityEntity specialityEntity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role role;


}

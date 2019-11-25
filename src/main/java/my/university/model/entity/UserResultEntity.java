package my.university.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "userResultEntity")
@Table(name = "user_results")
public class UserResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_results_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality_id")
    private SpecialityEntity specialityEntity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @Column(name = "final_mark")
    private Integer finalMark;

    @Column(name = "confirmed")
    private Boolean confirmed;

    private UserResultEntity(Builder builder) {
        setId(builder.id);
        setSpecialityEntity(builder.specialityEntity);
        setUserEntity(builder.userEntity);
        setFinalMark(builder.finalMark);
        setConfirmed(builder.confirmed);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private SpecialityEntity specialityEntity;
        private UserEntity userEntity;
        private Integer finalMark;
        private Boolean confirmed;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withSpecialityEntity(SpecialityEntity val) {
            specialityEntity = val;
            return this;
        }

        public Builder withUserEntity(UserEntity val) {
            userEntity = val;
            return this;
        }

        public Builder withFinalMark(Integer val) {
            finalMark = val;
            return this;
        }

        public Builder withConfirmed(Boolean val) {
            confirmed = val;
            return this;
        }

        public UserResultEntity build() {
            return new UserResultEntity(this);
        }
    }
}

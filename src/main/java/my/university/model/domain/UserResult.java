package my.university.model.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.university.model.entity.SpecialityEntity;
import my.university.model.entity.UserEntity;

@Data
@NoArgsConstructor
public class UserResult {

    private Integer id;

    private Speciality speciality;

    private  User user;

    private Integer finalMark;

    private Boolean confirmed;

    private UserResult(Builder builder) {
        setId(builder.id);
        setSpeciality(builder.speciality);
        setUser(builder.user);
        setFinalMark(builder.finalMark);
        setConfirmed(builder.confirmed);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private Speciality speciality;
        private User user;
        private Integer finalMark;
        private Boolean confirmed;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withSpeciality(Speciality val) {
            speciality = val;
            return this;
        }

        public Builder withUser(User val) {
            user = val;
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

        public UserResult build() {
            return new UserResult(this);
        }
    }
}

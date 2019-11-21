package my.university.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.university.model.entity.SpecialityEntity;
import my.university.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserResult {

    private Integer id;
    private SpecialityEntity specialityEntity;
    private UserEntity userEntity;
    private Integer finalMark;
    private Boolean confirmed;

    private UserResult(Builder builder) {
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

        public UserResult build() {
            return new UserResult(this);
        }
    }
}

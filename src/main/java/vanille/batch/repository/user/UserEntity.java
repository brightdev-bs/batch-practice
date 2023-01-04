package vanille.batch.repository.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vanille.batch.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Id
    private String userId;

    private String userName;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String phone;
    private String meta;

}

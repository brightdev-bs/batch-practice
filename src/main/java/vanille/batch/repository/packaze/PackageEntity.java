package vanille.batch.repository.packaze;

import lombok.*;
import vanille.batch.BaseEntity;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "package")
public class PackageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageSeq;

    private String packageName;
    private Integer count;
    private Integer period;
}

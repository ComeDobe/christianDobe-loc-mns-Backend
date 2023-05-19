import com.example.loctest.entity.DocEntity;
import com.example.loctest.entity.MaterielEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "posseder")
public class PossederEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private DocEntity doc;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private MaterielEntity materiel;

    // Constructeurs, getters, setters
}

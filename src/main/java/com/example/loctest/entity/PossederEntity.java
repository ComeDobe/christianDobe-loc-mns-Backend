import com.example.loctest.entity.DocEntity;
import com.example.loctest.entity.MaterielEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "posseder")
public class PossederEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posseder_id")
    private Integer possederId;

    @Column(name = "posseder_libelle")
    private String possederLibelle;


    @ManyToOne
    @JoinColumn(name = "doc_id")
    private DocEntity documents;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private MaterielEntity materiel;

    // Constructeurs, getters, setters
}






//import com.example.loctest.entity.DocEntity;
//import com.example.loctest.entity.MaterielEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "posseder")
//public class PossederEntity {
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "doc_id")
//    private DocEntity docId;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "materiel_id")
//    private MaterielEntity materielId;
//
//    // Constructeurs, getters, setters
//}
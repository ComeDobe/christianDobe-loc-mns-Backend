


import java.util.List;

import com.example.loctest.entity.TypeEntity;
import com.example.loctest.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("")
    public List<TypeEntity> getAllTypes() {
        return typeService.getAllTypes();
    }

    @GetMapping("/{typeId}")
    public TypeEntity getTypeById(@PathVariable int typeId) {
        return typeService.getTypeById(typeId);
    }

    @PostMapping("")
    public ResponseEntity<String> addType(@RequestBody TypeEntity type) {
        TypeEntity newType = typeService.addType(type);
        return new ResponseEntity<>("Type created successfully with id " + newType.getTypeId(), HttpStatus.OK);
    }

    @PutMapping("/{typeId}")
    public ResponseEntity<String> updateType(@PathVariable int typeId, @RequestBody TypeEntity type) {
        TypeEntity currentType = typeService.getTypeById(typeId);
        if (currentType == null) {
            return new ResponseEntity<>("Type not found", HttpStatus.NOT_FOUND);
        }
        currentType.setPret(type.getPret());
        currentType.setDateRetourReel(type.getDateRetourReel());
        typeService.updateType(currentType);
        return new ResponseEntity<>("Type updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity<String> deleteType(@PathVariable int typeId) {
        TypeEntity type = typeService.getTypeById(typeId);
        if (type == null) {
            return new ResponseEntity<>("Type not found", HttpStatus.NOT_FOUND);
        }
        typeService.deleteType(typeId);
        return new ResponseEntity<>("Type deleted successfully", HttpStatus.OK);
    }
}

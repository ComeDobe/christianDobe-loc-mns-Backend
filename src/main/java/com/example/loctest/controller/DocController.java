package com.example.loctest.controller;

import com.example.loctest.entity.DocEntity;
import com.example.loctest.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocController {

    @Autowired
    private DocService docService;

    @GetMapping("")
    public List<DocEntity> getAllDocuments() {
        return docService.getAllDocuments();
    }

    @GetMapping("/{docId}")
    public DocEntity getDocumentById(@PathVariable int docId) {
        return docService.getDocumentById(docId);
    }

    @PostMapping("")
    public DocEntity addDocument(@RequestBody DocEntity doc) {
        return docService.addDocument(doc);
    }

    @PutMapping("/{docId}")
    public DocEntity updateDocument(@PathVariable int docId, @RequestBody DocEntity doc) {
        return docService.updateDocument(docId, doc);
    }

    @DeleteMapping("/{docId}")
    public ResponseEntity<String> deleteDocument(@PathVariable int docId) {
        DocEntity doc = docService.getDocumentById(docId);
        if (doc == null) {
            return new ResponseEntity<>("Document not found", HttpStatus.NOT_FOUND);
        }
        docService.deleteDocument(docId);
        return new ResponseEntity<>("Document deleted", HttpStatus.OK);
    }
}

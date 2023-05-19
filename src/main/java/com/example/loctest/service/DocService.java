//package com.example.loctest.service;
//
//package com.example.loctest.service;
//
//import com.example.loctest.dao.DocDao;
//import com.example.loctest.entity.DocEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class DocService {
//
//    @Autowired
//    private DocDao docDao;
//
//    public List<DocEntity> getAllDocs() {
//        return docDao.findAll();
//    }
//
//    public DocEntity getDocById(int docId) {
//        return docDao.findById(docId).orElse(null);
//    }
//
//    public DocEntity addDoc(DocEntity doc) {
//        return docDao.save(doc);
//    }
//
//    public void deleteDoc(int docId) {
//        docDao.deleteById(docId);
//    }
//
//    public List<DocEntity> getAllDocuments() {
//    }
//
//    public DocEntity getDocumentById(int docId) {
//    }
//
//    public DocEntity addDocument(DocEntity doc) {
//    }
//
//    public DocEntity updateDocument(int docId, DocEntity doc) {
//    }
//
//    public void deleteDocument(int docId) {
//    }
//}




package com.example.loctest.service;

import com.example.loctest.repository.DocDao;
import com.example.loctest.entity.DocEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocService {

    @Autowired
    private DocDao docDao;

    public List<DocEntity> getAllDocs() {
        return docDao.findAll();
    }

    public DocEntity getDocById(int docId) {
        return docDao.findById(docId).orElse(null);
    }

    public DocEntity addDoc(DocEntity doc) {
        return docDao.save(doc);
    }

    public void deleteDoc(int docId) {
        docDao.deleteById(docId);
    }

    public List<DocEntity> getAllDocuments() {
        return docDao.findAll();
    }

    public DocEntity getDocumentById(int docId) {
        return docDao.findById(docId).orElse(null);
    }

    public DocEntity addDocument(DocEntity doc) {
        return docDao.save(doc);
    }

    public DocEntity updateDocument(int docId, DocEntity doc) {
        DocEntity docToUpdate = docDao.findById(docId).orElse(null);
        if (docToUpdate != null) {
            docToUpdate.setTitre(doc.getTitre());
            docToUpdate.setDescription(doc.getDescription());
            return docDao.save(docToUpdate);
        }
        return null;
    }

    public void deleteDocument(int docId) {
        docDao.deleteById(docId);
    }
}

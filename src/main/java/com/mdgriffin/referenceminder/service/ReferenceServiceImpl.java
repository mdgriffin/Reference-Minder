package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.Reference;
import com.mdgriffin.referenceminder.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    ReferenceRepository referenceRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Reference> getReferences() {
        return referenceRepository.findAll();
    }

    @Override
    public Reference saveReference(Reference reference) {
        reference.setCreatedAt(new Date());
        reference.setUpdatedAt(new Date());
        return referenceRepository.save(reference);
    }

    @Override
    public void deleteReference(String referenceId) {
        referenceRepository.deleteById(referenceId);
    }

    @Override
    public Optional<Reference> findById(String id) {
        return referenceRepository.findById(id);
    }

    @Override
    public Reference updateReference(Reference reference) {
        reference.setUpdatedAt(new Date());
        return referenceRepository.save(reference);
    }

    @Override
    public List<Reference> getReferencesTaggedWith(List<String> tags) {
        // db.getCollection('reference').find({"tags.text": {$in: ["design"] }})
        return  mongoTemplate.find(new Query(Criteria.where("tags.text").in(tags)), Reference.class);
    }

}

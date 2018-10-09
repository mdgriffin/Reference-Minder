package com.mdgriffin.referenceminder.repository;

import com.mdgriffin.referenceminder.entity.Reference;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReferenceRepository extends MongoRepository<Reference, String> {

    public List<Reference> findAll();

}

package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.Reference;
import com.mdgriffin.referenceminder.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    ReferenceRepository referenceRepository;

    @Override
    public List<Reference> getReferences() {
        return referenceRepository.findAll();
    }

}

package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.Reference;
import com.mdgriffin.referenceminder.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    ReferenceRepository referenceRepository;

    @Override
    public List<Reference> getReferences() {
        return referenceRepository.findAll();
    }

    @Override
    public Reference saveReference(Reference reference) {
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
        return referenceRepository.save(reference);
    }

}

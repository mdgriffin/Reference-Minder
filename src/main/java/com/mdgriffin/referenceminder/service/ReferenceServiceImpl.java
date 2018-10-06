package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Override
    public List<Reference> getReferences() {
        List<Reference> references = new ArrayList<>();
        references.add(new Reference(("Tom John Junior")));

        return references;
    }
}

package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.Reference;

import java.util.List;
import java.util.Optional;

public interface ReferenceService {

    List<Reference> getReferences ();

    Reference saveReference (Reference reference);

    Optional<Reference> findById (String id);

}

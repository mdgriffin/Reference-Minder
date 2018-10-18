package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.Reference;

import java.util.List;
import java.util.Optional;

public interface ReferenceService {

    List<Reference> getReferences ();

    Reference saveReference (Reference reference);

    void deleteReference (String referenceId);

    Optional<Reference> findById (String referenceId);

    Reference updateReference (Reference reference);

    List<Reference> getReferencesTaggedWith (List<String> tags);

}

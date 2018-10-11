package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.ReferenceType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReferenceTypeServiceImpl implements ReferenceTypeService {

    @Override
    public Map<String, String> getReferenceTypes() {
        Map<String, String> types = new HashMap<>();


        Arrays.stream(ReferenceType.values()).forEach(referenceType -> {
            types.put(referenceType.name(), referenceType.getDisplayName());
        });

        return types;
    }

}

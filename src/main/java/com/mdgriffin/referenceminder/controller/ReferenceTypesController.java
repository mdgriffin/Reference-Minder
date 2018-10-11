package com.mdgriffin.referenceminder.controller;

import com.mdgriffin.referenceminder.entity.Reference;
import com.mdgriffin.referenceminder.service.ReferenceService;
import com.mdgriffin.referenceminder.service.ReferenceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReferenceTypesController {

    @Autowired
    private ReferenceTypeService referenceTypeService;

    @GetMapping("/reference-types")
    public Map<String, String> getReferenceTypes(){
        return referenceTypeService.getReferenceTypes();
    }

}

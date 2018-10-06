package com.mdgriffin.referenceminder.controller;

import com.mdgriffin.referenceminder.entity.Reference;
import com.mdgriffin.referenceminder.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    @GetMapping("/references")
    public List<Reference> getReferences(){
        return referenceService.getReferences();
    }

}

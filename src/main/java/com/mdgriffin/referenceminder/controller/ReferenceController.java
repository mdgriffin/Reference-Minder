package com.mdgriffin.referenceminder.controller;

import com.mdgriffin.referenceminder.entity.Reference;
import com.mdgriffin.referenceminder.exception.ResourceNotFoundException;
import com.mdgriffin.referenceminder.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Ref;
import java.util.Arrays;
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

    // TODO: Figure out how to use the same get mapping
    @GetMapping(value = "/references", params = "tag")
    public List<Reference> getReferencesByTag(@RequestParam("tag") String tag){
        return referenceService.getReferencesTaggedWith(Arrays.asList(tag));
    }

    @GetMapping("/references/{referenceId}")
    public Reference getOneReference (@PathVariable String referenceId) {
        return referenceService.findById(referenceId).orElseThrow(() -> new ResourceNotFoundException("Reference", "id", referenceId));
    }

    @PostMapping("/references")
    public Reference createReference(@RequestBody @Valid Reference reference) {
        return referenceService.saveReference(reference);
    }

    @DeleteMapping("/references/{referenceId}")
    public ResponseEntity deleteReference (@PathVariable String referenceId) {
        try {
            referenceService.deleteReference(referenceId);
            return ResponseEntity.ok().build();
        } catch (Exception exc) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/references/{referenceId}")
    public Reference updateReference (@RequestBody @Valid Reference reference) {
        return referenceService.updateReference(reference);
    }

}

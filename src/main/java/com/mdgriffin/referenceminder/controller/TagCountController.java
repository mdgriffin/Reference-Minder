package com.mdgriffin.referenceminder.controller;

import com.mdgriffin.referenceminder.entity.TagCount;
import com.mdgriffin.referenceminder.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagCountController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("/tags")
    public List<TagCount> getTags () {
        return tagsService.getTags();
    }
}

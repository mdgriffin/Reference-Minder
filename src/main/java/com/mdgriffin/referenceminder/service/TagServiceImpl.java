package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.TagCount;
import com.mdgriffin.referenceminder.repository.TagCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagsService {

    @Autowired
    private TagCountRepository tagCountRepository;

    @Override
    public List<TagCount> getTags() {
        return tagCountRepository.findAll();
    }
}

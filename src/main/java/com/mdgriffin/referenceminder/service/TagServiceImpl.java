package com.mdgriffin.referenceminder.service;

import com.mdgriffin.referenceminder.entity.TagCount;
import com.mdgriffin.referenceminder.repository.TagCountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagsService {

    @Autowired
    private TagCountRepository tagCountRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    private static final String MAP_REDUCE_INPUT = "reference";

    private static final String MAP_REDUCE_OUTPUT = "tag_count";

    private static final String MAP_FUNCTION = "function () {this.tags.forEach(function (tag) { emit(tag.text, 1);});}";

    private static final String REDUCE_FUNCTION = "function (key, values) {return Array.sum(values);}";

    @Override
    public List<TagCount> getTags() {
        return tagCountRepository.findAll();
    }

    @Override
    public void updateTagCount() {
        MapReduceResults<TagCount> results = mongoTemplate.mapReduce(MAP_REDUCE_INPUT, MAP_FUNCTION, REDUCE_FUNCTION, new MapReduceOptions().outputCollection(MAP_REDUCE_OUTPUT), TagCount.class);
        logger.info("Ran MapReduce outputting " + MAP_REDUCE_OUTPUT + " collection with " + results.getCounts().getOutputCount() + " results");
    }
}
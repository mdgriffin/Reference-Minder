package com.mdgriffin.referenceminder.repository;

import com.mdgriffin.referenceminder.entity.TagCount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagCountRepository extends MongoRepository<TagCount, String> {

}

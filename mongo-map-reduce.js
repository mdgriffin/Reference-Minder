// Tag Count
db.reference.mapReduce(
    function () {
        this.tags.forEach(function (tag) {
            emit(tag.text, 1)
        });
    },
    function (key, values) {
        return Array.sum(values);
    },
    {
        // re-run map reduce
        query: { ts: { $gt: ISODate(db.getCollection('tag_count').find().sort({'_id': -1}).limit(1).updatedAt)  }},
        out: 'tag_count'
        //out: {inline: 1}
    }
)

//
db.reference.mapReduce(
    function () {
        var doc = this;
        this.tags.forEach(function (tag) {
            emit(tag.text, doc)
        });
    },
    function (key, values) {
        return {res: values}
    },
    {
        //out: 'tags'
        out: {inline: 1}
    }
)
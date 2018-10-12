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
        out: 'tags_count'
        //out: {inline: 1}
    }
)
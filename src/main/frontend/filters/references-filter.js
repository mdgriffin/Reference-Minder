export function flatten (references) {
    var res = references.reduce((acc, reference) => {
        let arr = [];

        arr.push(reference.title);
        arr.push(reference.authors.reduce((acc, author) => acc += author.name + ', ', ''));
        arr.push(reference.pages.from)
        arr.push(reference.pages.to)
        arr.push(new Date(reference.date.year, reference.date.month, reference.date.day).toLocaleDateString("en-IE"))
        arr.push(new Date(reference.dateAccessed.year, reference.dateAccessed.month, reference.dateAccessed.day).toLocaleDateString("en-IE"))
        arr.push(reference.type? reference.type :  '')

        acc.push(arr)

        return acc;
    }, [])

    return res;
}
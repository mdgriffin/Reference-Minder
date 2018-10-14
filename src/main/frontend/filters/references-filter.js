export function flatten (references) {
    return references.reduce((acc, reference) => {
        acc.push([
            reference.title,
            reference.authors.reduce((acc, author) => acc += author.name + ', ', ''),
            reference.pages.from,
            reference.pages.to,
            new Date(reference.date).toLocaleDateString("en-IE"),
            new Date(reference.dateAccessed).toLocaleDateString("en-IE"),
            reference.type? reference.type :  ''
        ]);

        return acc;
    }, [])
}
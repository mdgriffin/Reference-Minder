export function getReferenceTypes () {
    return fetch('/api/reference-types')
        .then(result => {
            return result.json()
        })
}
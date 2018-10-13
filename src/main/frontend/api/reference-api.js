export function getReferences () {
    return fetch('/api/references')
        .then(result => {
            return result.json()
        })
}

export function saveReference (reference) {
    return fetch('/api/references', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(reference)
    })
    .then(result => {
        return result.json()
    })
}

export function deleteReference (referenceId) {
    return fetch('/api/references/' +referenceId, {
        method: 'DELETE'
    });
}

export function getReferenceTypes () {
    return fetch('/api/reference-types')
        .then(result => {
            return result.json()
        })
}

export function getReferenceTags () {
    return fetch('/api/tags')
        .then(result => {
            return result.json()
        })
}
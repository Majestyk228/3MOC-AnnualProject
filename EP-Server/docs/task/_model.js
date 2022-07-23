module.exports = {
    task: {
        type: "object",
        properties: {
            idTask: {
                type: "int",
                description: "ID of the task.",
                example: "1",
            },
            title: {
                type: "String",
                description: "Title of the task.",
                example: "Connexion utilisateur.",
            },
            description: {
                type: "String",
                description: "Description of the task.",
                example: "La connexion utilisateur doit etre faite par une requête POST vers l'API."
            },
            idUser: {
                type: "int",
                description: "ID of the user that owns the task.",
                example: "1",
            },
            idList: {
                type: "int",
                description: "ID of the list the task belongs in.",
                example: "1",
            },
            idTag: {
                type: "int",
                description: "ID of the tag attached to the task.",
                example: "1",
            },
        },
    },
    tasks: {
        type: "object",
        additionalProperties: { $ref: "#/components/schemas/task" },
    },
}
module.exports = {
    get: {
        tags: ["comment"],
        description: "Get all comments from a task.",
        operationId: "commentsFromTask",
        parameters: [
            {
                in: "path",
                required: true,
                type: "int",
                name: "idTask",
                schema: {
                    type: "integer",
                    example: {
                        idTask: 2
                    }
                }
            },
        ],
        responses: {
            200: {
                description: "Get all comments from a task.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                idComment: 1,
                                firstname: "Sarah",
                                lastname: "KOUTA",
                                body: "Début de tâche prévu la semaine prochaine.",
                                date: "2022-07-21"
                            }
                        },
                    },
                },
            },
        },
    },
}

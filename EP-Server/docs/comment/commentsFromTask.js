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
                            $ref: "#/components/schemas/comments",
                        },
                    },
                },
            },
        },
    },
}

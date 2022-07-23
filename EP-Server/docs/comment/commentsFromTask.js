module.exports = {
    get: {
        tags: ["comment"],
        description: "Get all comments from a task.",
        operationId: "commentsFromTask",
        parameters: [],
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
module.exports = {
    get: {
        tags: ["task"],
        description: "Gets the infos of a given task.",
        operationId: "getTaskInfo",
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
                description: "Gets the infos of a given task.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/components/schemas/task",
                        },
                    },
                },
            },
            400: {
                description: "Bad Request",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                ERROR: "[API or/and database error]"
                            }
                        },
                    },
                },
            },
        },
    },
}
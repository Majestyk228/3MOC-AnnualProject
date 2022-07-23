module.exports = {
    get: {
        tags: ["list"],
        description: "Gets the list of lists owned by a user.",
        operationId: "getListOfUser",
        parameters: [
            {
                in: "path",
                required: true,
                type: "int",
                name: "idUser",
                schema: {
                    type: "integer",
                    example: {
                        idUser: 2
                    }
                }
            },
        ],
        responses: {
            200: {
                description: "Gets the list of lists owned by a user.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/components/schemas/lists",
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
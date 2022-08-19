module.exports = {
    get: {
        tags: ["post"],
        description: "Get a posts",
        operationId: "getPost",
        parameters: [
            {
                in: "header",
                name: "token",
                required: true,
                description: "User token",
                schema: {
                    type: "string",
                    example: {
                        token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZFVzZXIiOjYsImlhdCI6MTY2MDc2Mjg0NSwiZXhwIjoxNjYxMzY3NjQ1fQ.mORMwfV6A6KmACBTwvMKAcPCMtp0ks93A"
                    }
                }
            },
            {
                in: "path",
                name: "idPost",
                required: true,
                description: "ID of the post",
                schema: {
                    type: "integer",
                    example: {
                        idPost: 2
                    }
                }
            },
        ],
        responses: {
            200: {
                description: "Get a post",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/components/schemas/post",
                        },
                    },
                },
            },
            400: {
                description: "Bad Request",
                content: {
                    "application/json": {
                        schema: {
                            type: "string",
                            example: {
                                ERROR: "[API or/and database error]"
                            }
                        },
                    },
                },
            },
            404: {
                description: "Missing token in header",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                ERROR: "Missing token in header"
                            }
                        },
                    },
                },
            },
            406: {
                description: "Token expired/incorrect",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                ERROR: "Token expired/incorrect"
                            }
                        },
                    },
                },
            },
        },
    },
}

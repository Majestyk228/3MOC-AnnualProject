module.exports = {
    post: {
        tags: ["admin"],
        description: "Login procedure of admin",
        operationId: "adminLoginSecure",
        parameters: [],
        requestBody: {
            content: {
                "application/json": {
                    schema: {
                        type: "String",
                        example: {
                            email: "fipupa@gmail.com",
                            password: "test1234"
                        }
                    }
                },
            },
        },
        responses: {
            200: {
                description: "Login successuful",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                idAdmin: 1,
                                idCommunity: 2,
                                token: "eyJhbGciOiJIUzI1NiIsInR5cCI6I[...]oxNjU5MDQ2OTEzfQ.0lBdZ-HdQpUwqe9XY3eCg0wdtsLTt0E_RrYVLBEpamw"
                            }
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

module.exports = {
    post: {
        tags: ["admin"],
        description: "Login procedure of admin",
        operationId: "adminLogin",
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
                                email: "root@root.fr",
                                password: "IMAGINARY PASSWORD",
                                idCommunity: 2
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

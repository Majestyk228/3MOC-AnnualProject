module.exports = {
    post: {
        tags: ["admin"],
        description: "Login of admin",
        operationId: "adminLogin",
        parameters: [
            {
                in: "path",
                required: true,
                type: "String",
                name: "email",
                schema: {
                    type: "String",
                    example: {
                        email: "fipupa@gmail.com"
                    }
                }
            },
            {
                in: "path",
                required: true,
                type: "String",
                name: "password",
                schema: {
                    type: "String",
                    example: {
                        email: "test1234"
                    }
                }
            },
        ],
        requestBody: {
            content: {
                "application/json": {
                    schema: {
                        $ref: "#/components/schemas/admin",
                    },
                },
            },
        },
        responses: {
            200: {
                message: "Invitation created successfully",
                code: 21
            },
            400: {
                ERROR: "Bad Request"
            },
        },
    },
}

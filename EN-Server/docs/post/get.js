module.exports = {
    get: {
        tags: ["post"],
        description: "Get a posts",
        operationId: "getPost",
        parameters: [
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
        },
    },
}

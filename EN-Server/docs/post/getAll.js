module.exports = {
    get: {
        tags: ["post"],
        description: "Get all posts",
        operationId: "getAllPosts",
        parameters: [],
        responses: {
            200: {
                description: "Get all posts",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/components/schemas/posts",
                        },
                    },
                },
            },
        },
    },
}

module.exports = {
    get: {
        tags: ["admin"],
        description: "Get all invite",
        operationId: "getAllInvites",
        parameters: [],
        responses: {
            200: {
                description: "Get all invites",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/components/schemas/admin",
                        },
                    },
                },
            },
        },
    },
}

module.exports = {
    get: {
        tags: ["admin"],
        description: "Get all admins",
        operationId: "getAllInvites",
        parameters: [],
        responses: {
            200: {
                description: "Get all admins",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/components/schemas/admins",
                        },
                    },
                },
            },
        },
    },
}

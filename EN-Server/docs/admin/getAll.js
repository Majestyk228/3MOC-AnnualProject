module.exports = {
    get: {
        tags: ["admin"],
        description: "Get all admins [DEPRECIATED]",
        operationId: "getAllAdmin",
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
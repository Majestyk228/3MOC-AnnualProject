module.exports = {
    get: {
        tags: ["rewards"],
        description: "Get all rewards",
        operationId: "getAllRewards",
        parameters: [],
        responses: {
            200: {
                description: "Get all rewards",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/components/schemas/reward",
                        },
                    },
                },
            },
        },
    },
}

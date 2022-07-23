module.exports = {
    list: {
        type: "object",
        properties: {
            idList: {
                type: "int",
                description: "ID of the list.",
                example: "1",
            },
            title: {
                type: "String",
                description: "Title of the list.",
                example: "Urgent",
            },
            idUser: {
                type: "int",
                description: "ID of the user who owns the list.",
                example: "1"
            }
        },
    },
    lists: {
        type: "object",
        additionalProperties: { $ref: "#/components/schemas/list" },
    },
}
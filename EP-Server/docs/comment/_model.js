module.exports = {
    comment: {
        type: "object",
        properties: {
            idComment: {
                type: "int",
                description: "ID of the comment.",
                example: "1",
            },
            body: {
                type: "String",
                description: "Body of the comment.",
                example: "This is the body of the comment.",
            },
            date: {
                type: "Date",
                description: "Date the comment was posted on.",
                example: "2022-07-23"
            },
            idTask: {
                type: "int",
                description: "ID of the task the comment was posted on.",
                example: "3"
            },
            idUser: {
                type: "int",
                description: "ID of the user who posted the comment.",
                example: "1"
            }
        },
    },
    comments: {
        type: "object",
        additionalProperties: { $ref: "#/components/schemas/comment" },
    },
}
module.exports = {
    post: {
        type: "object",
        properties: {
            idPost: {
                type: "int",
                description: "ID of the post",
                example: "1",
            },
            title: {
                type: "string",
                description: "Title of the post",
                example: "SUUUUU",
            },
            body: {
                type: "string",
                description: "Body of the post",
                example: "Le titre de ce post est SUUUUU"
            },
            date: {
                type: "date",
                description: "Creation date of the post",
                example: "2022-05-25"
            },
            time: {
                type: "time",
                description: "Creation time of the post",
                example: "18:17:30"
            },
            likes: {
                type: "int",
                description: "Number of likes",
                example: 2
            },
            dislikes: {
                type: "int",
                description: "Number of dislikes",
                example: 5
            },
            idCommunity: {
                type: "int",
                description: "ID of the community the post is attached to",
                example: 4
            },
            idUser: {
                type: "int",
                description: "ID of the user who may have created the post",
                example: 6
            },
            idAdmin: {
                type: "int",
                description: "ID of the admin who may have created the post",
                example: 9
            },
            reported: {
                type: "int",
                description: "Number of reports",
                example: 4
            },
        },
    },
    posts: {
        type: "object",
        additionalProperties: { $ref: "#/components/schemas/post" },
    },
}

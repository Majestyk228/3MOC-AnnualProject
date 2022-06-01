module.exports = {
    admin: {
        type: "object",
        properties: {
            idAdmin: {
                type: "int",
                description: "ID of admin",
                example: "1",
            },
            firstName: {
                type: "String",
                description: "Firstname of admin",
                example: "Fally",
            },
            lastName: {
                type: "String",
                description: "Lastname of admin",
                example: "IPUPA"
            },
            email: {
                type: "String",
                description: "EMail of admin used to login",
                example: "fipupa@gmail.com"
            },
            password: {
                type: "String",
                description: "Password of user",
                example: "test1234"
            }
        },
    },
    admins: {
        type: "object",
        additionalProperties: { $ref: "#/components/schemas/admin" },
    },
}
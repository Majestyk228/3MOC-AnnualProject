module.exports = {
	user: {
		type: "object",
		properties: {
			idUser: {
				type: "int",
				description: "ID of the user.",
				example: "1",
			},
			firstname: {
				type: "String",
				description: "Firstname of the user.",
				example: "Sarah",
			},
			lastname: {
				type: "String",
				description: "Lastname of the user.",
				example: "KOUTA"
			},
			email: {
				type: "String",
				description: "Email of the user.",
				example: "sky@gmail.com",
			},
			password: {
				type: "String",
				description: "Password of the user.",
				example: "[password...]",
			}
		},
	},
	users: {
		type: "object",
		additionalProperties: { $ref: "#/components/schemas/user" },
	},
}

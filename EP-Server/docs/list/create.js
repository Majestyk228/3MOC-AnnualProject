module.exports = {
	post: {
		tags: ["list"],
		description: "Creates a new list for a user.",
		operationId: "createList",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							title: "ceci est une liste",
							idUser: 1
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "List created successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "List created successfully."
							}
						},
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "[API or/and database error]"
							}
						},
					},
				},
			},
		},
	},
}

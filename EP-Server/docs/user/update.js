module.exports = {
	put: {
		tags: ["user"],
		description: "Update a given user.",
		operationId: "updateUser",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idUser: 1,
							firstname: "Sarah",
							lastname: "KOUTA-LOPATEY",
							email: "skoutalopatey@myges.fr"
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "User updated successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "User updated successfully"
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
			}
		},
	},
}

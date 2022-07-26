module.exports = {
	put: {
		tags: ["user"],
		description: "Update a user's password.",
		operationId: "updatePassword",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idUser: 1,
							password: "[Password...]"
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Password user updated successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "Password user updated successfully"
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
			404: {
				description: "Missing argument",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "Missing argument"
							}
						},
					},
				},
			},
		},
	},
}

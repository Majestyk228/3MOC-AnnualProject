module.exports = {
	get: {
		tags: ["list"],
		description: "Update a given list.",
		operationId: "updateList",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idList: 1,
							title: "ceci est une liste mise à jour"
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "List updated successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "List updated successfully"
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
				description: "Missing title argument",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "Missing title argument"
							}
						},
					},
				},
			},
		},
	},
}

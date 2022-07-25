module.exports = {
	post: {
		tags: ["comment"],
		description: "Creates a new list for a user.",
		operationId: "createComment",
		parameters: [],
		responses: {
			200: {
				description: "List created successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "Comment created successfully."
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

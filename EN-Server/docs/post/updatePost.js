module.exports = {
	put: {
		tags: ["post"],
		description: "Udpate a given post (all entries are optional except idPost ; missing parameters wont be changed in database.",
		operationId: "updatePost",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idPost: 7,
							title: "TestAPI UPDATE",
							body: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nisl."
						}
					}
				},
			},
		},
		responses: {
			201: {
				description: "Post updated successfully",
				content: {
					"application/json": {
						type: "object",
						example: {
							Message: "Post updated successfully"
						}
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						type: "object",
						example: {
							ERROR: "Bad Request"
						}
					},
				},
			},
			422: {
				description: "Missing argument(s)",
				content: {
					"application/json": {
						type: "object",
						example: {
							ERROR: "Missing argument(s)"
						}
					},
				},
			}
		},
	},
}

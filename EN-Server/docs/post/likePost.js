module.exports = {
	post: {
		tags: ["post"],
		description: "Adds a like to a post.",
		operationId: "likePost",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idPost: 1
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Post liked successfully",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Post liked successfully",
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
							type: "string",
							example: {
								ERROR: "Bad Request"
							}
						},
					},
				},
			},
		},
	},
}

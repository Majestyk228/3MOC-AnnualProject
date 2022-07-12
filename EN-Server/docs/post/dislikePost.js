module.exports = {
	post: {
		tags: ["post"],
		description: "Adds a dislike to a post.",
		operationId: "dislikePost",
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
				description: "Post disliked successfully",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Post disliked successfully",
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
								ERROR: "[API or/and database error]"
							}
						},
					},
				},
			},
		},
	},
}

module.exports = {
	post: {
		tags: ["comment"],
		description: "Create a comment (idUser and idAdmin must be in body but only one of those need to have a value ; other one can be null",
		operationId: "createComment",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idCommunity: 1,
							body: "40354eme comm du val de marne haha",
							anonymous: "True",
							idPost: 1,
							idUser: 6
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Comment created successfully",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Comment created successfully",
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

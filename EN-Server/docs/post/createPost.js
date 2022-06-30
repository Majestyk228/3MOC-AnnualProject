module.exports = {
	post: {
		tags: ["post"],
		description: "Create post (idUser and idAdmin must be in body but only one of those need to have a value ; other one can be null",
		operationId: "createPost",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							title: "testAPI",
							body: "ceci est un post ADMIN de test via API",
							idCommunity: 1,
							idUser: null,
							idAdmin: 1
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Post created successfully",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Post created successfully",
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

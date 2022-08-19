module.exports = {
	post: {
		tags: ["user"],
		description: "Get all informations on a specific user",
		operationId: "getAllInfos",
		parameters: [
			{
				in: "header",
				name: "token",
				required: true,
				description: "User token",
				schema: {
					type: "string",
					example: {
						token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZFVzZXIiOjYsImlhdCI6MTY2MDc2Mjg0NSwiZXhwIjoxNjYxMzY3NjQ1fQ.mORMwfV6A6KmACBTwvMKAcPCMtp0ks93A"
					}
				}
			},
		],
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "integer",
						example: {
							idUser: 2
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Get all informations on a specific user",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/user",
						},
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						type: "object",
						example: {
							Error: "[API or/and database error]"
						}
					},
				},
			},
			404: {
				description: "Missing token in header",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "Missing token in header"
							}
						},
					},
				},
			},
			406: {
				description: "Token expired/incorrect",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "Token expired/incorrect"
							}
						},
					},
				},
			},
		},
	},
}

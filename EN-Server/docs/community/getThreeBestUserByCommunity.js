module.exports = {
	post: {
		tags: ["community"],
		description: "Get the first 3 users with highest score from a given community",
		operationId: "getThreeBestUserByCommunity",
		parameters: [
			{
				in: "header",
				name: "token",
				required: true,
				description: "Admin token",
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
							idCommunity: 2
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Get the first 3 users with highest score from a given community community stats",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: [
								{
									idUser: 7,
									firstName: "Bo",
									lastName: "BURNHAM"
								},
								{
									idUser: 6,
									firstName: "Fally",
									lastName: "IPUPA"
								},
								{
									idUser: 8,
									firstName: "Master",
									lastName: "KJ"
								}
							]
						},
					},
				},
			},
			400: {
				description: "Bad request",
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
			}
		},
	},
}

module.exports = {
	post: {
		tags: ["community"],
		description: "Get stats from a community",
		operationId: "getCommunityStats",
		parameters: [
			{
				in: "header",
				name: "token",
				required: true,
				description: "ID of the community",
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
				description: "Get community stats",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								nbUsers: 10,
								totalPointsCommunity: 500,
								nbPost: 10,
								nbVote: 10
							}
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

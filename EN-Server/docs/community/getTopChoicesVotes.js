module.exports = {
	post: {
		tags: ["community"],
		description: "Get every top choice on every vote for a specific community",
		operationId: "getTopChoicesVotes",
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
				description: "Gives for every vote its ID, title, body and the choice mosty selected by users.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								idVote: 1,
								title: "Premier vote !",
								bestChoice: 1,
								body: "Thanos est-il gentil ou méchant ?"
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

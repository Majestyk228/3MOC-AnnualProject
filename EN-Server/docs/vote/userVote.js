//voteInfo
module.exports = {
	post: {
		tags: ["vote"],
		description: "Inserts in database the choice of the user a given vote.",
		operationId: "userVote",
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
		requestBody:
		{
			content: {
				"application/json": {
					schema: {
						type: "object",
						example:
						{
							idUser: 6,
							idVote: 1,
							choice: 1
						}
					}
				},
			},
		},

		responses: {
			200: {
				description: "Vote User submitted successfully.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Vote User submitted successfully"
							}
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
							ERROR: "[API or/and database error]"
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

//voteInfo
module.exports = {
	post: {
		tags: ["vote"],
		description: "Inserts in database vote options for a given vote.",
		operationId: "insertVoteOptions",
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
						example: [
							{
								label: "Oui !",
								idVote: 1
							},
							{
								label: "Non !",
								idVote: 1
							},
							{
								label: "Sans opinion",
								idVote: 1
							}
						]
					}
				},
			},
		},

		responses: {
			200: {
				description: "Get all information on a given vote.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Vote Options created successfully"
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

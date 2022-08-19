//voteInfo
module.exports = {
	post: {
		tags: ["vote"],
		description: "Get title, body, vote options and number of time each option got selected for a given vote (NEEDS TO BE FIXED)",
		operationId: "getVoteInfo",
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
						example: {
							idVote: 1
						}
					}
				},
			},
		},

		responses: {
			200: {
				description: "Get title, body, vote options and number of time each option got selected for a given vote",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								title: "Premier vote !",
								body: "Thanon est-il gentil ou mechant ?",
								voteOptions: [
									{
										label: "Oui !",
										idVoteOptions: 1,
										nbChoice: 0
									},
									{
										label: "Non !",
										idVoteOptions: 2,
										nbChoice: 2
									},
									{
										label: "Sans opinion",
										idVoteOptions: 3,
										nbChoice: 0
									}
								]
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

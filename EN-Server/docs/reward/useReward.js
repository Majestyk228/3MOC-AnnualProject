// useReward.js
module.exports = {
	post: {
		tags: ["rewards"],
		description: "Submits the reward submmition to a post.",
		operationId: "useReward",
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
						type: "object",
						example: {
							idRewards: 1,
							idPost: 2,
							idUser: 8
						}
					}
				},
			},
		},
		responses: {
			201: {
				description: "Reward added successfully.",
				content: {
					"application/json": {
						type: "object",
						example: {
							message: "Reward added successfully."
						}
					},
				},
			},
			400: {
				description: "Missing infos",
				content: {
					"application/json": {
						type: "object",
						example: {
							Error: "Missing info(s)"
						}
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

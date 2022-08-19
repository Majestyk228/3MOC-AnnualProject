module.exports = {
	get: {
		tags: ["vote"],
		description: "Gives the last 5 votes created in a given community",
		operationId: "lastPosts",
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
			{
				in: "path",
				name: "idCommunity",
				required: true,
				description: "ID of the community",
				schema: {
					type: "integer",
					example: {
						idCommunity: 1
					}
				}
			},
		],
		responses: {
			201: {
				description: "Gives the last 5 votes created in a given community",
				content: {
					"application/json": {
						type: "object",
						example: [
							{
								idVote: 5,
								title: "Quatrième vote"
							},
							{
								idVote: 4,
								title: "Troisième vote"
							},
							{
								idVote: 3,
								title: "Test 1"
							},
							{
								idVote: 2,
								title: "Test 1"
							},
							{
								idVote: 1,
								title: "Test 1"
							}
						]
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

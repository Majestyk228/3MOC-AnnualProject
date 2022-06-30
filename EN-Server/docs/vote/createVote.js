module.exports = {
	post: {
		tags: ["vote"],
		description: "Create a new vote in database.",
		operationId: "createVote",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "string",
						example: {
							title: "Vote via API",
							body: "Ceci est un vote créé via mon API ; cool non ?",
							nbChoice: 3,
							important: true,
							idAdmin: 1,
							idCommunity: 1
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Vote created successfully.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Vote created successfully"
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
							type: "string",
							example: {
								ERROR: "Bad request"
							}
						},
					},
				},
			},
		},
	},
}

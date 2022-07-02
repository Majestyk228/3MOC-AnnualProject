module.exports = {
	put: {
		tags: ["vote"],
		description: "Udpate a given vote (all entries are optional except idVote ; missing parameters wont be changed in database.",
		operationId: "updateVote",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idVote: 7,
							title: "Vote via API UPDATED",
							body: "Ceci est un vote créé via mon API ; cool non UPDATED09360?",
							nbChoices: 3,
							voteBegins: "2022-07-02",
							voteEnds: "2022-07-29"
						}
					}
				},
			},
		},
		responses: {
			201: {
				description: "Vote updated successfully",
				content: {
					"application/json": {
						type: "object",
						example: {
							Message: "Vote updated successfully"
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
							ERROR: "Bad Request"
						}
					},
				},
			},
			422: {
				description: "Missing argument(s)",
				content: {
					"application/json": {
						type: "object",
						example: {
							ERROR: "Missing argument(s)"
						}
					},
				},
			}
		},
	},
}

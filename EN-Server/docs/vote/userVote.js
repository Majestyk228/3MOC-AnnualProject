//voteInfo
module.exports = {
	post: {
		tags: ["vote"],
		description: "Inserts in database the choice of the user a given vote.",
		operationId: "userVote",
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
		},
	},
}

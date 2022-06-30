module.exports = {
	delete: {
		tags: ["vote"],
		description: "Delete a new vote from the database.",
		operationId: "deleteVote",
		parameters: [
			{
				in: "path",
				name: "idVote",
				required: true,
				description: "ID of the vote",
				schema: {
					type: "integer",
					example: {
						idVote: 7
					}
				}
			},
		],
		responses: {
			200: {
				description: "Vote deleted successfully.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Vote deleted successfully"
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

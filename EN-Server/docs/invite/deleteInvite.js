module.exports = {
	delete: {
		tags: ["invite"],
		description: "Delete invitation",
		operationId: "deletedInvite",
		parameters: [
			{
				in: "path",
				name: "code",
				required: true,
				description: "Identification code of invitation",
				schema: {
					type: "integer",
					example: {
						code: 15
					}
				}
			},
		],
		responses: {
			200: {
				description: "Invitation deleted successfully",
				content: {
					"application/json": {
						type: "object",
						example: {
							Message: "Invitation deleted successfully"
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
							ERROR: "[API or/and database error]"
						}
					},
				},
			}
		},
	},
}

module.exports = {
	delete: {
		tags: ["user"],
		description: "Delete user account",
		operationId: "deletedUser",
		parameters: [
			{
				in: "path",
				name: "idUser",
				required: true,
				description: "ID of the user",
				schema: {
					type: "integer",
					example: {
						idUser: 15
					}
				}
			},
		],
		responses: {
			201: {
				description: "User deleted successfully",
				content: {
					"application/json": {
						type: "object",
						example: {
							Message: "User deleted successfully"
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
			400: {
				description: "idUser not in request",
				content: {
					"application/json": {
						type: "object",
						example: {
							ERROR: "idUser not in request"
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

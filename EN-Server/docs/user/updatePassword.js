module.exports = {
	put: {
		tags: ["user"],
		description: "Udpate user's password",
		operationId: "updatePasswordUser",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							password: "jerusalema",
							idUser: 8
						}
					}
				},
			},
		},
		responses: {
			201: {
				description: "Password updated successfully",
				content: {
					"application/json": {
						type: "object",
						example: {
							Message: "Password updated successfully"
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

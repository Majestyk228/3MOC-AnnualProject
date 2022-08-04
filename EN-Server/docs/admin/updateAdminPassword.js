module.exports = {
	put: {
		tags: ["admin"],
		description: "Udpate admin's password",
		operationId: "updateAdminPasswordUser",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							password: "jerusalema",
							idAdmin: 8
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
							Message: "Admin password updated successfully"
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
							ERROR: "idAdmin not in request"
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

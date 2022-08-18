module.exports = {
	put: {
		tags: ["admin"],
		description: "Udpate admin's password",
		operationId: "updateAdminPasswordUser",
		parameters: [
			{
				in: "header",
				name: "token",
				required: true,
				description: "Admin token",
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

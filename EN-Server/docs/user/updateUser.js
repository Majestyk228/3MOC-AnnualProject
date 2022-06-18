module.exports = {
	put: {
		tags: ["user"],
		description: "Udpate user's informations",
		operationId: "updateUser",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							firstName: "Master",
							lastName: "KG",
							birthDate: "1996-01-30",
							gender: "Male",
							areaCode: "94000",
							email: "mkg@gmail.com",
							password: "jerusalema"
						}
					}
				},
			},
		},
		responses: {
			201: {
				description: "User updated created",
				content: {
					"application/json": {
						type: "object",
						example: {
							Message: "User updated successfully"
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
			}
		},
	},
}

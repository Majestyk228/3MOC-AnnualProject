module.exports = {
	post: {
		tags: ["user"],
		description: "Registration of new user",
		operationId: "postRegister",
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
				description: "User account created",
				content: {
					"application/json": {
						type: "object",
						example: {
							Message: "User successfuly registered"
						}
					},
				},
			},
			400: {
				description: "Missing infos",
				content: {
					"application/json": {
						type: "object",
						example: {
							Error: "Missing info(s)"
						}
					},
				},
			},
			409: {
				description: "User already has an account",
				content: {
					"application/json": {
						type: "object",
						example: {
							Error: "User already has an account"
						}
					},
				},
			},
		},
	},
}

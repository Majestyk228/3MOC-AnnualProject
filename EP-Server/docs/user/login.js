module.exports = {
	post: {
		tags: ["user"],
		description: "Gets the user ID for login purposes.",
		operationId: "login",
		parameters: [],
		responses: {
			201: {
				description: "Gets the infos of a given task.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								idUser: "1"
							}
						},
					},
				},
			},
			400: {
				description: "Missing email or/and password",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "Missing email or/and password"
							}
						},
					},
				},
			},
			404: {
				description: "cannot find user",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "cannot find user"
							}
						},
					},
				},
			},
		},
	},
}

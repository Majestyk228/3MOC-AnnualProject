//registerAdmin.js
module.exports = {
	post: {
		tags: ["user"],
		description: "Registration of new user from the admin console and adds the user to the good community.",
		operationId: "postRegisterAdmin",
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
							password: "jerusalema",
							idCommunity: 2
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
							message: "User successfuly registered",
							idUser: 20
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

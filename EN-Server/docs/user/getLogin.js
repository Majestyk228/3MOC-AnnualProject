module.exports = {
	post: {
		tags: ["user"],
		description: "Gives idUser, idCommunity and JWT token",
		operationId: "login",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							email: "fipupa@gmail.com",
							password: "test1234"
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Gives idUser, idCommunity and JWT token",
				content: {
					"application/json": {
						type: "object",
						example: {
							idUser: 6,
							idCommunity: 1,
							token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZFVzZXIiOjYsImlhdCI6MTY1NTU1Mjg1OCwiZXhwIjoxNjU1NTYwMDU4fQ._EkoalCG5mZPrfAEx_ujqWYb_4apCm65-sQIO7SJfAA"
						}
					},
				},
			},
		},
	},
}

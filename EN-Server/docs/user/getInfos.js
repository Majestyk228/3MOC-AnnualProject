module.exports = {
	post: {
		tags: ["user"],
		description: "Get all informations on a specific user",
		operationId: "getAllInfos",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "integer",
						example: {
							idUser: 2
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Get all informations on a specific user",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/user",
						},
					},
				},
			},
		},
	},
}

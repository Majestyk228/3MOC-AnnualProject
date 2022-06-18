module.exports = {
	get: {
		tags: ["user"],
		description: "Get all users",
		operationId: "getAllUsers",
		responses: {
			200: {
				description: "Get all users",
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

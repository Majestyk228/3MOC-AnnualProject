module.exports = {
	get: {
		tags: ["user"],
		description: "Get all users ordered by points",
		operationId: "getAllUsersByPoints",
		responses: {
			200: {
				description: "Get all users ordered by points",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/users",
						},
					},
				},
			},
		},
	},
}

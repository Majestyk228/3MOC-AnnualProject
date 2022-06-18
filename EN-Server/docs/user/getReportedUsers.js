module.exports = {
	get: {
		tags: ["user"],
		description: "Get all reported users",
		operationId: "getReportedUsers",
		responses: {
			200: {
				description: "Get all reported users",
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

module.exports = {
	get: {
		tags: ["invite"],
		description: "Get all invite",
		operationId: "getAllInvites",
		parameters: [],
		responses: {
			200: {
				description: "Get all invites",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/invites",
						},
					},
				},
			},
		},
	},
}

//addToCommunity.js
module.exports = {
	post: {
		tags: ["user"],
		description: "Adds a given user to a given community.",
		operationId: "addToCommunity",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							iduser: 2,
							idCommunity: 5
						}
					}
				},
			},
		},
		responses: {
			201: {
				description: "User added to community successfully.",
				content: {
					"application/json": {
						type: "object",
						example: {
							message: "User added to community successfully."
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
							Error: "[API or/and database error]"
						}
					},
				},
			}
		},
	},
}

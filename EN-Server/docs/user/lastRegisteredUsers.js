module.exports = {
	get: {
		tags: ["user"],
		description: "Gives the last 5 users that registered in a given community",
		operationId: "lastRegisteredUsers",
		parameters: [
			{
				in: "path",
				name: "idCommunity",
				required: true,
				description: "ID of the community",
				schema: {
					type: "integer",
					example: {
						idCommunity: 1
					}
				}
			},
		],
		responses: {
			201: {
				description: "Gives the last 5 users that registered in a given community",
				content: {
					"application/json": {
						type: "object",
						example: [
							{
								idUser: 4,
								firstName: "FirstName",
								lastName: "lastName"
							},
							{
								idUser: 3,
								firstName: "FirstName",
								lastName: "lastName"
							},
							{
								idUser: 2,
								firstName: "FirstName",
								lastName: "lastName"
							}
						]
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						type: "object",
						example: {
							ERROR: "[API or/and database error]"
						}
					},
				},
			}
		},
	},
}

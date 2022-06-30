module.exports = {
	get: {
		tags: ["post"],
		description: "Gives the last 5 posts created in a given community",
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
				description: "Gives the last 5 posts created in a given community",
				content: {
					"application/json": {
						type: "object",
						example: [
							{
								idPost: 5,
								title: "Quatrième post"
							},
							{
								idUser: 4,
								title: "Troisième post"
							},
							{
								idUser: 3,
								title: "Test 1"
							},
							{
								idUser: 2,
								title: "Test 1"
							},
							{
								idUser: 1,
								title: "Test 1"
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
							ERROR: "Bad Request"
						}
					},
				},
			}
		},
	},
}

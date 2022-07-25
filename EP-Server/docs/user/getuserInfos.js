module.exports = {
	get: {
		tags: ["user"],
		description: "Gets the infos of a given user.",
		operationId: "getUserInfo",
		parameters: [
			{
				in: "path",
				required: true,
				type: "int",
				name: "idTask",
				schema: {
					type: "integer",
					example: {
						idUser: 2
					}
				}
			},
		],
		responses: {
			200: {
				description: "Gets the infos of a given user.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								firstname: "Sarah",
								lastname: "KOUTA",
								email: "skoutalopatey@myges.fr"
							}
						},
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "[API or/and database error]"
							}
						},
					},
				},
			},
		},
	},
}

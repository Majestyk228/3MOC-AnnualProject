module.exports = {
	get: {
		tags: ["comment"],
		description: "Get all comment from a post. [formatted for Android client]",
		operationId: "commentByPostFormatted",
		parameters: [
			{
				in: "path",
				name: "idPost",
				required: true,
				description: "ID of the post",
				schema: {
					type: "integer",
					example: {
						idPost: 1
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get all comment from a post.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: [
								{
									firstName: "Black",
									lastName: "Sherif",
									body: "40354eme comm du val de marne haha",
									anonymous: "True"
								},
								{
									firstName: "Black",
									lastName: "Sherif",
									body: "40355eme comm du val de marne haha",
									anonymous: "True"
								},
								{
									firstName: "Black",
									lastName: "Sherif",
									body: "40356eme comm du val de marne haha",
									anonymous: "True"
								}
							]
						},
					},
				},
			},
			400: {
				description: "Bad request",
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

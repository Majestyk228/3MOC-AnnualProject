module.exports = {
	post: {
		tags: ["post"],
		description: "Get the list of post from a given community, formatted to be used for Android client Exprimons-nous.",
		operationId: "getAllPostsFormatted",
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
			200: {
				description: "Get the list of post from a given community, formatted to be used for Android client Exprimons-nous. NOTE : comments and rewards initialized at 0 ; can be updated with another route.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								idPost: 1,
								firstName: "Fally",
								lastName: "IPUPA",
								title: "Premier poste !",
								body: "Ceci est le premier poste pour la commune du Val-de-Marne !!!",
								likes: 0,
								dislikes: 0,
								comments: 0,
								rewards: 0
							}
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

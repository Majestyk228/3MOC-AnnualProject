module.exports = {
	comment: {
		type: "object",
		properties: {
			idComment: {
				type: "int",
				description: "ID of the comment",
				example: "1",
			},
			body: {
				type: "string",
				description: "Body of the comment",
				example: "SUUUUU MY FRENCH",
			},
			likes: {
				type: "int",
				description: "Number of likes on the comment",
				example: "3"
			},
			dislikes: {
				type: "int",
				description: "Number of dislikes on the comment",
				example: "3"
			},
			reports: {
				type: "int",
				description: "Number of reports on the comment",
				example: "1"
			},
			anonymous: {
				type: "bool",
				description: "Wether the comment is anonymous or not",
				example: true
			},
			idPost: {
				type: "int",
				description: "ID of the post it's linked to.",
				example: "2"
			},
			idUser: {
				type: "int",
				description: "ID of the user who created the comment",
				example: "7"
			},
			date: {
				type: "date",
				description: "Date of creation of the comment",
				example: "2022-06-30"
			}
		},
	},
	comments: {
		type: "object",
		additionalProperties: { $ref: "#/components/schemas/comment" },
	},
}

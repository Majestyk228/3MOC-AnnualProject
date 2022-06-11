module.exports = {
	vote: {
		type: "object",
		properties: {
			idVote: {
				type: "int",
				description: "ID of vote",
				example: "1",
			},
			title: {
				type: "String",
				description: "Title of vote",
				example: "Super !!!",
			},
			body: {
				type: "String",
				description: "Body of the vote",
				example: "Thanos est-il gentil ou mechant ?"
			},
			nbChoice: {
				type: "int",
				description: "Number of choices available for this vote",
				example: "3"
			},
			important: {
				type: "boolean",
				description: "Refers to the importance of the vote",
				example: "true"
			},
			idUser: {
				type: "int",
				description: "ID of user",
				example: "2"
			},
			idAdmin: {
				type: "int",
				description: "ID of admin who created the vote",
				example: "2"
			},
			voteBegins: {
				type: "date",
				description: "Opening date of the vote",
				example: "2022-06-10"
			},
			voteEnds: {
				type: "date",
				description: "Closing date of the vote",
				example: "2022-06-17"
			},
			idCommunity: {
				type: "int",
				description: "ID of community",
				example: "2"
			}
		},
	},
	votes: {
		type: "object",
		additionalProperties: { $ref: "#/components/schemas/vote" },
	},
}

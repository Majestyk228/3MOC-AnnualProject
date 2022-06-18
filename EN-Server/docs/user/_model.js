module.exports = {
	user: {
		type: "object",
		properties: {
			idUser: {
				type: "int",
				description: "ID of the user",
				example: "6",
			},
			firstName: {
				type: "string",
				description: "Firstname of the user",
				example: "Fally",
			},
			lastName: {
				type: "string",
				description: "lastName of the user",
				example: "IPUPA"
			},
			birthDate: {
				type: "date",
				description: "Birthdate of the user",
				example: "1977-12-14"
			},
			gender: {
				type: "string",
				description: "Gender of the user",
				example: "Male"
			},
			areaCode: {
				type: "string",
				description: "Areacode of the user",
				example: "94000"
			},
			email: {
				type: "istringnt",
				description: "Email adress of the user",
				example: "fipupa@gmail.com"
			},
			password: {
				type: "string",
				description: "Password of the user",
				example: "test1234"
			},
			points: {
				type: "int",
				description: "Number of points earned by the user",
				example: 7
			},
			signInDate: {
				type: "date",
				description: "The date the user created their account on",
				example: "2022-05-12"
			}
		},
	},
	users: {
		type: "object",
		additionalProperties: { $ref: "#/components/schemas/user" },
	},
}

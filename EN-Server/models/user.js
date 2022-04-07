//Modele User

class User {
	constructor(idUser, firstName, lastName, birthdate, sex, areaCode, email, password, points) {
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.sex = sex;
		this.areaCode = areaCode;
		this.email = email;
		this.password = password;
		this.points = points;
	}

	addUser() {
		//code...
	}

	editUser(idUser, firstName, lastName, birthdate, sex, areaCode, email, password, points) {
		//code...
	}

	deleteUser(idUser) {
		//code...
	}

	getAllUsers() {
		//code...
	}

	getUser(idUser) {
		//code...
	}
}
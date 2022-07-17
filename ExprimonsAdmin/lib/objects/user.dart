class User {
  int? idUser;
  String? firstName;
  String? lastName;
  String? birthDate;
  String? gender;
  String? areaCode;
  String? email;
  String? password;
  int? points;
  String? signInDate;

  User({
    this.idUser,
    this.firstName,
    this.lastName,
    this.birthDate,
    this.gender,
    this.areaCode,
    this.email,
    this.password,
    this.points,
    this.signInDate,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      idUser: json['idUser'],
      firstName: json['firstName'],
      lastName: json['lastName'],
      birthDate: json['birthDate'],
      gender: json['gender'],
      areaCode: json['areaCode'],
      email: json['email'],
      password: json['password'],
      points: json['points'],
      signInDate: json['signInDate'],

    );
  }

}

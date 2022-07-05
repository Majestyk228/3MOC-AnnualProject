class Vote {
  int? idVote;
  String? title;
  String? body;
  int? nbChoice;
  bool? important;
  int? idUser;
  int? idAdmin;
  String? voteBegins;
  String? voteEnds;
  int? idCommunity;

  Vote(
      {this.idVote,
      this.title,
      this.body,
      this.nbChoice,
      this.important,
      this.idUser,
      this.idAdmin,
      this.voteBegins,
      this.voteEnds,
      this.idCommunity});

  factory Vote.fromJson(Map<String, dynamic> json) {
    return Vote(
      idVote: json['idVote'],
      title: json['title'],
      body: json['body'],
      nbChoice: json['nbChoice'],
      important: json['important'],
      idUser: json['idUser'],
      idAdmin: json['idAdmin'],
      voteBegins: json['voteBegins'],
      voteEnds: json['voteEnds'],
      idCommunity: json['idCommunity'],
    );
  }
}

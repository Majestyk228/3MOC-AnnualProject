class Community {
  int? idCommunity;
  String? label;

  Community({this.idCommunity, this.label});

  factory Community.fromJson(Map<String, dynamic> json) {
    return Community(
      idCommunity: json['idCommunity'],
      label: json['label'],
    );
  }
}

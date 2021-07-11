class DocumentModel {
  String? id;
  String? taskId;
  late String nameStudent;
  bool? isPlagiary;
  late String content;
  String? grade;
  String? dateSubmit;

  DocumentModel({
    this.id,
    this.taskId,
    required this.nameStudent,
    this.isPlagiary,
    required this.content,
    this.grade,
    this.dateSubmit,
  });

  factory DocumentModel.fromJson(Map<String, dynamic> json) => DocumentModel(
        id: json['id'],
        nameStudent: json['nameStudent'],
        isPlagiary: json['isPlagiary'],
        content: json['content'],
        grade: json['grade'],
        dateSubmit: json['dateSubmit'],
      );

  Map<String, dynamic> toJson() => {
        'id': id,
        'nameStudent': nameStudent,
        'isPlagiary': isPlagiary,
        'content': content,
        'grade': grade,
        'dateSubmit': dateSubmit,
      };
}

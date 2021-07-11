class TaskModel {
  String? id;
  late String title;
  late String description;
  late String dateFinalSubmit;
  String? documents;
  String? status;
  String? average;

  TaskModel({
    this.id,
    required this.title,
    required this.description,
    required this.dateFinalSubmit,
    this.documents,
    this.status,
    this.average,
  });

  factory TaskModel.fromJson(Map<String, dynamic> json) => TaskModel(
        id: json['id'],
        title: json['title'],
        description: json['description'],
        dateFinalSubmit: json['dateFinalSubmit'],
        documents: json['documents'],
        status: json['status'],
        average: json['average'],
      );

  Map<String, dynamic> toJson() => {
        'id': id,
        'title': title,
        'description': description,
        'dateFinalSubmit': dateFinalSubmit,
        'documents': documents,
        'status': status,
        'average': average,
      };
}

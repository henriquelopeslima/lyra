import 'package:dio/dio.dart';

import '../utils/const.dart';
import '../models/document_model.dart';

class DocumentService {
  Dio dio = Dio();
  late Response response;

  void getAll() async {
    response = await dio.get('${AppConst().URL_STUDENT}documents');
    print(response.data);
  }

  void create(DocumentModel document) async {
    try {
      await dio.post(
        '${AppConst().URL_STUDENT}submitDocument/${document.taskId}',
        data: document.toJson(),
      );
    } on DioError catch (err) {
      print('Error in post of task ${err.response!.statusCode}');
    }
  }
}

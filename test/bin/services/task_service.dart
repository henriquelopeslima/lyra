import 'package:dio/dio.dart';

import '../utils/const.dart';
import '../models/task_model.dart';

class TaskService {
  Dio dio = Dio();
  late Response response;

  void getAll() async {
    response = await dio.get('${AppConst().URL_TEACHER}tasks');
    print(response.data);
  }

  void create(TaskModel task) async {
    try {
      await dio.post('${AppConst().URL_TEACHER}createTask',
          data: task.toJson());
    } on DioError catch (err) {
      print('Error in post of task ${err.response?.statusCode ?? 400}');
    }
  }
}

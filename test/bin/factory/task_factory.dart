import 'package:intl/intl.dart';

import '../models/task_model.dart';
import '../services/task_service.dart';
import '../utils/random_custom.dart';

class TaskFactory {
  final taskService = TaskService();
  final randomCustom = RandomCustom();
  final formatDate = DateFormat('yyyy-MM-ddTHH:mm');

  void taskFactory(int amount) {
    for (var i = 0; i < amount; i++) {
      var task = _createRandow();
      taskService.create(task);
    }
  }

  TaskModel _createRandow() {
    var label = randomCustom.getRandomString(2);
    var dateFinalSubmit = DateTime.now();
    dateFinalSubmit = dateFinalSubmit
        .add(Duration(seconds: (200 + randomCustom.random.nextInt(300))));

    return TaskModel(
      title: 'Task $label',
      description: 'Description of $label',
      dateFinalSubmit: formatDate.format(dateFinalSubmit),
    );
  }
}

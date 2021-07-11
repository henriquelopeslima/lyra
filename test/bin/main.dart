import 'factory/document_factory.dart';
import 'factory/task_factory.dart';
import 'utils/const.dart';

void main(List<String> arguments) async {
  final taskFactory = TaskFactory();
  final documentFactory = DocumentFactory();

  taskFactory.taskFactory(AppConst().amountTask);
  documentFactory.documentFactory(AppConst().amountDocument);
}

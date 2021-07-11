import 'package:intl/intl.dart';

import '../models/document_model.dart';
import '../services/document_service.dart';
import '../utils/const.dart';
import '../utils/random_custom.dart';

class DocumentFactory {
  final documentService = DocumentService();
  final randomCustom = RandomCustom();
  final formatDate = DateFormat('yyyy-MM-ddThh:mm');

  void documentFactory(int amount) {
    for (var i = 0; i < amount; i++) {
      var document = _createRandow();
      documentService.create(document);
    }
  }

  DocumentModel _createRandow() {
    var label = randomCustom.getRandomString(2);

    return DocumentModel(
      taskId: (RandomCustom().random.nextInt(AppConst().amountTask - 1) + 1)
          .toString(),
      nameStudent: 'Student $label',
      content: 'content $label',
    );
  }
}

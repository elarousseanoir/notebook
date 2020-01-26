package notebook.service;

import notebook.data.ExecutionRequest;
import notebook.data.NoteBookIN;

public interface RequestParsingService {

    ExecutionRequest parseInterpreterRequest(NoteBookIN request);
}

package notebook.serviceimpl;


import notebook.data.ExecutionRequest;
import notebook.data.ExecutionResponse;
import notebook.data.NoteBookIN;
import notebook.data.NoteBookOut;
import notebook.service.InterpreterService;
import notebook.service.RequestParsingService;
import notebook.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class Controller {


    @Autowired
    private RequestParsingService requestParsingService;

    @Autowired
    private ServiceFactory interpreterServiceFactory;

    @PostMapping("/execute")
    public ResponseEntity<NoteBookOut> execute( @RequestBody NoteBookIN in, HttpSession httpSession) throws RuntimeException {
        ExecutionRequest request = requestParsingService.parseInterpreterRequest(in);
        InterpreterService interpreterService = interpreterServiceFactory.getInterpreterService(request.getLanguage());
        String sessionId = in.getSessionId() != null ? in.getSessionId() : httpSession.getId();
        request.setSessionId(sessionId);
        ExecutionResponse executionResponse = interpreterService.execute(request);
        NoteBookOut interpreterResponse = new NoteBookOut();
        interpreterResponse.setResponse(executionResponse.getOutput());
        interpreterResponse.setErrors(executionResponse.getErrors());
        interpreterResponse.setSessionId(sessionId);
        return ResponseEntity.ok(interpreterResponse);
    }
}

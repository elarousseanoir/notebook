package notebook.service.impl;


import notebook.data.ExecutionRequest;
import notebook.data.NoteBookIN;
import notebook.service.RequestParsingService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RequestParsingServiceImpl implements RequestParsingService {
    private static final String REQUEST_PATTERN = "%(\\w+)\\s+(.*)";
    private static final Pattern pattern = Pattern.compile(REQUEST_PATTERN);


    @Override
    public ExecutionRequest parseInterpreterRequest(NoteBookIN request) {
        Matcher matcher = pattern.matcher(request.getCode());
        if (matcher.matches()) {
            String language = matcher.group(1);
            String code = matcher.group(2);

            ExecutionRequest executionRequest = new ExecutionRequest();
            executionRequest.setCode(code);
            executionRequest.setLanguage(language);

            return executionRequest;
        }
        throw new RuntimeException();

    }

}

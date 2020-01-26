package notebook.serviceimpl;


import notebook.data.ExecutionRequest;
import notebook.data.NoteBookIN;
import notebook.service.impl.RequestParsingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class InterpreterRequestParsingServiceImplTest {

    private RequestParsingServiceImpl interpreterRequestParsingService;

    @Before
    public void setUp()  {
        interpreterRequestParsingService = Mockito.mock(RequestParsingServiceImpl.class);
        Mockito.when(interpreterRequestParsingService.parseInterpreterRequest(Mockito.any(NoteBookIN.class)))
                .thenCallRealMethod();
    }

    @Test
    public void parseInterpreterRequest()  {
        NoteBookIN request = new NoteBookIN();
        request.setCode("%js console.log('Hello World');");
        ExecutionRequest executionRequest = interpreterRequestParsingService.parseInterpreterRequest(request);
        assertEquals("js", executionRequest.getLanguage());
        assertEquals("console.log('Hello World');", executionRequest.getCode());
    }

    @Test(expected = RuntimeException.class)
    public void parseInvalidInterpreterRequest() throws RuntimeException {
        NoteBookIN request = new NoteBookIN();
        request.setCode(" %js console.log('Hello World');");
        interpreterRequestParsingService.parseInterpreterRequest(request);
    }

}
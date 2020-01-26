package notebook.service;

import notebook.data.Interpreter;
import notebook.exception.LanguageNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceFactory {

    private Map<Interpreter, InterpreterService> interpreterServiceMap = new EnumMap<>(Interpreter.class);

    @Autowired
    public ServiceFactory(List<InterpreterService> interpreterServices) {
        for (InterpreterService interpreterService: interpreterServices) {
            interpreterServiceMap.put(interpreterService.getInterpreterLanguage(), interpreterService);
        }
    }


    public InterpreterService getInterpreterService(String language) throws RuntimeException {
        Interpreter interpreter = Interpreter.getInterpreterFromLanguageName(language);
        if (interpreter == null || !interpreterServiceMap.containsKey(interpreter)) {
            throw new LanguageNotSupportedException();
        }
        return interpreterServiceMap.get(interpreter);
    }



}

package notebook.service;


import notebook.data.ExecutionRequest;
import notebook.data.ExecutionResponse;
import notebook.data.Interpreter;


public interface InterpreterService {


    Interpreter getInterpreterLanguage();

    ExecutionResponse execute(ExecutionRequest request);
}

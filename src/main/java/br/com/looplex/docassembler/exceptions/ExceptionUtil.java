package br.com.looplex.docassembler.exceptions;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExceptionUtil {

    private static String getStackTraceAsString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    private static List<String> stacktraceToList(String[] stacktrace) {
        return Arrays.asList(stacktrace)
                .stream()
                .map(msg -> msg.replace("\tat ", ""))
                .collect(Collectors.toList());
    }

    public static List<String> getStackTrace(Exception exception) {
        String stacktrace = getStackTraceAsString(exception);
        return stacktraceToList(stacktrace.split(System.lineSeparator()));
    }

}

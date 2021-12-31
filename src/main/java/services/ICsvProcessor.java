package services;

import java.io.IOException;
import java.util.Map;

public interface ICsvProcessor {
    Map<String, String> processCsv(String dirName,String mappingPath) throws IOException;
}
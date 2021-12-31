package utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import model.dto.CustomerMappingCsvDTO;
import model.dto.DsrcInvCsvDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utility {

    public static List<?> getDtoFromCsv(Class type, File file){
        CsvMapper csvMapper = new CsvMapper();

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(type)
                .withHeader()
                .withColumnSeparator('|')
                .withComments();

        List<?> dtos = new ArrayList<>();
        MappingIterator<DsrcInvCsvDTO> dtoItr = null;
        try {
            dtoItr = csvMapper
                    .readerWithTypedSchemaFor(type)
                    .with(csvSchema)
                    .readValues(file);

            dtos = dtoItr.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dtos;
    }

    public static List<File> getFilesFromDir(String dirName){
        List<File> result = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(dirName))) {
            result = paths
                    .filter(Files::isRegularFile)
                    .map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
     return result;
    }

    public static void generateCsv(List<?> dtos){

    }
}

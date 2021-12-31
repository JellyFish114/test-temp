package utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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

    public static <T> List<T> getDtoFromCsv(Class<?> type, File file) {
        CsvMapper csvMapper = new CsvMapper();

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(type)
                .withHeader()
                .withColumnSeparator('|')
                .withComments();

        List<T> dtos = new ArrayList<>();

        try {
            MappingIterator<T> dtoItr = csvMapper
                    .readerWithTypedSchemaFor(type)
                    .with(csvSchema)
                    .readValues(file);

            dtos = dtoItr.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dtos;
    }

    public static List<File> getFilesFromDir(String dirName) {

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

    public static void dtosToCsv(List<?> dtos, String pathOutput) {

        CsvMapper csvMapper = new CsvMapper();
        Class<?> type = dtos.get(0).getClass();

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(type)
                .withHeader()
                .withColumnSeparator('|')
                .withComments();

        try {
            SequenceWriter dtoItr = csvMapper.writerWithSchemaFor(type)
                    .with(csvSchema)
                    .writeValues(new File(pathOutput));

            dtoItr.writeAll(dtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: maybe move to an other class
    public void generatePDFs() {
        //5. create a template for the new PDFs on Doc-builder
        //6. generate the PDFs
    }
}

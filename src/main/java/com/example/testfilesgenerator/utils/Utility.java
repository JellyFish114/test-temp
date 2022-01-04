package com.example.testfilesgenerator.utils;

import com.example.testfilesgenerator.dto.DsrcInvCsvDTO;
import com.example.testfilesgenerator.dto.PdfBuilderRequestDTO;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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

    public static void createFolderIfNotPresent(String outputDir) {
        File theDir = new File(outputDir);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    public static void getPdfsFromDtos(String url, String outputDir, List<DsrcInvCsvDTO> dtos, String template) {

        for (DsrcInvCsvDTO dto : dtos) {

            PdfBuilderRequestDTO temp = new PdfBuilderRequestDTO();
            temp.setTemplate(template);
            temp.setData(dto);

            ResponseEntity<byte[]> response = new RestTemplate().postForEntity(
                    url,
                    Collections.singletonList(temp),
                    byte[].class
            );

            if (response.getBody() != null) {
                try (FileOutputStream stream = new FileOutputStream(outputDir + dto.getDocumentName())) {
                    stream.write(response.getBody());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Cannot creat PDF, response is empty !");
            }

        }
    }
}


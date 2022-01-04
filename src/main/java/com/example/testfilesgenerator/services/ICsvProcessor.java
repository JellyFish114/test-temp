package com.example.testfilesgenerator.services;

import java.util.List;

public interface ICsvProcessor {
    <T> List<T> processCsv(String inputDir, String mappingPath, String outputDir);
}
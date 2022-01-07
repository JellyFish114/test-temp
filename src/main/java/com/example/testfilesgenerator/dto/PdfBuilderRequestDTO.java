package com.example.testfilesgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfBuilderRequestDTO {

    private String template;
    private Object data;

}


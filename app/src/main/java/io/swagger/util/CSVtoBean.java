package io.swagger.util;

import com.opencsv.bean.CsvToBeanBuilder;
import io.swagger.dto.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class CSVtoBean {

    public List<Product> setCsvToBean() throws IOException {

        String fileName = "/Users/moezguer/Documents/GitFold/shopServer/app/src/main/resources/ShoppingProductData.csv";
        Path myPath = Paths.get(fileName);

        BufferedReader bufferedReader = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);

        List list = new CsvToBeanBuilder(bufferedReader)
                .withSeparator(',')
                .withType(Product.class)
                .build()
                .parse();

        return list;

    }
}

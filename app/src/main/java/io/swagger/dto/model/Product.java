package io.swagger.dto.model;

import com.opencsv.bean.CsvBindByName;
import io.swagger.enumeration.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private UUID id;

    @CsvBindByName
    private String title;

    @CsvBindByName
    private Double price;

    private Currency currency;

}

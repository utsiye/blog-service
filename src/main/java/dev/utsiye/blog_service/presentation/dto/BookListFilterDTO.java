package dev.utsiye.blog_service.presentation.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;


@Data
public class BookListFilterDTO {
    private long categoryId;

    @Size(min = 1, max = 100)
    private String author;

    @PositiveOrZero
    private int offset;

    @Min(0) @Max(100)
    private int limit;
}
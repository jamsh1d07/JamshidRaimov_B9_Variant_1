package uz.pdp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookDTO{

    @NotNull(message = "Kitobni name ni bo'lishi kerak")
    private String name;


    private String author;

    private String description;

    private Double price;

    private Boolean active=true;

    @CreationTimestamp //avtomatik yozadi
    private Timestamp createdDate;

}

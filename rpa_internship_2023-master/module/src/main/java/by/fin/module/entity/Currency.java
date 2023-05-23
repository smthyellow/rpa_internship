package by.fin.module.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currencies")
public class Currency {

    @Id
    @Column(name = "currency_id")
    @JsonProperty("Cur_ID")
    private long curID;
    @Column(name = "currency_abbreviation")
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;
    @Column(name = "currency_name")
    @JsonProperty("Cur_Name")
    private String curName;
    @Column(name="available")
    private boolean available;
}
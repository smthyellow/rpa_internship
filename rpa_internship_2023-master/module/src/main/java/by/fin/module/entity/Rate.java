package by.fin.module.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rates")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long rateId;
    @Column(name = "currency_id")
    @JsonProperty("Cur_ID")
    private long curID;
    @Column(name = "currency_date")
    @JsonProperty("Date")
    private Date date;
    @Column(name = "currency_officialRate")
    @JsonProperty("Cur_OfficialRate")
    private Double curOfficialRate;
}

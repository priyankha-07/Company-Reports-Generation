package report.generation.company_reports.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class Dates {
    private String date;
    private String month;
    private String year;
    //private String endDate;

}

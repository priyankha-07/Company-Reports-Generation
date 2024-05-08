package report.generation.company_reports.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report_generation")
public class Company {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String date;
    private double totalRevenue;
    private double profitPercentage;
}


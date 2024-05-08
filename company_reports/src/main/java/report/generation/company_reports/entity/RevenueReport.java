// RevenueReportDTO.java
package report.generation.company_reports.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import report.generation.company_reports.entity.Company;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueReport{
    private List<Company> companies;
    private Map<String, Double> sums;
}

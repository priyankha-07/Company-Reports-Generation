
package report.generation.company_reports.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report.generation.company_reports.entity.Company;
import report.generation.company_reports.entity.Dates;
import report.generation.company_reports.entity.RevenueReport;
import report.generation.company_reports.repository.CompanyRepository;
import report.generation.company_reports.service.CSVWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    @PostMapping("/add")
    public ResponseEntity<String> addRevenueRecord(@RequestBody Company company) {
        repository.save(company);
        return new ResponseEntity<>("Record added successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/get/AllData")
    public ResponseEntity<RevenueReport> getRevenueRecord(@RequestBody Dates dates) {
        List<Company> companies = repository.findByDate(dates.getStartDate(), dates.getEndDate());
        Map<String, Double> sums = repository.getSumOfTotalRevenueAndProfitPercentageForDateRange(
                dates.getStartDate(), dates.getEndDate());
        RevenueReport report = new RevenueReport(companies, sums);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

@GetMapping("/download")
public void downloadRevenueReport(@RequestParam String startDate, @RequestParam String endDate, @RequestParam(required = false) String monthYear, HttpServletResponse response) {
    List<Company> companies;
    Map<String, Double> sums;

    if (monthYear != null && !monthYear.isEmpty()) {
        String monthStart = monthYear + "-01";
        String monthEnd = monthYear + "-07"; // Assuming the last day of the month
        companies = repository.findByDate(monthStart, monthEnd);
        sums = repository.getSumOfTotalRevenueAndProfitPercentageForDateRange(monthStart, monthEnd);
    } else if (startDate.equals(endDate)) {
        companies = repository.findByDate(startDate, endDate);
        sums = repository.getSumOfTotalRevenueAndProfitPercentageForDateRange(startDate, endDate);
    } else {
        companies = repository.findByDate(startDate, endDate);
        sums = repository.getSumOfTotalRevenueAndProfitPercentageForDateRange(startDate, endDate);
    }

    try {
        response.setContentType("text/csv");
        String filename;

        if (startDate.equals(endDate)) {
            filename = "daily_report.csv";
        } else if (monthYear != null && !monthYear.isEmpty()) {
            filename = "eym_report.csv";
        } else {
            filename = "etd_report.csv";
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        CSVWriter.writeRevenueReportToOutputStream(response.getWriter(), companies, sums);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}




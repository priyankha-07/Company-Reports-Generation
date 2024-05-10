package report.generation.company_reports.controller;//
//package report.generation.company_reports.controller;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import report.generation.company_reports.entity.Company;
//import report.generation.company_reports.entity.Dates;
//import report.generation.company_reports.entity.RevenueReport;
//import report.generation.company_reports.repository.CompanyRepository;
//import report.generation.company_reports.service.CSVWriter;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/company")
//public class CompanyController {
//    @Autowired
//    private CompanyRepository repository;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addRevenueRecord(@RequestBody Company company) {
//        repository.save(company);
//        return new ResponseEntity<>("Record added successfully!", HttpStatus.CREATED);
//    }
//
//    @PostMapping("/get/AllData")
//    public ResponseEntity<RevenueReport> getRevenueRecord(@RequestBody Dates dates) {
//        List<Company> companies = repository.findByDate(dates.getStartDate(), dates.getEndDate());
//        Map<String, Double> sums = repository.getSumOfTotalRevenueAndProfitPercentageForDateRange(
//                dates.getStartDate(), dates.getEndDate());
//        RevenueReport report = new RevenueReport(companies, sums);
//        return new ResponseEntity<>(report, HttpStatus.OK);
//    }
//    @GetMapping
//    public List<Company> getCompaniesByMonthAndYear(@RequestParam String  month, @RequestParam int year) {
//        return repository.findByMonthAndYear(month, year);
//    }
//@GetMapping("/findByMonthAndYear")
//public List<Company> findByMonthAndYear(@RequestParam("month") String month, @RequestParam("year") String year) {
//        return repository.findByMonthAndYear(month, year);
//}
//    @GetMapping("/download")
//public void downloadRevenueReport(@RequestParam String startDate, @RequestParam String endDate, @RequestParam(required = false) String monthYear, HttpServletResponse response) {
//    List<Company> companies;
//    Map<String, Double> sums;
//
//    if (monthYear != null && !monthYear.isEmpty()) {
//        String monthStart = monthYear + "-01";
//        String monthEnd = monthYear + "-07"; // Assuming the last day of the month
//        companies = repository.findByDate(monthStart, monthEnd);
//        sums = repository.getSumOfTotalRevenueAndProfitPercentageForDateRange(monthStart, monthEnd);
//    } else if (startDate.equals(endDate)) {
//        companies = repository.findByDate(startDate, endDate);
//        sums = repository.getSumOfTotalRevenueAndProfitPercentageForDateRange(startDate, endDate);
//    } else {
//        companies = repository.findByDate(startDate, endDate);
//        sums = repository.getSumOfTotalRevenueAndProfitPercentageForDateRange(startDate, endDate);
//    }
//
//    try {
//        response.setContentType("text/csv");
//        String filename;
//
//        if (startDate.equals(endDate)) {
//            filename = "daily_report.csv";
//        } else if (monthYear != null && !monthYear.isEmpty()) {
//            filename = "eym_report.csv";
//        } else {
//            filename = "mtd_report.csv";
//        }
//
//        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
//
//        CSVWriter.writeRevenueReportToOutputStream(response.getWriter(), companies, sums);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}
//
//}
//
//
//

//
//    @PostMapping("/daily-report")
//    public RevenueReport getDailyReport(@RequestBody Dates dateRequest) {
//        String date = dateRequest.getDate();
//        List<Company> companies = companyRepository.findByDate(date);
//        Map<String, Double> sums = companyRepository.getSumOfTotalRevenueAndProfitPercentageForDate(date);
//        return new RevenueReport(companies, sums);
//    }
//
//    @PostMapping("/mtd-report")
//    public RevenueReport getMTDReport(@RequestBody Dates dateRequest) {
//        String endDate = dateRequest.getDate();
//        String startDate = getStartDateOfMonth(endDate);
//        List<Company> companies = companyRepository.findByDateRange(startDate, endDate);
//        Map<String, Double> sums = companyRepository.getSumOfTotalRevenueAndProfitPercentageForDateRange(startDate, endDate);
//        return new RevenueReport(companies, sums);
//    }
//
//    @PostMapping("/eom-report")
//    public RevenueReport getEOMReport(@RequestBody Dates dateRequest) {
//        String[] dateParts = dateRequest.getDate().split("-");
//        String month = dateParts[0];
//        String year = dateParts[1];
//
//        List<Company> companies = companyRepository.findByMonthAndYear(month, year);
//        Map<String, Double> sums = companyRepository.getSumOfTotalRevenueAndProfitPercentage();
//
//        return new RevenueReport(companies, sums);
//    }
//
//
//    private String getStartDateOfMonth(String endDate) {
//        return "01-" + endDate.substring(3); // Assuming date format is dd-mm-yyyy
//    }
//
//}
//
//
//
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import report.generation.company_reports.entity.Company;
import report.generation.company_reports.entity.Dates;
import report.generation.company_reports.repository.CompanyRepository;
import service.CsvGenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;



     @PostMapping("/daily-report")
        public void getDailyReport(@RequestBody Dates dateRequest, HttpServletResponse response) throws IOException {
            String date = dateRequest.getDate();
            List<Company> companies = companyRepository.findByDate(date);
            Map<String, Double> sums = companyRepository.getSumOfTotalRevenueAndProfitPercentageForDate(date);
            response.setHeader("Content-Type", "text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"daily-report.csv\"");
            PrintWriter writer = response.getWriter();
            CsvGenerator.generateCsv(companies, sums, writer);
        }

       @PostMapping("/mtd-report")
         public void getMTDReport(@RequestBody Dates dateRequest, HttpServletResponse response) throws IOException {
          String endDate = dateRequest.getDate();
          String startDate = getStartDateOfMonth(endDate);
          List<Company> companies = companyRepository.findByDateRange(startDate, endDate);
          Map<String, Double> sums = companyRepository.getSumOfTotalRevenueAndProfitPercentageForDateRange(startDate, endDate);
          response.setContentType("text/csv");
          response.setHeader("Content-Disposition", "attachment; filename=mtd-report.csv");
          PrintWriter writer = response.getWriter();
         CsvGenerator.generateCsv(companies, sums, writer);
}

        @PostMapping("/eom-report")
        public void getEOMReport(@RequestBody Dates dateRequest, HttpServletResponse response) throws IOException {
            String[] dateParts = dateRequest.getDate().split("-");
            String month = dateParts[0];
            String year = dateParts[1];

            List<Company> companies = companyRepository.findByMonthAndYear(month, year);
            Map<String, Double> sums = companyRepository.getSumOfTotalRevenueAndProfitPercentage();
            response.setHeader("Content-Type", "text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"eom-report.csv\"");
            PrintWriter writer = response.getWriter();
            CsvGenerator.generateCsv(companies, sums, writer);
        }

        private String getStartDateOfMonth(String endDate) {
            return "01-" + endDate.substring(3); // Assuming date format is dd-mm-yyyy
        }
    }

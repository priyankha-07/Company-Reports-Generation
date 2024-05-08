package report.generation.company_reports.service;

import report.generation.company_reports.entity.Company;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CSVWriter {
    public static void writeRevenueReportToOutputStream(PrintWriter writer, List<Company> companies, Map<String, Double> sums) {

        writer.println("Company Name,Date,Total Revenue,Profit Percentage,Sum of Total Revenue,Sum of Profit Percentage");


        for (Company company : companies) {
            writer.println(String.join(",", company.getName(), company.getDate(),
                    String.valueOf(company.getTotalRevenue()), String.valueOf(company.getProfitPercentage()),
                    String.valueOf(sums.get("sumOfTotalRevenue")), String.valueOf(sums.get("sumOfProfitPercentage")))); // Use the keys to get sums
        }

    }
}

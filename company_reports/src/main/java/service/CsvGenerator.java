//package service;
//
//import report.generation.company_reports.entity.Company;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import java.util.Map;
//
//    public class CsvGenerator {
//        public static void generateCsv(List<Company> companies, Map<String, Double> sums, PrintWriter writer) throws IOException {
//            // Write CSV header
//            writer.println("Id,Date, Company Name, TotalRevenue, Profit Percentage");
//
//            // Write data to CSV
//            for (Company company : companies) {
//                writer.println(company.getId()+"," +company.getDate() +","+company.getName() + ", " + company.getTotalRevenue()+ ", " + company.getProfitPercentage());
//            }
//
//            writer.println("Total Revenue, " + sums.get("totalRevenue"));
//            writer.println("Total Profit Percentage, " + sums.get("profitPercentage"));
//
//            writer.flush();
//            writer.close();
//        }
//    }
//
//
//
package service;

import report.generation.company_reports.entity.Company;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CsvGenerator {
    public static void generateCsv(List<Company> companies, Map<String, Double> sums, PrintWriter writer) throws IOException {
        writer.println("Id,Date, Company Name, TotalRevenue, Profit Percentage");
        double totalRevenue = 0.0;
        double totalProfitPercentage = 0.0;
        int count = companies.size();


        for (Company company : companies) {
            writer.println(company.getId() + "," + company.getDate() + "," + company.getName() + ", " + company.getTotalRevenue() + ", " + company.getProfitPercentage());
            totalRevenue += company.getTotalRevenue();
            totalProfitPercentage += company.getProfitPercentage();

        }
        writer.println("Total Revenue, " + totalRevenue);
        writer.println("Total Profit Percentage, " + totalProfitPercentage);
        writer.println("Total Records, " + count);

        writer.flush();
        writer.close();
    }
}

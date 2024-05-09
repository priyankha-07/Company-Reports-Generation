package report.generation.company_reports.repository;//package report.generation.company_reports.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import report.generation.company_reports.entity.Company;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public interface CompanyRepository extends JpaRepository<Company , Integer> {
//    @Query("SELECT c FROM Company c where c.date between :startDate and :endDate")
//    List<Company> findByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);
//
//    @Query("SELECT SUM(c.totalRevenue) AS sumOfTotalRevenue, SUM(c.profitPercentage) AS sumOfProfitPercentage FROM Company c " +
//            "WHERE c.date BETWEEN :startDate AND :endDate")
//    Map<String, Double> getSumOfTotalRevenueAndProfitPercentageForDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
//
////    @Query("SELECT c FROM Company c " +
////            "WHERE FUNCTION('YEAR', c.date) = :year " +
////            "AND FUNCTION('MONTH', c.date) = :month")
////    List<Company> findByMonthAndYear(@Param("month") String month, @Param("year") String year);
////
//@Query("SELECT c FROM Company c " +
//        "WHERE EXTRACT(YEAR FROM TO_DATE(c.date, 'DD-MM-YYYY')) = :year " +
//        "AND EXTRACT(MONTH FROM TO_DATE(c.date, 'DD-MM-YYYY')) = :month")
//List<Company> findByMonthAndYear(@Param("month") String month, @Param("year") String year);
//}
//
//
//
//
////    @Query("SELECT SUM(c.totalRevenue) AS sumOfTotalRevenue, SUM(c.profitPercentage) AS sumOfProfitPercentage FROM Company c")
////    Map<String, Double> getSumOfTotalRevenueAndProfitPercentage();
//
//

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import report.generation.company_reports.entity.Company;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// below code refers to send date in single paramter
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE c.date = :date")
    List<Company> findByDate(@Param("date") String date);

    @Query("SELECT SUM(c.totalRevenue) AS sumOfTotalRevenue, SUM(c.profitPercentage) AS sumOfProfitPercentage FROM Company c " +
            "WHERE c.date = :date")
    Map<String, Double> getSumOfTotalRevenueAndProfitPercentageForDate(@Param("date") String date);

    @Query("SELECT c FROM Company c " +
            "WHERE c.date BETWEEN :startDate AND :endDate")
    List<Company> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT SUM(c.totalRevenue) AS sumOfTotalRevenue, SUM(c.profitPercentage) AS sumOfProfitPercentage FROM Company c " +
            "WHERE c.date BETWEEN :startDate AND :endDate")
    Map<String, Double> getSumOfTotalRevenueAndProfitPercentageForDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT SUM(c.totalRevenue) AS sumOfTotalRevenue, SUM(c.profitPercentage) AS sumOfProfitPercentage FROM Company c")
    Map<String, Double> getSumOfTotalRevenueAndProfitPercentage();

    @Query("SELECT c FROM Company c " +
            "WHERE EXTRACT(YEAR FROM TO_DATE(c.date, 'DD-MM-YYYY')) = :year " +
            "AND EXTRACT(MONTH FROM TO_DATE(c.date, 'DD-MM-YYYY')) = :month")
    List<Company> findByMonthAndYear(@Param("month") String month, @Param("year") String year);


}

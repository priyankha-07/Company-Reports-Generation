package report.generation.company_reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import report.generation.company_reports.entity.Company;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

@Repository
public interface CompanyRepository extends JpaRepository<Company , Integer> {
    @Query("SELECT c FROM Company c where c.date between :startDate and :endDate")
    List<Company> findByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT SUM(c.totalRevenue) AS sumOfTotalRevenue, SUM(c.profitPercentage) AS sumOfProfitPercentage FROM Company c " +
                "WHERE c.date BETWEEN :startDate AND :endDate")
    Map<String, Double> getSumOfTotalRevenueAndProfitPercentageForDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
    }


//    @Query("SELECT SUM(c.totalRevenue) AS sumOfTotalRevenue, SUM(c.profitPercentage) AS sumOfProfitPercentage FROM Company c")
//    Map<String, Double> getSumOfTotalRevenueAndProfitPercentage();



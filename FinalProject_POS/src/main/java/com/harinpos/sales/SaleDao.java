package com.harinpos.sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.harinpos.statistics.*;

@Component
@Repository
public class SaleDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public SaleDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 * 판매 정보를 추가합니다.
	 * 
	 * @param sale 추가할 판매 정보 객체
	 */
	public void insertSale(final Sale sale) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO sales (CODE, sale_date, sale_quantity, sales, productName) VALUES (?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, sale.getCode());
				pstmt.setTimestamp(2, Timestamp.valueOf(sale.getSale_date()));
				pstmt.setInt(3, sale.getSale_quantity());
				pstmt.setDouble(4, sale.getSales());
				pstmt.setString(5, sale.getProductName());
				return pstmt;
			}
		}, keyHolder);
	}

	/**
	 * 
	 * 특정 판매 코드에 해당하는 판매 정보를 삭제합니다.
	 * 
	 * @param code 삭제할 판매 코드
	 */
	public void deleteSale(int code) {
		String sql = "DELETE FROM Sales WHERE code = ?";
		jdbcTemplate.update(sql, code);
	}

	/**
	 * 
	 * 일별 판매량과 매출을 조회합니다.
	 * 
	 * @return 일별 판매 정보 리스트
	 */
	public List<DailySale> getDailySales() {
		String sql = "SELECT p.productName, IFNULL(s.dailyQuantity, 0) AS dailyQuantity, IFNULL(s.dailySales, 0) AS dailySales "
				+ "FROM ( " + "SELECT productName, SUM(sale_quantity) AS dailyQuantity, SUM(sales) AS dailySales "
				+ "FROM sales " + "WHERE DATE(sale_date) = CURDATE() " + "GROUP BY productName " + ") s "
				+ "RIGHT JOIN ( " + "SELECT DISTINCT productName " + "FROM sales "
				+ ") p ON p.productName = s.productName " + "ORDER BY p.productName";

		RowMapper<DailySale> rowMapper = new BeanPropertyRowMapper<>(DailySale.class);
		return jdbcTemplate.query(sql, rowMapper);
	}

	/**
	 * 
	 * 특정 날짜의 일별 판매량과 매출을 조회합니다.
	 * 
	 * @param selectedDate 조회할 날짜 (yyyy-MM-dd 형식)
	 * 
	 * @return 일별 판매 정보 리스트
	 */
	public List<DailySale> getDailySalesByDate(String selectedDate) {
		String sql = "SELECT p.productName, IFNULL(s.dailyQuantity, 0) AS dailyQuantity, IFNULL(s.dailySales, 0) AS dailySales "
				+ "FROM ( " + "SELECT productName, SUM(sale_quantity) AS dailyQuantity, SUM(sales) AS dailySales "
				+ "FROM sales " + "WHERE DATE(sale_date) = ? " + "GROUP BY productName " + ") s " + "RIGHT JOIN ( "
				+ "SELECT DISTINCT productName " + "FROM sales " + ") p ON p.productName = s.productName "
				+ "ORDER BY p.productName";

		RowMapper<DailySale> rowMapper = new BeanPropertyRowMapper<>(DailySale.class);
		return jdbcTemplate.query(sql, rowMapper, selectedDate);
	}

	/**
	 * 
	 * 주간 판매량과 매출을 조회합니다.
	 * 
	 * @return 주간 판매 정보 리스트
	 */
	public List<DailySale> getWeeklySales() {
		String sql = "SELECT p.name, IFNULL(s.dailyQuantity, 0) AS dailyQuantity, IFNULL(s.dailySales, 0) AS dailySales "
				+ "FROM product p " + "LEFT JOIN ( "
				+ "SELECT productName, SUM(sale_quantity) AS dailyQuantity, SUM(sales) AS dailySales " + "FROM sales "
				+ "WHERE YEARWEEK(sale_date) = YEARWEEK(CURDATE()) " + "GROUP BY productName "
				+ ") s ON p.name = s.productName " + "ORDER BY s.productName";
		RowMapper<DailySale> rowMapper = new BeanPropertyRowMapper<>(DailySale.class);
		return jdbcTemplate.query(sql, rowMapper);
	}

	/**
	 * 
	 * 특정 날짜의 주간 판매량과 매출을 조회합니다.
	 * 
	 * @param selectedDate 조회할 날짜 (yyyy-MM-dd 형식)
	 * 
	 * @return 주간 판매 정보 리스트
	 */
	public List<DailySale> getWeeklySalesByDate(String selectedDate) {
		String sql = "SELECT p.name, IFNULL(s.dailyQuantity, 0) AS dailyQuantity, IFNULL(s.dailySales, 0) AS dailySales "
				+ "FROM product p " + "LEFT JOIN ( "
				+ "SELECT productName, SUM(sale_quantity) AS dailyQuantity, SUM(sales) AS dailySales " + "FROM sales "
				+ "WHERE YEARWEEK(sale_date) = YEARWEEK(?) " + "GROUP BY productName "
				+ ") s ON p.name = s.productName " + "ORDER BY s.productName";

		RowMapper<DailySale> rowMapper = new BeanPropertyRowMapper<>(DailySale.class);
		return jdbcTemplate.query(sql, rowMapper, selectedDate);
	}

	/**
	 * 
	 * 월별 판매량과 매출을 조회합니다.
	 * 
	 * @return 월별 판매 정보 리스트
	 */
	public List<DailySale> getMonthlySales() {
		String sql = "SELECT p.productName, IFNULL(s.dailyQuantity, 0) AS dailyQuantity, IFNULL(s.dailySales, 0) AS dailySales "
				+ "FROM ( " + "SELECT productName, SUM(sale_quantity) AS dailyQuantity, SUM(sales) AS dailySales "
				+ "FROM sales " + "WHERE YEAR(sale_date) = YEAR(CURDATE()) AND MONTH(sale_date) = MONTH(CURDATE()) "
				+ "GROUP BY productName " + ") s " + "RIGHT JOIN ( " + "SELECT DISTINCT productName " + "FROM sales "
				+ ") p ON p.productName = s.productName " + "ORDER BY p.productName";
		RowMapper<DailySale> rowMapper = new BeanPropertyRowMapper<>(DailySale.class);
		return jdbcTemplate.query(sql, rowMapper);
	}

	/**
	 * 
	 * 특정 날짜의 월별 판매량과 매출을 조회합니다.
	 * 
	 * @param selectedDate 조회할 날짜 (yyyy-MM-dd 형식)
	 * 
	 * @return 월별 판매 정보 리스트
	 */
	public List<DailySale> getMonthlySalesByDate(String selectedDate) {
		String sql = "SELECT p.productName, IFNULL(s.dailyQuantity, 0) AS dailyQuantity, IFNULL(s.dailySales, 0) AS dailySales "
				+ "FROM ( " + "SELECT productName, SUM(sale_quantity) AS dailyQuantity, SUM(sales) AS dailySales "
				+ "FROM sales " + "WHERE YEAR(sale_date) = YEAR(?) AND MONTH(sale_date) = MONTH(?) "
				+ "GROUP BY productName " + ") s " + "RIGHT JOIN ( " + "SELECT DISTINCT productName " + "FROM sales "
				+ ") p ON p.productName = s.productName " + "ORDER BY p.productName";

		RowMapper<DailySale> rowMapper = new BeanPropertyRowMapper<>(DailySale.class);
		return jdbcTemplate.query(sql, rowMapper, selectedDate, selectedDate);
	}

}

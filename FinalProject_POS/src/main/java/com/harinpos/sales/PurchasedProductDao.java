package com.harinpos.sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.harinpos.product.Product;

@Component
@Repository
public class PurchasedProductDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public PurchasedProductDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 모든 구매하려는 제품 정보를 조회합니다.
	 *
	 * @return 구매하려는 제품 정보 목록
	 */
	public List<PurchasedProduct> selectAll() {
		List<PurchasedProduct> results = jdbcTemplate.query("select * from PurchasedProduct ORDER BY code ASC",
				(ResultSet rs, int rowNum) -> {
					PurchasedProduct purchasedProduct = new PurchasedProduct(rs.getInt("ID"), rs.getInt("CODE"),
							rs.getString("productName"), rs.getDouble("PRICE"), rs.getInt("QUANTITY"),
							rs.getTimestamp("date").toLocalDateTime());
					return purchasedProduct;
				});
		return results;
	}

	/**
	 * 구매하려는 제품 정보를 데이터베이스에 삽입합니다.
	 *
	 * @param purchasedProduct 삽입할 구매하려는 제품 정보
	 */
	public void insertPP(final PurchasedProduct purchasedProduct) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO PurchasedProduct (CODE, PRICE, QUANTITY, date, productName) VALUES (?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, purchasedProduct.getCode());
				pstmt.setDouble(2, purchasedProduct.getPrice());
				pstmt.setInt(3, purchasedProduct.getQuantity());
				pstmt.setTimestamp(4, Timestamp.valueOf(purchasedProduct.getDate()));
				pstmt.setString(5, purchasedProduct.getName());
				return pstmt;
			}
		}, keyHolder);
	}

	/**
	 * 지정된 ID를 가진 구매하려는 제품 정보를 데이터베이스에서 삭제합니다.
	 *
	 * @param id 삭제할 구매하려는 제품 정보의 ID
	 */
	public void deletePP(int id) {
		String sql = "DELETE FROM PurchasedProduct WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	/**
	 * 모든 구매하려는 제품 정보를 데이터베이스에서 삭제합니다.
	 */
	public void deleteAll() {
		String sql = "DELETE FROM PurchasedProduct";
		jdbcTemplate.update(sql);
	}
}

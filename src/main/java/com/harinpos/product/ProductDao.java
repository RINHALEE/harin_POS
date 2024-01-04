package com.harinpos.product;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ProductDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ProductDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 모든 상품 정보를 조회하는 메서드입니다.
	 *
	 * @return 모든 상품 정보를 담고 있는 Product 객체의 리스트
	 */
	public List<Product> selectAll() {
		List<Product> results = jdbcTemplate.query("select * from Product ORDER BY code ASC",
				(ResultSet rs, int rowNum) -> {
					Product product = new Product(rs.getInt("CODE"), rs.getDouble("PRICE"), rs.getInt("QUANTITY"),
							rs.getString("NAME"));
					return product;
				});
		return results;
	}

	/**
	 * 상품을 등록하는 메서드입니다.
	 *
	 * @param product 등록할 상품 정보를 담고 있는 Product 객체
	 */
	public void insert(final Product product) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO Product (CODE, PRICE, QUANTITY, NAME) VALUES (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, product.getCode());
				pstmt.setDouble(2, product.getPrice());
				pstmt.setInt(3, product.getQuantity());
				pstmt.setString(4, product.getName());
				return pstmt;
			}
		}, keyHolder);
	}

	/**
	 * 상품을 업데이트하는 메서드입니다.
	 *
	 * @param code     업데이트할 상품의 코드
	 * @param quantity 업데이트할 상품의 수량
	 * @param name     업데이트할 상품의 이름
	 * @param price    업데이트할 상품의 가격
	 */
	public void updateProduct(int code, int quantity, String name, double price) {
		String sql = "UPDATE Product SET quantity = ?, name = ?, price = ? WHERE code = ?";
		jdbcTemplate.update(sql, quantity, name, price, code);
	}

	/**
	 * 상품을 삭제하는 메서드입니다.
	 *
	 * @param code 삭제할 상품의 코드
	 */
	public void deleteProduct(int code) {
		String sql = "DELETE FROM Product WHERE code = ?";
		jdbcTemplate.update(sql, code);
	}

	/**
	 * 상품 이름으로 상품을 검색하는 메서드입니다.
	 *
	 * @param name 검색할 상품의 이름
	 * @return 검색된 상품 정보를 담고 있는 Product 객체의 리스트
	 */
	public List<Product> selectByProductName(String name) {
		String sql = "SELECT * FROM Product WHERE name LIKE ?";
		List<Product> results = jdbcTemplate.query(sql, new Object[] { name }, new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				int code = rs.getInt("code");
				int quantity = rs.getInt("quantity");
				String productName = rs.getString("name");
				double price = rs.getDouble("price");

				Product product = new Product(code, price, quantity, productName);
				return product;
			}
		});

		return results;
	}

	/**
	 * 상품 코드로 상품을 검색하는 메서드입니다.
	 *
	 * @param code 검색할 상품의 코드
	 * @return 검색된 상품 정보를 담고 있는 Product 객체의 리스트
	 */
	public List<Product> selectByProductCode(int code) {
		String sql = "SELECT * FROM Product WHERE code = ?";
		List<Product> results = jdbcTemplate.query(sql, new Object[] { code }, new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				int code = rs.getInt("code");
				int quantity = rs.getInt("quantity");
				String productName = rs.getString("name");
				double price = rs.getDouble("price");

				Product product = new Product(code, price, quantity, productName);
				return product;
			}
		});

		return results;
	}

	/**
	 * 상품 코드로 상품의 수량을 조회하는 메서드입니다.
	 *
	 * @param code 조회할 상품의 코드
	 * @return 상품의 수량
	 */
	public int selectQuantity(int code) {
		String sql = "SELECT quantity FROM Product WHERE code = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, code);
	}

	/**
	 * 상품 코드로 상품이 존재하는지 여부를 확인하는 메서드입니다.
	 *
	 * @param code 확인할 상품의 코드
	 * @return 상품이 존재하는지 여부 (true: 존재함, false: 존재하지 않음)
	 */
	public boolean existsByCode(int code) {
		String sql = "SELECT COUNT(*) FROM Product WHERE code = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, code);
		return count > 0;
	}

	/**
	 * 상품 입고 후 재고를 업데이트하는 메서드입니다.
	 *
	 * @param code           상품 코드
	 * @param beforeQuantity 입고 전 상품 수량
	 * @param afterQuantity  입고 후 상품 수량
	 */
	public void updateAddProduct(int code, int BeforeQuantity, int AfterQuantity) {
		String sql = "UPDATE Product SET quantity = ? WHERE code = ?";
		jdbcTemplate.update(sql, BeforeQuantity + AfterQuantity, code);
	}

}

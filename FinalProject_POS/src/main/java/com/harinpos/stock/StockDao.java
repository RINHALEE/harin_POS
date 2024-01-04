package com.harinpos.stock;

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

@Component
@Repository
public class StockDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public StockDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 모든 재고 정보를 조회합니다.
	 *
	 * @return 재고 정보 목록
	 */
	public List<Stock> selectStockAll() {
		List<Stock> results = jdbcTemplate.query(
				"SELECT s.CODE, s.receive_date, s.receive_quantity, p.name " + "FROM Stock s "
						+ "JOIN Product p ON s.CODE = p.CODE " + "ORDER BY s.receive_date DESC",
				(ResultSet rs, int rowNum) -> {
					Stock stock = new Stock(rs.getInt("CODE"), rs.getTimestamp("receive_date").toLocalDateTime(),
							rs.getInt("receive_quantity"));
					stock.setProductName(rs.getString("name")); // 제품명 설정
					return stock;
				});
		return results;
	}

	/**
	 * 재고 정보를 데이터베이스에 삽입합니다.
	 *
	 * @param stock 삽입할 재고 정보
	 */
	public void insert(final Stock stock) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO Stock (CODE, RECEIVE_DATE, RECEIVE_QUANTITY) VALUES (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, stock.getCode());
				pstmt.setTimestamp(2, Timestamp.valueOf(stock.getReceive_date()));
				pstmt.setInt(3, stock.getReceive_quantity());
				return pstmt;
			}
		}, keyHolder);
	}

	/**
	 * 지정된 코드를 가진 재고 정보를 데이터베이스에서 삭제합니다.
	 *
	 * @param code 삭제할 재고 정보의 코드
	 */
	public void deleteStock(int code) {
		String sql = "DELETE FROM Stock WHERE code = ?";
		jdbcTemplate.update(sql, code);
	}
}

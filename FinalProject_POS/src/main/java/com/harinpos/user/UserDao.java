package com.harinpos.user;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class UserDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 모든 사용자 정보를 조회하는 메서드입니다.
	 *
	 * @return 사용자 정보 목록
	 */
	public List<User> selectAll() {
		List<User> results = jdbcTemplate.query("select * from User", (ResultSet rs, int rowNum) -> {
			User user = new User(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("POSITION"),
					rs.getString("NAME"));
			return user;
		});
		return results;
	}

	/**
	 * 특정 사용자 ID에 해당하는 사용자 정보를 조회하는 메서드입니다.
	 *
	 * @param id 조회할 사용자 ID
	 * @return 사용자 정보 객체
	 */
	public User selectById(String id) {
		List<User> results = jdbcTemplate.query("select * from User where id = ?", new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User member = new User(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("POSITION"),
						rs.getString("NAME"));
				return member;
			}
		}, id);
		return results.isEmpty() ? null : results.get(0);
	}

	/**
	 * 사용자 정보를 추가하는 메서드입니다.
	 *
	 * @param user 추가할 사용자 정보 객체
	 */
	public void insert(final User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO User (ID, PASSWORD, POSITION, NAME) VALUES (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getPosition());
				pstmt.setString(4, user.getName());
				return pstmt;
			}
		}, keyHolder);
	}

	/**
	 * 사용자 정보를 업데이트하는 메서드입니다.
	 *
	 * @param id       업데이트할 사용자 ID
	 * @param password 업데이트할 비밀번호
	 * @param position 업데이트할 직책
	 * @param name     업데이트할 이름
	 */
	public void updateUser(String id, String password, String position, String name) {
		String sql = "UPDATE User SET password = ?, position = ?, name = ? WHERE id = ?";
		jdbcTemplate.update(sql, password, position, name, id);
	}

	/**
	 * 특정 사용자 ID에 해당하는 사용자 정보를 삭제하는 메서드입니다.
	 *
	 * @param id 삭제할 사용자 ID
	 */
	public void deleteUser(String id) {
		String sql = "DELETE FROM User WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

}

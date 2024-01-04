package com.harinpos.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	@Autowired
	private UserDao userDao;

	/**
	 * UserService 생성자입니다.
	 *
	 * @param userDao UserDao 객체
	 */
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 새로운 회원을 등록하는 메서드입니다.
	 *
	 * @param req 등록할 회원 정보를 담고 있는 RegisterRequest 객체
	 * @return 등록된 회원의 ID
	 * @throws DuplicateMemberException 중복된 회원이 이미 존재하는 경우 발생하는 예외
	 */
	public String regist(RegisterRequest req) throws DuplicateMemberException {
		User user = userDao.selectById(req.getId());
		if (user != null) {
			throw new DuplicateMemberException("중복된 회원입니다.");
		}
		User newMember = new User(req.getId(), req.getPassword(), req.getPosition(), req.getName());
		userDao.insert(newMember);
		return newMember.getId();
	}

}

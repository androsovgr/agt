package ru.mephi.agt.service.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import ru.mephi.agt.model.User;

public class UserMapper {

	public static User parse(ResultSet rs) throws SQLException {
		User user = null;
		if (rs.next()) {
			user = new User();
			user.setUserId(rs.getLong("user_id"));
			user.setPassword(rs.getString("password"));
			user.setNickName(rs.getString("nickname"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			Timestamp birthTimestamp = rs.getTimestamp("birth_date");
			Date birthDate = birthTimestamp == null ? null : new Date(
					birthTimestamp.getTime());
			user.setBirthDate(birthDate);
			user.setCity(rs.getString("city"));
			user.setCountry("country");
			user.setPhone("phone");
			// TODO: gender
			// Gender.valueOf(arg0)
			// user.setGender(gender);
		}
		return user;
	}
}

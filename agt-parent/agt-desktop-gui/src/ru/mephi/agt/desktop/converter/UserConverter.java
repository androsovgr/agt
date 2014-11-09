package ru.mephi.agt.desktop.converter;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import ru.mephi.agt.desktop.model.UserModel;
import ru.mephi.agt.model.User;

public class UserConverter {

	public static UserModel convertToGuiModel(User user) {
		UserModel userModel = null;

		if (user != null) {
			userModel = new UserModel();
			userModel.setId(user.getUserId());
			userModel.setFirstName(user.getFirstName());
			userModel.setLastName(user.getLastName());
			userModel.setNick(user.getNickName());
			userModel.setBirthDate(user.getBirthDate() == null ? null : user
					.getBirthDate().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate());
			userModel.setGender(user.getGender());
			userModel.setCity(user.getCity());
			userModel.setCountry(user.getCountry());
		}

		return userModel;
	}

	public static List<UserModel> convertToGuiModelList(List<User> users) {
		List<UserModel> userModels = null;

		if (users != null) {
			userModels = new ArrayList<UserModel>();
			for (User user : users) {
				userModels.add(convertToGuiModel(user));
			}
		}

		return userModels;
	}
}

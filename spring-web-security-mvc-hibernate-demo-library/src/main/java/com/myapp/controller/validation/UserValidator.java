package com.myapp.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myapp.library.entity.LibraryUser;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LibraryUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LibraryUser user = (LibraryUser) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.user");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.user");

		if (user.getPassword() != null && user.getConfirmPassword() != null) {

			if (!(user.getPassword().equals(user.getConfirmPassword()))) {
				errors.rejectValue("password", "NotEmpty.user.password.password_mismatch");
				errors.rejectValue("confirmPassword", "NotEmpty.user.confirmPassword.password_mismatch");
			}

		}

		if (user.getRoles() == null || (user.getRoles() != null && (user.getRoles().getId() == 9999))) {
			errors.rejectValue("roles.name", "NotEmpty.user.roles");
		}
	}

}

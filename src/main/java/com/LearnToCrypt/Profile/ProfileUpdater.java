package com.LearnToCrypt.Profile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

public class ProfileUpdater implements IUpdateProfile {

    private static final Logger logger = LogManager.getLogger(ProfileUpdater.class);
    private IProfileValidator validator;
    private IUserNameChanger nameChanger;
    private IPasswordChanger passwordChanger;

    public ProfileUpdater(IProfileValidator validator,
                          IUserNameChanger nameChanger,
                          IPasswordChanger passwordChanger) {
        this.validator = validator;
        this.nameChanger = nameChanger;
        this.passwordChanger = passwordChanger;
    }

    @Override
    public void update(IUserProfileBridge profile,
                       String newName,
                       String newPassword,
                       String confirmPassword) throws ValidationException, SQLException {
        if(null != newName &&
                !newName.equals(profile.getUserName()) &&
                !newName.equals("")) {
            logger.info("Processing Request to update username to " + newName);
            validator.validateName(newName);
            nameChanger.changeName(profile.getEmail(), newName);
            logger.info("Name Changed Successfully");
        }
        if(null != newPassword && !newPassword.equals("")) {
            logger.info("Processing request to update password");
            validator.validatePassword(newPassword, confirmPassword);
            passwordChanger.changePassword(profile.getEmail(), newPassword);
            logger.info("Password Changed Successfully");
        }
    }
}

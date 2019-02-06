package interfaces;

import bean.BasicUserInfo;
import bean.RestrictUserInfo;
import bean.UserInfoRegister;
import entity.*;
import exceptions.*;

import java.sql.SQLException;

public interface UserProfileService {

    BasicUserInfo getBasicUserInfo(Nickname nk) throws UserNotExistEx;

    RestrictUserInfo getRestrictedUserInfo(Nickname nk) throws UserNotExistEx;

    Boolean doesNicknameExist(Nickname nk) throws UserNotExistEx;

    Boolean doesTaxCodeExist(TaxCode taxCode)throws UserNotExistEx;

    void createUser(Nickname nk, UserInfoRegister dataCreate) throws WrongParameters, ClassNotFoundException;

    void cancelUser(Nickname nk) throws UserNotExistEx;

    void deleteUser(Nickname nk)throws UserNotExistEx;

}

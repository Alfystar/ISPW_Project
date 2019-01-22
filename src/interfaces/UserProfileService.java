package interfaces;

import bean.BasicUserInfo;
import bean.RestrictUserInfo;
import bean.UserInfoRegister;
import entity.Nickname;

public interface UserProfileService {

    BasicUserInfo getBasicUserInfo(Nickname nk);

    RestrictUserInfo getRestrictedUserInfo(Nickname nk);

    Boolean doesNicknameExist(Nickname nk);

    Boolean doesTaxCodeExist(Nickname nk);

    void createUser(Nickname nk, UserInfoRegister dataCreate);

    void cancelUser(Nickname nk);

    void deleteUser(Nickname nk);

}

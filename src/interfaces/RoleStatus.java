package interfaces;

import entity.Nickname;
import entity.Roles;
import entity.UserStatus;
import exceptions.UserNotExistEx;

import java.sql.SQLException;

public interface RoleStatus {


    Roles getRoles(Nickname nk) throws UserNotExistEx;

    UserStatus getStatus(Nickname nk)  throws UserNotExistEx;

    void makeARenter(Nickname nk)throws UserNotExistEx, SQLException, ClassNotFoundException;

    void removeRentership(Nickname nk) throws UserNotExistEx, SQLException, ClassNotFoundException;

    void makeATenant(Nickname nk) throws UserNotExistEx, SQLException, ClassNotFoundException;

    void removeTenantship(Nickname nk) throws UserNotExistEx, SQLException,ClassNotFoundException;

    void changeUserStatus(Nickname nk, UserStatus stat) throws UserNotExistEx, SQLException, ClassNotFoundException ;

    Boolean isRenter(Nickname nk) throws UserNotExistEx;

    Boolean isTenant(Nickname nk)throws UserNotExistEx;

    Boolean isActive(Nickname nk)throws UserNotExistEx;

    Boolean isInactive(Nickname nk)throws UserNotExistEx;

    Boolean isCancelled(Nickname nk)throws UserNotExistEx;

    Boolean isBanned(Nickname nk) throws  UserNotExistEx;

}

package interfaces;

import entity.Nickname;
import entity.Roles;
import entity.UserStatus;

public interface RoleStatus {


    Roles getRoles(Nickname nk);

    UserStatus getStatus(Nickname nk);

    void makeARenter(Nickname nk);

    void removeRentership(Nickname nk);

    void makeATenant(Nickname nk);

    void removeTenantship(Nickname nk);

    void changeUserStatus(Nickname nk, UserStatus stat);

    Boolean isRenter(Nickname nk);

    Boolean isTenant(Nickname nk);

    Boolean isActive(Nickname nk);

    Boolean isInactive(Nickname nk);

    Boolean isCanceled(Nickname nk);

    Boolean isBanned(Nickname nk);

}

package externalBean;

import externalControl.ChangeDataControl;

import static java.lang.Boolean.FALSE;

public class ChangeDataBean {
    // todo:decidere quali dati lasciare (in accordo con ChangeNotAnagraphicData)
    private String firstname;
    private String lastname;
    private String taxcode;
    private String email;
    private String bday;
    private String gender;
    private String roles;
    private String socialStatus;
    private String phoneNumber;
    private String address;
    private String birthPlace;
    private String nationality;
    private String newPW;
    private String confirmPW;

    public ChangeDataBean() {
        this.firstname = "";
        this.lastname = "";
        this.taxcode = "";
        this.email = "";
        this.bday = "";
        this.gender = "";
        this.roles = "";
        this.socialStatus = "";
        this.phoneNumber = "";
        this.address = "";
        this.birthPlace = "";
        this.nationality = "";
        this.newPW = "";
        this.confirmPW = "";
    }

    public void setFirstname(String fn) {
        this.firstname = fn;
    }
    public String getFirstname() {
        return this.firstname;
    }

    public void setLastname(String ln) {
        this.lastname = ln;
    }
    public String getLastname() {
        return this.lastname;
    }

    public void setTaxcode(String tc) { this.taxcode = tc; }
    public String getTaxcode() {
        return this.taxcode;
    }

    public void setEmail(String em) {
        this.email = em;
    }
    public String getEmail() {
        return this.email;
    }

    public void setBday(String bd) {
        this.bday = bd;
    }
    public String getBday() {
        return this.bday;
    }

    public void setGender(String gen) {
        this.gender = gen;
    }
    public String getGender() {
        return this.gender;
    }

    public void setRoles(String rol) {
        this.roles = rol;
    }
    public String getRoles() {
        return this.roles;
    }

    public void setSocialStatus(String socStat) {
        this.socialStatus = socStat;
    }
    public String getSocialStatus() {
        return this.socialStatus;
    }

    public void setPhoneNumber(String phN) {
        this.phoneNumber = phN;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setAddress(String addr) {
        this.address = addr;
    }
    public String getAddress() {
        return this.address;
    }

    public void setBirthPlace(String birPl) {
        this.birthPlace = birPl;
    }
    public String getBirthPlace() { return this.birthPlace; }

    public void setNationality(String nat) {
        this.nationality = nat;
    }
    public String getNationality() {
        return this.nationality;
    }

    public void setNewPW(String nPW) {
        this.newPW = nPW;
    }
    public String getNewPW() {
        return this.newPW;
    }

    public void setConfirmPW(String cPW) {
        this.confirmPW = cPW;
    }
    public String getConfirmPW() {
        return this.confirmPW;
    }

    public Boolean validateChange() {
        if(!this.newPW.equals(this.confirmPW)){
            return FALSE;
        }

        ChangeDataControl controller = ChangeDataControl.getInstance();

        return controller.changeData();
    }



}

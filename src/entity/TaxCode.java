package entity;
public class TaxCode {
    private String cf;

    public TaxCode(String cf) {
        this.cf= cf;
    }

    public TaxCode() {
        this.cf= "";
    }

    public String get() {
        return this.cf;
    }

    public void set(String newCf) {
        this.cf= newCf;
    }
}

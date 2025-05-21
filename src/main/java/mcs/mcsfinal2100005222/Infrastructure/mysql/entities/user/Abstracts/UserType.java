package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.Abstracts;

public enum UserType {
    TYPE_CONSUMER("FREE_TIER"),
    TYPE_SELLER("QUARTER_TIER"),
    TYPE_ADMIN("HALF_TIER"),
    TYPE_FULL("FULL_TIER");

    private String value;

    UserType(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


    public String getUserType() {
        return name();
    }
}

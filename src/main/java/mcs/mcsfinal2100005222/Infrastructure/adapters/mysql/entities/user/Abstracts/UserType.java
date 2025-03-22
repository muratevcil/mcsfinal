package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.Abstracts;

public enum UserType {
    TYPE_FREE("FREE_TIER"),
    TYPE_QUARTER("QUARTER_TIER"),
    TYPE_HALF("HALF_TIER"),
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

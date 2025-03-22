package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.Abstracts;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_MOD("MOD"),
    ROLE_FSK("FSK"),
    ROLE_SELLER("SELLER");


    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}

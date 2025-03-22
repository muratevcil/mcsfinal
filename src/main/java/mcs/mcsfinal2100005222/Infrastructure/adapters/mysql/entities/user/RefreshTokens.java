package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="refresh_tokens")
public class RefreshTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="token",columnDefinition = "TEXT")
    private String refreshToken;

    @Column(name="setting_date")
    private Date settingDate;

    @Column(name="expiration_date")
    private Date expirationDate;

    @Column(name="is_expired")
    private boolean isExpired;


}

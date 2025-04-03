package mcs.mcsfinal2100005222.Domain.entities.wallet;

import lombok.*;
import mcs.mcsfinal2100005222.Domain.entities.user.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletEntity {

    private String walletUuid;

    private UserEntity user;

    private double walletBalance;

}

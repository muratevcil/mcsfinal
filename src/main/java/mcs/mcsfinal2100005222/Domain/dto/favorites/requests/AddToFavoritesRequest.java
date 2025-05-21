package mcs.mcsfinal2100005222.Domain.dto.favorites.requests;


import lombok.Data;

@Data
public class AddToFavoritesRequest {

    private String jwt;

    private String productUuid;

}

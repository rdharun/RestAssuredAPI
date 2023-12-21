package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartCreationResponseBody {
    private String cartId;
    private String userId;
    private String createdAt;

}

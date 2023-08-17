package capstone.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Orders implements Serializable {
    private Long id;

    private LocalDateTime orderDate;

    private int totalPrice;

    private String description;

    private String cardNumber;

    private String promotionCode;

    private int status;

    private Long cusId;

    private Long techId;

    private String curFeedback;

    private int cusRating;

    private String techFeedback;

    private int techRating;
}

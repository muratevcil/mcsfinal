package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name="product_reviews")
public class ProductReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="uuid")
    private String uuid;
    @Column(name="liked_score")
    private int likedScore = 0;
    @Column(name="disliked_score")
    private int dislikedScore = 0;
    @Column(name="review_score")
    private int reviewScore;
    @Column(name="review_description")
    @Nullable
    private String reviewDescription;
    @Column(name="review_answer")
    @Nullable
    private String reviewAnswer;
    @Column(name="is_reply")
    private boolean isReply;
    @Column(name="reply_uuid")
    @Nullable
    private String replyUuid;

}

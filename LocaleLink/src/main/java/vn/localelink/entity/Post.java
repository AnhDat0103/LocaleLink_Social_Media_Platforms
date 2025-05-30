package vn.localelink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Size
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private int postId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Null
    @ManyToOne
    @JoinColumn(name = "from_post_id")
    private Post fromPostID;

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Size(max = 255)
    @Column(name = "image")
    private String image;


    @Column(name = "create_at")
    private Date createAt;


    @Column(name = "update_at")
    private Date updateAt;
}

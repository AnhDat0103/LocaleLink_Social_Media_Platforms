package vn.localelink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vn.localelink.entity.enums.FriendshipEnum;

@Entity
@Table(name = "friendships")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Friendship {
    @EmbeddedId
    private FriendshipId id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "friend_id", nullable = false, referencedColumnName = "user_id", insertable = false, updatable = false)
    private User friend;

    @NotNull
    @Column(name = "status", nullable = false)
    private FriendshipEnum status;

}

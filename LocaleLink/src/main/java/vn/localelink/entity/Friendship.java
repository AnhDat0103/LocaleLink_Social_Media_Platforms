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
@Table(name = "Friendships")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Friendship {
    @EmbeddedId
    private FriendshipId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    @NotNull
    @Column(name = "status", nullable = false)
    private FriendshipEnum status;

}

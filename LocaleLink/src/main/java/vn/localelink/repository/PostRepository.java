package vn.localelink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.localelink.DTO.response.PostResponse;
import vn.localelink.entity.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

@Query(
                value = "SELECT new vn.localelink.DTO.response.PostResponse(p.user.email, p.fromPostID, p.content, p.image, p.createAt, p.updateAt)" +
                "FROM Post p WHERE "
)
    Optional<PostResponse> findPostDtoById(Integer id);
}

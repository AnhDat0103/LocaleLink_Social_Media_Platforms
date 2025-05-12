package vn.localelink.mapper;

import org.mapstruct.Mapper;
import vn.localelink.DTO.request.PostRequest;
import vn.localelink.DTO.response.PostResponse;
import vn.localelink.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPost(PostRequest postRequest);

    PostResponse toPostResponse(Post post);
}

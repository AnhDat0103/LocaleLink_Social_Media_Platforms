package vn.localelink.DTO.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.localelink.entity.Post;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {

    private String email;

    private Post fromPostID;

    private String content;

    private String image;

    private Date createAt;

    private Date updateAt;
}

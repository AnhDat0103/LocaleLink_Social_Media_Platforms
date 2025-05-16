package vn.localelink.DTO.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.localelink.enums.ErrorEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostRequest {

    private int userId;

    @NotNull(message = ErrorEnum.NOT_EMPTY_CONTENT)
    private String content;

    @Size(max = 255)
    private String image;

}

package vn.localelink.DTO.request;

import jakarta.validation.constraints.NotNull;
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
public class PostPutUpdate {
    @NotNull(message = ErrorEnum.NOT_EMPTY_USER)
    private int userId;

    @NotNull(message = ErrorEnum.NOT_EMPTY_CONTENT)
    private String content;

    @Size(max = 255)
    private String image;
}

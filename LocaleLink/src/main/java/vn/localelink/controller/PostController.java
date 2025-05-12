package vn.localelink.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import vn.localelink.DTO.request.PostPutUpdate;
import vn.localelink.DTO.request.PostRequest;
import vn.localelink.DTO.response.ApiResponse;
import vn.localelink.DTO.response.PostResponse;
import vn.localelink.exception.AppException;
import vn.localelink.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getPostById(@PathVariable Integer id) throws AppException {
        PostResponse post = postService.handleFindById(id);
        return ApiResponse.<PostResponse>builder()
                .code("200")
                .status("success")
                .data(post)
                .build();
    }

    @PostMapping
    public ApiResponse<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest) throws AppException {
        PostResponse savedPost = postService.save(postRequest);
        return ApiResponse.<PostResponse>builder()
                .code("200")
                .status("success")
                .data(savedPost)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PostResponse> updatePost(@PathVariable Integer id,@Valid @RequestBody PostPutUpdate postPutUpdate) throws AppException {
        PostResponse updatedPost = postService.update(id, postPutUpdate);
        return ApiResponse.<PostResponse>builder()
                .code("200")
                .status("success")
                .data(updatedPost)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(@PathVariable Integer id) throws AppException {
        postService.handleRemovePost(id);
        return ApiResponse.<Void>builder()
                .code("200")
                .status("success")
                .message("Post deleted successfully")
                .build();
    }

}

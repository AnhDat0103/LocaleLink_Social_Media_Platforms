package vn.localelink.controller;

import com.nimbusds.jwt.JWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import vn.localelink.DTO.request.PostPutUpdate;
import vn.localelink.DTO.request.PostRequest;
import vn.localelink.DTO.response.ApiResponse;
import vn.localelink.DTO.response.PostResponse;
import vn.localelink.exception.AppException;
import vn.localelink.service.PostService;
import vn.localelink.service.UserService;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPosts() throws AppException {
       List<PostResponse> posts = postService.handleFindAll();
        return ApiResponse.<List<PostResponse>>builder()
                .code("200")
                .status("success")
                .data(posts)
                .build();
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
    public ApiResponse<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest, HttpServletRequest httpServletRequest) throws AppException {

        Authentication authentication = (Authentication) httpServletRequest.getUserPrincipal();
        Jwt jwt = (Jwt) authentication.getCredentials();
        String username = jwt.getSubject();
        postRequest.setUserId(userService.findByEmail(username).getUserId());

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

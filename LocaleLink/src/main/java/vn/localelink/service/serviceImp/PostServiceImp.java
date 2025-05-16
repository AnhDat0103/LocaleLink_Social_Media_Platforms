package vn.localelink.service.serviceImp;

import org.springframework.stereotype.Service;
import vn.localelink.DTO.request.PostPutUpdate;
import vn.localelink.DTO.request.PostRequest;
import vn.localelink.DTO.response.PostResponse;
import vn.localelink.entity.Post;
import vn.localelink.entity.User;
import vn.localelink.enums.ErrorEnum;
import vn.localelink.exception.AppException;
import vn.localelink.mapper.PostMapper;
import vn.localelink.repository.PostRepository;
import vn.localelink.repository.UserRepository;
import vn.localelink.service.PostService;


import java.util.Date;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImp(PostMapper postMapper, PostRepository postRepository, UserRepository userRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostResponse save(PostRequest postRequest) throws AppException {
        User currentUser = userRepository.findById(postRequest.getUserId()).orElseThrow(
                () -> new AppException(ErrorEnum.USER_NOT_FOUND)
        );
        Post post = postMapper.toPost(postRequest);
        post.setUser(currentUser);
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        return postMapper.toPostResponse(postRepository.save(post));
    }

    @Override
    public PostResponse update(Integer postId, PostPutUpdate postPutUpdate) throws AppException{
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new AppException(ErrorEnum.POST_NOT_FOUND)
        );
        post.setContent(postPutUpdate.getContent());
        post.setImage(postPutUpdate.getImage());
        post.setUpdateAt(new Date());
        return postMapper.toPostResponse(postRepository.save(post));
    }

    @Override
    public PostResponse handleFindById(Integer id) throws AppException {
        return postRepository.findPostDtoById(id).orElseThrow(
                () -> new AppException(ErrorEnum.POST_NOT_FOUND)
        );
    }

    @Override
    public void handleRemovePost(Integer id) throws AppException{
        Post post = postRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorEnum.POST_NOT_FOUND)
        );
        postRepository.delete(post);
    }

    @Override
    public List<PostResponse> handleFindAll() {
        return postRepository.findAll().stream().map(postMapper::toPostResponse).toList();
    }
}

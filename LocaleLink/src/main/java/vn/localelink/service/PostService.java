package vn.localelink.service;

import jakarta.validation.Valid;
import vn.localelink.DTO.request.PostPutUpdate;
import vn.localelink.DTO.request.PostRequest;
import vn.localelink.DTO.response.PostResponse;
import vn.localelink.exception.AppException;

import java.util.List;

public interface PostService {
    PostResponse save(@Valid PostRequest postRequest) throws AppException;

    PostResponse update(Integer id, PostPutUpdate postPutUpdate) throws AppException;

    PostResponse handleFindById(Integer id) throws AppException;

    void handleRemovePost(Integer id) throws AppException;

    List<PostResponse> handleFindAll();
}

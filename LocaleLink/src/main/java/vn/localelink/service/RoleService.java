package vn.localelink.service;

import jakarta.validation.Valid;
import vn.localelink.DTO.request.RoleCreate;
import vn.localelink.DTO.request.RolePutUpdate;
import vn.localelink.entity.Role;
import vn.localelink.enums.RoleEnum;
import vn.localelink.exception.AppException;

import java.util.List;

public interface RoleService {

    Role findRoleByName(RoleEnum roleName) throws AppException;

    List<Role> findAll();

    Role findById(int id) throws AppException;

    Role createRole(@Valid RoleCreate roleCreate);

    Role updateRole(int id, @Valid RolePutUpdate rolePutUpdate) throws AppException;

    void deleteRole(int id) throws AppException;
}

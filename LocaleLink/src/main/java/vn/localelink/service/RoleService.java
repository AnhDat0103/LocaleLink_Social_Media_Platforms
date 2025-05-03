package vn.localelink.service;

import vn.localelink.entity.Role;
import vn.localelink.enums.RoleEnum;
import vn.localelink.exception.AppException;

public interface RoleService {

    Role findRoleByName(RoleEnum roleName) throws AppException;
}

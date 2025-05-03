package vn.localelink.service.serviceImp;

import org.springframework.stereotype.Service;
import vn.localelink.entity.Role;
import vn.localelink.enums.ErrorEnum;
import vn.localelink.enums.RoleEnum;
import vn.localelink.exception.AppException;
import vn.localelink.repository.RoleRepository;
import vn.localelink.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findRoleByName(RoleEnum roleName) throws AppException {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new AppException(ErrorEnum.ROLE_NOT_FOUND));
    }
}

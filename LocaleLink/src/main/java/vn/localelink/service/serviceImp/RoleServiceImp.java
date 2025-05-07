package vn.localelink.service.serviceImp;

import org.springframework.stereotype.Service;
import vn.localelink.DTO.request.RoleCreate;
import vn.localelink.DTO.request.RolePutUpdate;
import vn.localelink.entity.Role;
import vn.localelink.enums.ErrorEnum;
import vn.localelink.enums.RoleEnum;
import vn.localelink.exception.AppException;
import vn.localelink.repository.RoleRepository;
import vn.localelink.service.RoleService;

import java.util.List;

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

    @Override
    public List<Role> findAll() {
        return List.of();
    }

    @Override
    public Role findById(int id) throws AppException {
        return roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorEnum.ROLE_NOT_FOUND));
    }

    @Override
    public Role createRole(RoleCreate roleCreate) {
        return Role.builder()
                .roleName(roleCreate.getRoleName())
                .description(roleCreate.getDescription())
                .build();
    }

    @Override
    public Role updateRole(int id, RolePutUpdate rolePutUpdate) throws AppException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorEnum.ROLE_NOT_FOUND));
        if (rolePutUpdate.getRoleName() != null) {
            role.setRoleName(rolePutUpdate.getRoleName());
        }
        if (rolePutUpdate.getDescription() != null) {
            role.setDescription(rolePutUpdate.getDescription());
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) throws AppException {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorEnum.ROLE_NOT_FOUND));
        roleRepository.delete(role);
    }

}

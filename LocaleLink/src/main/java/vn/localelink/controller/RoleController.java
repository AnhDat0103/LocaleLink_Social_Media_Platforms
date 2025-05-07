package vn.localelink.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import vn.localelink.DTO.request.RoleCreate;
import vn.localelink.DTO.request.RolePutUpdate;
import vn.localelink.DTO.response.ApiResponse;
import vn.localelink.entity.Role;
import vn.localelink.exception.AppException;
import vn.localelink.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ApiResponse<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ApiResponse.<List<Role>>builder()
                .status("success")
                .data(roles)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Role> getRoleById(@PathVariable int id) throws AppException {
        Role role = roleService.findById(id);
        return ApiResponse.<Role>builder()
                .status("success")
                .data(role)
                .build();
    }

    @PostMapping
    public ApiResponse<Role> createRole(@Valid @RequestBody RoleCreate roleCreate) throws AppException {
        Role role = roleService.createRole(roleCreate);
        return ApiResponse.<Role>builder()
                .status("success")
                .data(role)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Role> updateRole(@PathVariable int id, @Valid @RequestBody RolePutUpdate rolePutUpdate) throws AppException {
        Role role = roleService.updateRole(id, rolePutUpdate);
        return ApiResponse.<Role>builder()
                .status("success")
                .data(role)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteRole(@PathVariable int id) throws AppException {
        roleService.deleteRole(id);
        return ApiResponse.builder()
                .status("success")
                .message("Role deleted successfully")
                .build();
    }
}
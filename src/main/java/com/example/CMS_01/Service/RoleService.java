package com.example.CMS_01.Service;

import com.example.CMS_01.Entity.Role;
import org.springframework.stereotype.Service;

public interface RoleService extends Service{
    Role saveRole(Role role);
    void deleteRole(Integer id);
}

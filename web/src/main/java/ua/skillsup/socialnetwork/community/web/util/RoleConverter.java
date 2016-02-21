package ua.skillsup.socialnetwork.community.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import socialnetwork.community.dao.RoleService;
import socialnetwork.community.dao.entity.Role;

@Component
public class RoleConverter implements Converter<Object, Role> {

    @Autowired
    RoleService roleService;

    public Role convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        Role role = roleService.findById(id);
        System.out.println("Role : " + role);
        return role;
    }

}

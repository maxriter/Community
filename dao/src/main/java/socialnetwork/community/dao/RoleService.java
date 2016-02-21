package socialnetwork.community.dao;

import socialnetwork.community.dao.entity.Role;
import socialnetwork.community.dao.entity.RoleType;

import java.util.List;


public interface RoleService {

    List<Role> findAll();

    Role findByType(RoleType roleType);

    Role findById(int id);
}

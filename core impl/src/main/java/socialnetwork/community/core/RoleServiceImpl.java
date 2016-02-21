package socialnetwork.community.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.dao.RoleDao;
import socialnetwork.community.dao.RoleService;
import socialnetwork.community.dao.entity.Role;
import socialnetwork.community.dao.entity.RoleType;

import java.util.List;


@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao dao;

    public List<Role> findAll() {
        return dao.findAll();
    }

    public Role findByType(RoleType roleType) {
        return dao.findByType(roleType);
    }

    public Role findById(int id) {
        return dao.findById(id);
    }

}

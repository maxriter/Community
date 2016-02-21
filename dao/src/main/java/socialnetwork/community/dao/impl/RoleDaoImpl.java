package socialnetwork.community.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import socialnetwork.community.dao.AbstractDao;
import socialnetwork.community.dao.RoleDao;
import socialnetwork.community.dao.entity.Role;
import socialnetwork.community.dao.entity.RoleType;

import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {

    @SuppressWarnings("unchecked")
    public List<Role> findAll(){
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<Role>)crit.list();
    }

    public Role findById(int id) {
        return getByKey(id);
    }

    public Role findByType(RoleType roleType) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", roleType.toString()));
        Role role = (Role) crit.uniqueResult();
        return role;
    }
}

package ua.skillsup.socialnetwork.community.web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.entity.Contact;
import socialnetwork.community.dao.entity.Role;

import java.util.ArrayList;
import java.util.List;

@Service("contactDetailsService")
public class ContactAuthorizationService implements UserDetailsService {

    @Autowired
    ContactDao contactDao;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Contact contact = contactDao.findByUsername(username);
        if (contact == null) {
            throw new UsernameNotFoundException("Contact not found");
        }
        return new org.springframework.security.core.userdetails.User(contact.getUsername(), contact.getPassword(),
                contact.getState().equals("Active"), true, true, true, getGrantedAuthorities(contact));
    }


    private List<GrantedAuthority> getGrantedAuthorities(Contact contact) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : contact.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType()));
        }
        return authorities;
    }
}

package socialnetwork.community.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import socialnetwork.community.api.JavaContactService;
import socialnetwork.community.dao.ContactDao;
import socialnetwork.community.dao.HobbyDao;

import static socialnetwork.community.dao.converters.EntityConverter.convert;


public class App {
    public static void main(String[] args) {
        /*
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("daoApplicationContext.xml");
        JavaContactService service = context.getBean(JavaContactService.class);
        ContactDao contactDao = context.getBean(ContactDao.class);
        HobbyDao hobbyDao = context.getBean(HobbyDao.class);

        context.close();
        */

    }
}

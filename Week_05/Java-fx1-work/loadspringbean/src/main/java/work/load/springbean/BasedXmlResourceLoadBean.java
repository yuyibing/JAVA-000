package work.load.springbean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import work.load.springbean.domain.User;

/**
 * 基于XML资源装载Spring Bean
 */
public class BasedXmlResourceLoadBean {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-context.xml");
        User user = (User)beanFactory.getBean("user");
        System.out.println(user);

    }

}

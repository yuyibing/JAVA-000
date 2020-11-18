package work.load.springbean;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;
import work.load.springbean.domain.User;

/**
 * 基于创建BeanDefinition的加载Spring Bean
 */
public class BasedCreateBeanDefinitionLoadBean {

    public static void main(String[] args) {
        // BeanDefinition 的定义（声明）
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", "1");
        beanDefinitionBuilder.addPropertyValue("name", "yyyy");
        beanDefinitionBuilder.addPropertyValue("address", "gz");
        // 获取 AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 当前 BeanDefinition 来自于哪里
        beanDefinition.setSource(BasedCreateBeanDefinitionLoadBean.class);

        final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册 User 的 BeanDefinition
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", User.class);

        System.out.println(user);
    }

}

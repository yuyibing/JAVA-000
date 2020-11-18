package work.load.springbean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import work.load.springbean.domain.User;

/**
 * 基于Properties资源加载Spring Bean
 */
public class BasedPropertiesResourceLoadBean {
    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化基于properties 的PropertiesBeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";
        // 加载properties资源
        // 指定字符编码 UTF-8
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNums = beanDefinitionReader.loadBeanDefinitions(encodedResource);

        System.out.println("已加载的BeanDefinition的数量：" + beanNums);

        // 通过 Bean Id和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}

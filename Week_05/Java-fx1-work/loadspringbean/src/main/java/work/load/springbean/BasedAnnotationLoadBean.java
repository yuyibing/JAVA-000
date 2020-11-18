package work.load.springbean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import work.load.springbean.domain.User;

/**
 * 基于Annotion加载Spring Bean
 */
@PropertySource("classpath:/META-INF/user.properties")
public class BasedAnnotationLoadBean {

    @Bean
    public User configuredUser(@Value("${user.id}") Integer id,
                               @Value("${user.name}") String name,
                               @Value("${user.address}") String address){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration class
        context.register(BasedAnnotationLoadBean.class);
        // 启动 Spring 应用上下文
        context.refresh();

        User user = context.getBean("configuredUser", User.class);
        System.out.println(user);

        // 关闭 Spring 应用上下文
        context.close();
    }

}

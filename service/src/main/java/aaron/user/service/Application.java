package aaron.user.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-03-05
 */
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
@SpringBootApplication
public class Application {
}


- application context` is a sub interface from bean factory
- application context the same as`IOC` container but it has enterprise features
- since application context is a sub interface form bean factory then it inherits all features `bean factory` provides


**Application context** provides :
- integrates `AOP`
- handle message resource (`il8n`)
- Web Application context 

### `IOC` container : 

it is a Spring's core engine for dependency injection (DI) and bean management it instantiates, configures, and manages Objects(beans) and their dependencies 

- **Primary Interfaces** : Bean factory -> the fundamental `IoC` container interface. it provides `DI` capabilities (e.g. lazy loading, bean lookup)

- key Responsibilities:
	- Creating beans
	- Wiring dependencies
	- Managing bean `lifecycles` (init/destroy methods)
	- Handling bean scopes (`singleton`, `prototype`. etc)



***Code***:

```java
// using bean factory  
BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring.xml");  
EmailService emailService = beanFactory.getBean("emailService", EmailService.class);  
emailService.sendEmail("hasan@gmail.com" , "hello form spring you dev");  

///  #######################################################################################################
  
  
// using application context  
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");  
EmailService service = context.getBean("emailService", EmailService.class);  
emailService.sendEmail("alhassan.ebrahiem@gmail.com", "hello from spring");
```

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
       xsi:schemaLocation="http://www.springframework.org/schema/beans  
           http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">  
  
  
    <bean id="emailService" class="org.project.EmailService"></bean>  
  
  
</beans>

```


### Bean scopes :

- `Singleton(default)` : per instance per `IoC` container
- `Prototype` : per instance per bean request
- `Request` : per instance per `HTTP` request
- Session : per instance per `HTTP` session
- Application : per instance per `Servler` context 
- `WebSocket` : is a singleton but lives longer
- custom scope -> you can create custom scope

**Prototype bean scope :**

- the client code must clean up prototype-scoped objects because configured destruction life cycle callback methods are not called for these objects

- **Q: what happens when injecting Prototype ban into a singleton bean:** 
	- both beans will be initialized only once at the startup
	- prototype will lose its actual behavior

to fix this issue :
- use application context but this violates inversion of control 
```java
@Autowired  
private ApplicationContext applicationContext;  
  
  
public String getMethod(){  
    return applicationContext.getBean(PrototypeBean.class).getMethod();  
}


```

use @lockup
```java
public String getMethod(){  
    return prototypeBean().getMethod();  
}  
  
@Lookup  
public PrototypeBean prototypeBean(){  
    return null;  
}

```

and there is also provider but it comes form `JSL ` from `javax.inject.provider`
### Bean life cycle 
- #### Creation life cycle: 

1) instantiate -> call default constructor 
2) populate properties -> call setters to set values to properties
3) Aware interfaces : 
	- `BeanNameAware` -> has  `setBeanName(String name)`
	- `BeanFactoryAware` -> has `setBeanFactory(BeanFactory bf)`
	- `ApplicationContextAware` -> `setAlicationContext()`

4) Initializing bean (interface) -> one method `afterPropertySet
5) Custom init method -> the best solution 
6) Bean Post Processors -> has two method ( `pre-initialization` , `post-initialzation`

- ### Destruction life cycle: 
	- when `IoC` container shutdown:
		- disposable bean interface -> destroy 
		- custom destroy method



***Note :*** custom init method is the best solution because when implementing aware interfaces i will be tightly coupled to these interface


***Note :*** if i want to add customized code during bean creation:
1) override *aware interfaces
2) initializing bean -> `afterPropertySet`
3) custom init method
4) @post Construct
5) `BeanPostProcessors`




@Bean vs @component:
- component based annotation : apply on the class level and there's implicit one-to-one mapping between bean name and bean

- bean based annotation : applied on methods
	- bean name is the method name



@Configuration : 
- used when i want to use java class as operation
- used to differentiate config class from other stereo type annotated classes
- used when to i want to start my app with these configs



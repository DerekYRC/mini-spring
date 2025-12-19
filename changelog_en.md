# [Basics: IoC](#basics-ioc)
## [Simplest Bean Container](#simplest-bean-container)
> Code branch: simple-bean-container

Define a simple bean container BeanFactory that contains an internal map to store beans, with only two methods: register bean and get bean.
```java
public class BeanFactory {
	private Map<String, Object> beanMap = new HashMap<>();

	public void registerBean(String name, Object bean) {
		beanMap.put(name, bean);
	}

	public Object getBean(String name) {
		return beanMap.get(name);
	}
}
```

Test:
```java
public class SimpleBeanContainerTest {

	@Test
	public void testGetBean() throws Exception {
		BeanFactory beanFactory = new BeanFactory();
		beanFactory.registerBean("helloService", new HelloService());
		HelloService helloService = (HelloService) beanFactory.getBean("helloService");
		assertThat(helloService).isNotNull();
		assertThat(helloService.sayHello()).isEqualTo("hello");
	}

	class HelloService {
		public String sayHello() {
			System.out.println("hello");
			return "hello";
		}
	}
}
```

## [BeanDefinition and BeanDefinitionRegistry](#beandefinition-and-beandefinitionregistry)
> Code branch: bean-definition-and-bean-definition-registry

Main added classes:
- BeanDefinition: As the name suggests, a class used to define bean information, including bean class type, constructor parameters, property values, etc. Each bean corresponds to a BeanDefinition instance. Simplified BeanDefinition only contains the bean's class type.
- BeanDefinitionRegistry: BeanDefinition registry interface that defines methods for registering BeanDefinition.
- SingletonBeanRegistry and its implementation DefaultSingletonBeanRegistry: Define methods for adding and getting singleton beans.

The bean container serves as an implementation class for both BeanDefinitionRegistry and SingletonBeanRegistry, possessing capabilities of both. After registering BeanDefinition in the bean container, beans are instantiated only when used.

## [Bean Instantiation Strategy InstantiationStrategy](#bean-instantiation-strategy-instantiationstrategy)
> Code branch: instantiation-strategy

Currently beans are instantiated using beanClass.newInstance() in the AbstractAutowireCapableBeanFactory.doCreateBean method, which only applies when beans have no-argument constructors.

For bean instantiation, an instantiation strategy interface InstantiationStrategy is abstracted, with two implementation classes:
- SimpleInstantiationStrategy: Uses bean's constructor for instantiation
- CglibSubclassingInstantiationStrategy: Uses CGLIB to dynamically generate subclasses

## [Populating Properties for Beans](#populating-properties-for-beans)
> Code branch: populate-bean-with-property-values

Add PropertyValues corresponding to bean properties in BeanDefinition. After bean instantiation, populate properties for the bean (AbstractAutowireCapableBeanFactory#applyPropertyValues).

## [Injecting Beans into Beans](#injecting-beans-into-beans)
> Code branch: populate-bean-with-bean

Add BeanReference class in PropertyValue to represent references to other beans. When setting bean properties, if the property value is a BeanReference, get the reference bean from the container.

## [Resources and Resource Loaders](#resources-and-resource-loaders)
> Code branch: resource-and-resource-loader

Spring core module provides accessing low-level resources functionality. Add Resource interface and FileSystemResource, ClassPathResource, UrlResource implementation classes, as well as ResourceLoader interface and DefaultResourceLoader implementation class.

## [Defining Beans in XML Files](#defining-beans-in-xml-files)
> Code branch: xml-file-define-bean

Add xml file support for bean definitions. Add XmlBeanDefinitionReader class for reading bean definitions from xml files.

## [Container Extension Mechanisms BeanFactoryPostProcessor and BeanPostProcessor](#container-extension-mechanisms)
> Code branch: bean-factory-post-processor-and-bean-post-processor

BeanFactoryPostProcessor and BeanPostProcessor are two key extension mechanisms in Spring:
- BeanFactoryPostProcessor allows modifying bean definitions before bean instantiation
- BeanPostProcessor allows processing beans before and after initialization

## [Application Context ApplicationContext](#application-context-applicationcontext)
> Code branch: application-context

ApplicationContext is the advanced container in Spring IoC, providing more enterprise-level functionality than BeanFactory.

## [Bean Initialization and Destruction Methods](#bean-initialization-and-destruction-methods)
> Code branch: init-and-destroy-method

Add support for bean initialization and destruction methods. Beans can implement InitializingBean and DisposableBean interfaces, or specify initialization and destruction methods in bean definitions.

## [Aware Interfaces](#aware-interfaces)
> Code branch: aware-interface

Aware interfaces allow beans to access Spring container infrastructure:
- BeanFactoryAware: Access to BeanFactory
- BeanClassLoaderAware: Access to class loader
- BeanNameAware: Access to bean name
- ApplicationContextAware: Access to ApplicationContext

## [Bean Scopes, Adding Prototype Support](#bean-scopes-adding-prototype-support)
> Code branch: prototype-bean

Add prototype bean scope support. In addition to the default singleton scope, beans can now be defined with prototype scope for creating new instances on each request.

## [FactoryBean](#factorybean)
> Code branch: factory-bean

FactoryBean is a special kind of bean that serves as a factory for other objects. Implement FactoryBean interface to customize object creation logic.

## [Container Events and Event Listeners](#container-events-and-event-listeners)
> Code branch: event-and-event-listener

Add event mechanism with ApplicationEvent, ApplicationListener, and ApplicationEventPublisher to support publish-subscribe pattern within the Spring container.

# [Basics: AOP](#basics-aop)

## [Pointcut Expressions](#pointcut-expressions)
> Code branch: pointcut-expression

Add pointcut expression support for AOP. AspectJExpressionPointcut class uses AspectJ expression syntax to match join points.

## [JDK-based Dynamic Proxy](#jdk-based-dynamic-proxy)
> Code branch: jdk-dynamic-proxy

Implement JDK dynamic proxy for AOP. JdkDynamicAopProxy creates proxy objects implementing target interfaces.

## [CGLIB-based Dynamic Proxy](#cglib-based-dynamic-proxy)
> Code branch: cglib-dynamic-proxy

Add CGLIB dynamic proxy support. CglibAopProxy creates proxy by extending target classes, supporting proxying of classes without interfaces.

## [AOP Proxy Factory](#aop-proxy-factory)
> Code branch: aop-proxy-factory

Add ProxyFactory to unify proxy creation logic, automatically choosing between JDK and CGLIB proxy based on target type.

## [Common Advice Types](#common-advice-types)
> Code branch: advice-types

Implement common advice types:
- BeforeAdvice: Execute before method invocation
- AfterAdvice: Execute after method invocation
- AfterReturningAdvice: Execute after successful method return
- ThrowsAdvice: Execute when method throws exception

## [PointcutAdvisor: Combination of Pointcut and Advice](#pointcutadvisor)
> Code branch: pointcut-advisor

PointcutAdvisor combines Pointcut and Advice, defining where and what advice should be applied.

## [Integrating Dynamic Proxy into Bean Lifecycle](#integrating-dynamic-proxy-into-bean-lifecycle)
> Code branch: auto-proxy

Integrate AOP proxy creation into Spring bean lifecycle using BeanPostProcessor. DefaultAdvisorAutoProxyCreator automatically creates proxies for beans matching advisor pointcuts.

# [Extensions](#extensions)

## [PropertyPlaceholderConfigurer](#propertyplaceholderconfigurer)
> Code branch: property-placeholder-configurer

Add property placeholder support. PropertyPlaceholderConfigurer resolves ${...} placeholders in bean definitions using property files.

## [Package Scanning](#package-scanning)
> Code branch: package-scan

Add component scanning functionality. ClassPathBeanDefinitionScanner scans packages for @Component annotated classes and automatically registers them as beans.

## [@Value Annotation](#value-annotation)
> Code branch: value-annotation

Add @Value annotation support for injecting values from properties into bean fields.

## [@Autowired Annotation](#autowired-annotation)
> Code branch: autowired-annotation

Add @Autowired annotation for automatic dependency injection based on type matching.

## [Type Conversion (Part 1)](#type-conversion-part-1)
> Code branch: type-conversion-first-part

Add basic type conversion support for converting string property values to appropriate types during bean instantiation.

## [Type Conversion (Part 2)](#type-conversion-part-2)
> Code branch: type-conversion-second-part

Enhance type conversion system with ConversionService for more sophisticated type conversions.

# [Advanced](#advanced)

## [Solving Circular Dependencies (Part 1): Without Proxy Objects](#solving-circular-dependencies-part-1)
> Code branch: circular-reference-without-proxy-bean

Solve circular dependency issues for regular beans using three-level cache mechanism (singletonObjects, earlySingletonObjects, singletonFactories).

## [Solving Circular Dependencies (Part 2): With Proxy Objects](#solving-circular-dependencies-part-2)
> Code branch: circular-reference-with-proxy-bean

Extend circular dependency resolution to handle cases involving AOP proxy objects, ensuring proxy creation during early exposure phase.

# [Others](#others)

## [Bug Fix: Properties Not Set for Proxy Beans](#bug-fix-proxy-bean-properties)
> Code branch: populate-proxy-bean-with-property-values

Fix issue where proxy beans were not getting their properties populated. Ensure property population occurs for both target objects and their proxies.

## [Support for Lazy Loading and Multi-Aspect Enhancement](#lazy-loading-and-multi-aspect)
> Code branch: lazy-and-multi-aspect

Add lazy bean initialization support and enable multiple aspect enhancements on single target objects.

---

*This changelog documents the evolution of mini-spring from a simple bean container to a feature-rich IoC and AOP framework, demonstrating core Spring concepts through incremental development.*
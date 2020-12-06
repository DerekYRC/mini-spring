# <img src="assets/spring-framework.png" width="80" height="80"> mini-spring
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/DerekYRC/mini-spring)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## About
* [中文版](./README_CN.md)

The **mini-spring** is a simplified version of the Spring framework that will help you quickly get familiar with the Spring source code and grasp the core principles of Spring. The core logic of Spring is extracted, the code is extremely simplified, and the core functions of Spring, such as IoC and AOP, resource loaders, event listeners, type conversion, container extension points, bean life cycle and scope, and application context, are retained.

If this project can help you, please give a **STAR, thank you!!!**

## Contents
#### Basics
* [IoC](#Ioc)
    * [Implement a simple container](#实现一个简单的容器)
    * [BeanDefinition and BeanDefinitionRegistry](#BeanDefinition和BeanDefinitionRegistry)
    * [Bean Instantiation Strategy](#Bean实例化策略InstantiationStrategy)
    * [Populate bean with property values](#为bean填充属性)
    * [Populate bean with bean](#为bean注入bean)
    * [Resource and ResourceLoader](#资源和资源加载器)
    * [Define the bean in the XML file](#在xml文件中定义bean)
    * [Container extension mechanism：BeanFactoryPostProcess and BeanPostProcessor](#容器扩展机制BeanFactoryPostProcess和BeanPostProcessor)
    * [ApplicationContext](#应用上下文ApplicationContext)
    * [Init method and destroy method of bean](#bean的初始化和销毁方法)
    * [Aware interface](#Aware接口)
    * [Bean scope, added prototype support](#bean作用域，增加prototype的支持)
    * [FactoryBean](#FactoryBean)
    * [Event and event listener](#容器事件和事件监听器)
* [AOP](#AOP)
    * [Pointcut expression](#切点表达式)
    * [JDK-based dynamic proxy](#基于JDK的动态代理)
    * [CGLIB-based dynamic proxy](#基于CGLIB的动态代理)
    * [AOP ProxyFactory](#AOP代理工厂ProxyFactory)
    * [Common Advice: BeforeAdvice/AfterAdvice/AfterReturningAdvice/ThrowsAdvice](#几种常用的Advice)
    * [PointcutAdvisor：A combination of Pointcut and Advice](#PointcutAdvisor：Pointcut和Advice的组合)
    * [Dynamic proxies are integrated into the bean lifecycle](#动态代理融入bean生命周期)
    

#### Expanding 
* [PropertyPlaceholderConfigurer](#PropertyPlaceholderConfigurer)
* [Type conversion](#类型转换)
* [Package scan](#包扫描)
* [Autowired annotation](#基于注解的依赖注入Autowired)

#### Advanced
* [Solve the problem of circular dependencies](#解决循环依赖问题)

## Usage
Each function point corresponds to a branch. Switch to the branch corresponding to the function point to see the new function. The incremental change point is described in the [changelog.md](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md) file.

## Reference
- [《Spring源码深度解析》](https://book.douban.com/subject/25866350/)
- [《精通Spring 4.x》](https://book.douban.com/subject/26952826/)
- [tiny-spring](https://github.com/code4craft/tiny-spring)

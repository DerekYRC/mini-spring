# <img src="assets/spring-framework.png" width="80" height="80"> mini-spring
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/DerekYRC/mini-spring)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Stars](https://img.shields.io/github/stars/DerekYRC/mini-spring)](https://img.shields.io/github/stars/DerekYRC/mini-spring)
[![Forks](https://img.shields.io/github/forks/DerekYRC/mini-spring)](https://img.shields.io/github/forks/DerekYRC/mini-spring)

**English | [简体中文](./README.md)**

**Sister Projects:** 
 - [**mini-spring-cloud**](https://github.com/DerekYRC/mini-spring-cloud) **(simplified Spring Cloud framework)**
 - [**mini-netty**](https://github.com/DerekYRC/mini-netty) **(simplified Netty framework)**

## About

**mini-spring** is a simplified version of the Spring framework that helps you quickly familiarize yourself with Spring source code and master Spring's core principles. It extracts Spring's core logic with **extremely simplified code while preserving Spring's core functionality**, including IoC and AOP, resource loaders, event listeners, type conversion, container extension points, bean lifecycle and scope, application context, and other core features.

If this project helps you, please give it a **STAR, thank you!!!**

## Features
#### Basics
* [IoC](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基础篇IoC)
    * [Implementing a simple container](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#最简单的bean容器)
    * [BeanDefinition and BeanDefinitionRegistry](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#BeanDefinition和BeanDefinitionRegistry)
    * [Bean instantiation strategy InstantiationStrategy](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#Bean实例化策略InstantiationStrategy)
    * [Populating properties for beans](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#为bean填充属性)
    * [Injecting beans into beans](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#为bean注入bean)
    * [Resources and resource loaders](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#资源和资源加载器)
    * [Defining beans in XML files](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#在xml文件中定义bean)
    * [Container extension mechanisms BeanFactoryPostProcess and BeanPostProcessor](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#BeanFactoryPostProcess和BeanPostProcessor)
    * [Application context ApplicationContext](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#应用上下文ApplicationContext)
    * [Bean initialization and destruction methods](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#bean的初始化和销毁方法)
    * [Aware interfaces](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#Aware接口)
    * [Bean scopes, adding prototype support](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#bean作用域增加prototype的支持)
    * [FactoryBean](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#FactoryBean)
    * [Container events and event listeners](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#容器事件和事件监听器)
* [AOP](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基础篇AOP)
    * [Pointcut expressions](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#切点表达式)
    * [JDK-based dynamic proxy](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基于JDK的动态代理)
    * [CGLIB-based dynamic proxy](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基于CGLIB的动态代理)
    * [AOP proxy factory ProxyFactory](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#AOP代理工厂)
    * [Common types of Advice: BeforeAdvice/AfterAdvice/AfterReturningAdvice/ThrowsAdvice](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#几种常用的AdviceBeforeAdviceAfterAdviceAfterReturningAdviceThrowsAdvice)
    * [PointcutAdvisor: combination of Pointcut and Advice](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#pointcutadvisorpointcut和advice的组合)
    * [Integrating dynamic proxy into bean lifecycle](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#动态代理融入bean生命周期)
    
#### Extensions
* [PropertyPlaceholderConfigurer](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#propertyplaceholderconfigurer)
* [Package scanning](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#包扫描)
* [@Value annotation](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#value注解)
* [Annotation-based dependency injection with @Autowired](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#autowired注解)
* [Type conversion (Part 1)](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#类型转换一)
* [Type conversion (Part 2)](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#类型转换二)

#### Advanced
* [Solving circular dependency issues (Part 1): without proxy objects](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#解决循环依赖问题一没有代理对象)
* [Solving circular dependency issues (Part 2): with proxy objects](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#解决循环依赖问题二有代理对象)

#### Others
* [Properties not set for proxy beans (discovered and fixed by kerwin89)](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#bug-fix没有为代理bean设置属性discovered-and-fixed-by-kerwin89)
* [Support for lazy loading and multi-aspect enhancement (by zqczgl)](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#支持懒加载和多切面增强by-zqczgl)

## Usage
Read [changelog.md](https://github.com/DerekYRC/mini-spring/blob/main/changelog_en.md)

[Video Tutorial (Complete Version)](https://www.bilibili.com/video/BV1nb4y1A7YJ)

## Questions
[**Ask Questions Here**](https://github.com/DerekYRC/mini-spring/issues/4)

## Contributing
Pull Requests are welcome

## About Me
[**Learn More**](https://github.com/DerekYRC)

Phone/WeChat: **15975984828**  Email: **15975984828@163.com**

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=DerekYRC/mini-spring&type=Date)](https://star-history.com/#DerekYRC/mini-spring&Date)

## Copyright Notice
This project may not be used for commercial purposes without my written permission.

## References
- [《Spring源码深度解析》](https://book.douban.com/subject/25866350/)
- [《Spring 源码解析》](http://svip.iocoder.cn/categories/Spring)
- [《精通Spring 4.x》](https://book.douban.com/subject/26952826/)
- [《tiny-spring》](https://github.com/code4craft/tiny-spring)

#### Advanced
* [Solve the problem of circular dependencies(first part): without proxy bean](#解决循环依赖问题一)
* [Solve the problem of circular dependencies(second part): with proxy bean](#解决循环依赖问题二)

#### Bug fix
* [populate proxy bean with property values(discovered and fixed by kerwin89)](#没有为代理bean设置属性)

## Usage
Each function point corresponds to a branch. Switch to the branch corresponding to the function point to see the new function. The incremental change point is described in the [changelog.md](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md) file.

## Contributing
Any contributions you make are greatly appreciated.

## Contact
Please feel free to ask me any questions related to mini-spring and other technologies. My email is **15521077528@163.com**. 

## Reference
- [《Spring源码深度解析》](https://book.douban.com/subject/25866350/)
- [《精通Spring 4.x》](https://book.douban.com/subject/26952826/)
- [tiny-spring](https://github.com/code4craft/tiny-spring)

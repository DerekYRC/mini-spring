# <img src="assets/spring-framework.png" width="80" height="80"> mini-spring
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/DerekYRC/mini-spring)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Stars](https://img.shields.io/github/stars/DerekYRC/mini-spring)](https://img.shields.io/github/stars/DerekYRC/mini-spring)
[![Forks](https://img.shields.io/github/forks/DerekYRC/mini-spring)](https://img.shields.io/github/forks/DerekYRC/mini-spring)

* [English version](./README.md)

## 关于

**mini-spring**是简化版的spring框架，能帮助你快速熟悉spring源码和掌握spring的核心原理。抽取了spring的核心逻辑，代码极度简化，保留spring的核心功能，如IoC和AOP、资源加载器、事件监听器、类型转换、容器扩展点、bean生命周期和作用域、应用上下文等核心功能。

如果本项目能帮助到你，请给个**STAR，谢谢！！！**

## 功能
#### 基础篇
* [IoC](#Ioc)
    * [实现一个简单的容器](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#%E6%9C%80%E7%AE%80%E5%8D%95%E7%9A%84bean%E5%AE%B9%E5%99%A8)
    * [BeanDefinition和BeanDefinitionRegistry](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#BeanDefinition%E5%92%8CBeanDefinitionRegistry)
    * [Bean实例化策略InstantiationStrategy](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#Bean%E5%AE%9E%E4%BE%8B%E5%8C%96%E7%AD%96%E7%95%A5InstantiationStrategy)
    * [为bean填充属性](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#%E4%B8%BAbean%E5%A1%AB%E5%85%85%E5%B1%9E%E6%80%A7)
    * [为bean注入bean](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#%E4%B8%BAbean%E6%B3%A8%E5%85%A5bean)
    * [资源和资源加载器](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#%E8%B5%84%E6%BA%90%E5%92%8C%E8%B5%84%E6%BA%90%E5%8A%A0%E8%BD%BD%E5%99%A8)
    * [在xml文件中定义bean](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#%E5%9C%A8xml%E6%96%87%E4%BB%B6%E4%B8%AD%E5%AE%9A%E4%B9%89bean)
    * [容器扩展机制BeanFactoryPostProcess和BeanPostProcessor](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#BeanFactoryPostProcess%E5%92%8CBeanPostProcessor)
    * [应用上下文ApplicationContext](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#%E5%BA%94%E7%94%A8%E4%B8%8A%E4%B8%8B%E6%96%87ApplicationContext)
    * [bean的初始化和销毁方法](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#bean%E7%9A%84%E5%88%9D%E5%A7%8B%E5%8C%96%E5%92%8C%E9%94%80%E6%AF%81%E6%96%B9%E6%B3%95)
    * [Aware接口](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#Aware%E6%8E%A5%E5%8F%A3)
    * [bean作用域，增加prototype的支持](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#bean%E4%BD%9C%E7%94%A8%E5%9F%9F)
    * [FactoryBean](#FactoryBean)
    * [容器事件和事件监听器](#容器事件和事件监听器)
* [AOP](#AOP)
    * [切点表达式](#切点表达式)
    * [基于JDK的动态代理](#基于JDK的动态代理)
    * [基于CGLIB的动态代理](#基于CGLIB的动态代理)
    * [AOP代理工厂ProxyFactory](#AOP代理工厂ProxyFactory)
    * [几种常用的Advice: BeforeAdvice/AfterAdvice/AfterReturningAdvice/ThrowsAdvice](#几种常用的Advice)
    * [PointcutAdvisor：Pointcut和Advice的组合](#PointcutAdvisor：Pointcut和Advice的组合)
    * [动态代理融入bean生命周期](#动态代理融入bean生命周期)
    

#### 扩展篇
* [PropertyPlaceholderConfigurer](#PropertyPlaceholderConfigurer)
* [包扫描](#包扫描)
* [@Value注解](#@Value注解)
* [基于注解@Autowired的依赖注入](#基于注解@Autowired的依赖注入)
* [类型转换（一）](#类型转换一)
* [类型转换（二）](#类型转换二)

#### 高级篇
* [解决循环依赖问题（一）：没有代理对象](#解决循环依赖问题（一）：没有代理对象)
* [解决循环依赖问题（二）：有代理对象](#解决循环依赖问题（二）：有代理对象)

#### bug fix
* [没有为代理bean设置属性(discovered and fixed by kerwin89)](#没有为代理bean设置属性)

## 使用方法
每个功能点对应一个分支，切换到功能点对应的分支了解新增的功能，增量改动点在[changelog.md](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md)文件中描述。

## 贡献
欢迎Pull Request。

## 联系我
欢迎探讨跟mini-spring和其他技术相关的问题，个人邮箱：**15521077528@163.com**。

## 参考
- [《Spring源码深度解析》](https://book.douban.com/subject/25866350/)
- [《精通Spring 4.x》](https://book.douban.com/subject/26952826/)
- [tiny-spring](https://github.com/code4craft/tiny-spring)

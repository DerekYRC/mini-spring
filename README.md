# <img src="assets/spring-framework.png" width="80" height="80"> mini-spring
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/DerekYRC/mini-spring)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Stars](https://img.shields.io/github/stars/DerekYRC/mini-spring)](https://img.shields.io/github/stars/DerekYRC/mini-spring)
[![Forks](https://img.shields.io/github/forks/DerekYRC/mini-spring)](https://img.shields.io/github/forks/DerekYRC/mini-spring)

**[English](./README_en.md) | 简体中文**

**姊妹版：** 
 - [**mini-spring-cloud**](https://github.com/DerekYRC/mini-spring-cloud) **(简化版的spring cloud框架)**
 - [**mini-netty**](https://github.com/DerekYRC/mini-netty) **(简化版的netty框架)**

## 关于

**mini-spring**是简化版的spring框架，能帮助你快速熟悉spring源码和掌握spring的核心原理。抽取了spring的核心逻辑，**代码极度简化，保留spring的核心功能**，如IoC和AOP、资源加载器、事件监听器、类型转换、容器扩展点、bean生命周期和作用域、应用上下文等核心功能。

如果本项目能帮助到你，请给个**STAR，谢谢！！！**

## 功能
#### 基础篇
* [IoC](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基础篇IoC)
    * [实现一个简单的容器](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#最简单的bean容器)
    * [BeanDefinition和BeanDefinitionRegistry](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#BeanDefinition和BeanDefinitionRegistry)
    * [Bean实例化策略InstantiationStrategy](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#Bean实例化策略InstantiationStrategy)
    * [为bean填充属性](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#为bean填充属性)
    * [为bean注入bean](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#为bean注入bean)
    * [资源和资源加载器](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#资源和资源加载器)
    * [在xml文件中定义bean](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#在xml文件中定义bean)
    * [容器扩展机制BeanFactoryPostProcess和BeanPostProcessor](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#BeanFactoryPostProcess和BeanPostProcessor)
    * [应用上下文ApplicationContext](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#应用上下文ApplicationContext)
    * [bean的初始化和销毁方法](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#bean的初始化和销毁方法)
    * [Aware接口](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#Aware接口)
    * [bean作用域，增加prototype的支持](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#bean作用域增加prototype的支持)
    * [FactoryBean](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#FactoryBean)
    * [容器事件和事件监听器](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#容器事件和事件监听器)
* [AOP](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基础篇AOP)
    * [切点表达式](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#切点表达式)
    * [基于JDK的动态代理](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基于JDK的动态代理)
    * [基于CGLIB的动态代理](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基于CGLIB的动态代理)
    * [AOP代理工厂ProxyFactory](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#AOP代理工厂)
    * [几种常用的Advice: BeforeAdvice/AfterAdvice/AfterReturningAdvice/ThrowsAdvice](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#几种常用的AdviceBeforeAdviceAfterAdviceAfterReturningAdviceThrowsAdvice)
    * [PointcutAdvisor：Pointcut和Advice的组合](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#pointcutadvisorpointcut和advice的组合)
    * [动态代理融入bean生命周期](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#动态代理融入bean生命周期)
    
#### 扩展篇
* [PropertyPlaceholderConfigurer](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#propertyplaceholderconfigurer)
* [包扫描](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#包扫描)
* [@Value注解](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#value注解)
* [基于注解@Autowired的依赖注入](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#autowired注解)
* [类型转换（一）](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#类型转换一)
* [类型转换（二）](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#类型转换二)

#### 高级篇
* [解决循环依赖问题（一）：没有代理对象](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#解决循环依赖问题一没有代理对象)
* [解决循环依赖问题（二）：有代理对象](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#解决循环依赖问题二有代理对象)

#### 其他
* [没有为代理bean设置属性(discovered and fixed by kerwin89)](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#bug-fix没有为代理bean设置属性discovered-and-fixed-by-kerwin89)
* [支持懒加载和多切面增强(by zqczgl)](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#支持懒加载和多切面增强by-zqczgl)

## 使用方法
阅读[changelog.md](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md)

[视频教程(完整版)](https://www.bilibili.com/video/BV1nb4y1A7YJ)

## 提问
[**点此提问**](https://github.com/DerekYRC/mini-spring/issues/4)

## 贡献
欢迎Pull Request

## 关于我
[**点此了解**](https://github.com/DerekYRC)

手机/微信：**15975984828**  邮箱：**15975984828@163.com**

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=DerekYRC/mini-spring&type=Date)](https://star-history.com/#DerekYRC/mini-spring&Date)

## 版权说明
未取得本人书面许可，不得将该项目用于商业用途

## 参考
- [《Spring源码深度解析》](https://book.douban.com/subject/25866350/)
- [《Spring 源码解析》](http://svip.iocoder.cn/categories/Spring)
- [《精通Spring 4.x》](https://book.douban.com/subject/26952826/)
- [《tiny-spring》](https://github.com/code4craft/tiny-spring)

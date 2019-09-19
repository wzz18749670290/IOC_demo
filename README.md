# android IOC demo

**IOC**
[IOC百度百科](https://baike.baidu.com/item/控制反转/1158025?fromtitle=ioc&fromid=4853&fr=aladdin)
控制反转（Inversion of Control，缩写为IoC），是面向对象编程中的一种设计原则，可以用来减低计算机代码之间的耦合度。其中最常见的方式叫做依赖注入（Dependency Injection，简称DI），还有一种方式叫“依赖查找”（Dependency Lookup）。通过控制反转，对象在被创建的时候，由一个调控系统内所有对象的外界实体将其所依赖的对象的引用传递给它。也可以说，依赖被注入到对象中。

###### 实现功能

```
1.Activity类加入ContentView注解即可实现 activity.setContentView($ContentView值) 对layout布局文件的注入
2.Activity中的Widget成员变量加入ViewID注解即可实现 View widget=findViewById($ViewID值) 对widget成员变量值的注入
3.Activity中的Method中加入OnClick/OnLongClick/OnTouch注解即可实现widget对应事件的注入
```



该demo供android开发者初学IOC参考
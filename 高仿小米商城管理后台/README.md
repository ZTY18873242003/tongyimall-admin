# XiaoMiShopping-SpringBoot
高仿小米商城（别名：统一哥小店）PC版，是Vue + SpringBoot的前后端分离项目，已完成了商城系统的大部分，包括首页门户、商品分类、首页轮播、商品展示、购物车、地址管理等部分。商城剩余部分和管理端还在开发中。

## 主要技术栈
前端:vue2 + vue-router + vuex + axios + sass （vue-cli4搭建的）<br>
后端:Springboot + Mybatis + MySQL 


## 启动
&nbsp;&nbsp;&nbsp;&nbsp;使用数据库管理软件（如DataGrip或者navicat)运行xiaomi.sql脚本文件在本地建库建表及插入数据.<br>
&nbsp;&nbsp;&nbsp;&nbsp;数据库登录的用户名和密码在src/main/resources目录下的JDBC.properties文件中
#### 一、推荐开发工具：

> + IntelliJ IDEA 2020.2.1（业界公认最好的java开发工具，都2021年了就别再用那什么Eclipse了）
> + DataGrip 2020.1 x64（数据库管理工具，用这个就不用在命令行中纯手写建表建库代码、简单快捷、提高效率~.个人认为比Navicat好用）
> + WebStorm 2020.1x64（前端开发工具）
> 建议都安装上，可以破解，相关内容请自行百度

#### 二、运行环境
&nbsp;&nbsp;&nbsp;&nbsp;为保证项目能够运行，请至少安装好以下运行环境：
 + 1、Maven(项目对象模型，可以通过一小段描述信息来管理项目的构建，报告和文档的项目管理工具软件，教程：[Maven的安装与配置](https://blog.csdn.net/a805814077/article/details/100545928))
+ 2、MySQL 8.0+版本（不支持8.0版本以下的）
+ 3、Mysql-connect-java的jar包，并安装到后端项目目录中(可见教程：[Java连接MySQL mysql-connector-java-bin.jar驱动包的下载与安装](https://blog.csdn.net/qq_41950447/article/details/90085170)

#### 三、Github项目地址
&nbsp;&nbsp;&nbsp;&nbsp;项目目前分为商城前端（tongyimall-vue）和商城后端(tongyimall-api)两个部分，我分开放到两个仓库中了。后续考虑会加入管理端（tongyimall-admin）和管理后端(tongyimall-admin-api),敬请关注！<br>
&nbsp;&nbsp;&nbsp;&nbsp;商城前端部分：[XiaoMiShopping-Vue](https://github.com/ZTY18873242003/XiaoMiShopping-Vue) <br>
&nbsp;&nbsp;&nbsp;&nbsp;商城后端部分：[XiaoMiShopping-SpringBoot](https://github.com/ZTY18873242003/XiaoMiShopping-SpringBoot)

&nbsp;&nbsp;&nbsp;&nbsp;坚持不易，后续我还会继续完善项目。给个Star吧~
# tongyimall-admin
高仿小米商城管理端(分为管理前端（tongyimall-admin-vue)和管理后端(tongyimall-admin-api)两部分)，是Vue + SpringBoot的前后端分离项目，基于 vue.js，使用 vue-cli3 脚手架，引用 Element UI 组件库。

### 一、功能(在线地址:www.tongyi.site/admin,用户名:admin,密码:2000918131X)

-   登录(采用RSA加密算法)
-   商品上下架
-   商品图片管理
-   识别登录IP/浏览器/地点
-   首页大类推荐商品管理
-   首页底部推荐商品管理
-   404 / 403
-   自定义图标

## 二、运行环境
&nbsp;&nbsp;&nbsp;&nbsp;为保证项目能够运行，请至少安装好以下运行环境：
 + 1、Maven(项目对象模型，可以通过一小段描述信息来管理项目的构建，报告和文档的项目管理工具软件，教程：[Maven的安装与配置](https://blog.csdn.net/a805814077/article/details/100545928))
 + 2、MySQL 8.0+版本
 + 3、Node环境
 + 4、JDK1.8


## 三、快速启动
##### 1、前端部分

```
git clone https://github.com/ZTY18873242003/tongyimall-admin      //下载到本地

cd /高仿小米商城管理前端    //进入到前端目录

npm install          // 安装项目依赖(需要电脑上装有Node环境)，等待安装完成之后，安装失败可用 cnpm 或 yarn


npm run serve        // 开启服务器，浏览器访问 http://localhost:8080


npm run build        // 执行构建命令，生成的dist文件夹放在服务器下即可访问
```
##### 2、后端部分
```
使用root用户权限在本地新建名为xiaomi的mysql数据库，登录密码为:2000918131X,可在 /高仿小米商城管理后台/src/main/resources/JDBC.properties文件中修改密码

cd /高仿小米商城后台   //进入到后端目录

使用数据库管理工具(如Navicat)运行xiaomi.sql脚本文件(用户端也是这个数据库),或复制其中内容在命令行执行

打开IDEA或者Eclipse 运行 高仿小米商城管理后台/src/main/java/com/zty/xiaomiadmin/server/ServerApplication.java文件，默认是8081端口
```

## 四、完整项目地址
&nbsp;&nbsp;&nbsp;&nbsp;项目目前分为商城端（tongyimall）和管理端(tongyimall-admin)两个部分，我分开放到两个仓库中了。<br>
&nbsp;&nbsp;&nbsp;&nbsp;商城端：[tongyimall](https://github.com/ZTY18873242003/tongyimall) <br>
&nbsp;&nbsp;&nbsp;&nbsp;管理端：[tongyimall-admin](https://github.com/ZTY18873242003/tongyimall-admin)

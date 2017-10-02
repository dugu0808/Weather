# Weather
A weather applicaton for Android

> Version 1.0 
## 1.create a project

在模拟器中运行的测试TextView控件效果如下（2017.09.21）：

![](https://i.imgur.com/mgKqFJ2.png)

## 2.create a toolbar

要制作的界面分为四部分：“工具栏”、“今日天气信息”、“一周天气信息”和“其他”。

先制作顶部工具栏，包含“城市选择”、“分隔符”、“天气名称”、“定位”和“刷新”等控件。

顶部工具栏效果如下（2017.09.25）：

![](http://chuantu.biz/t6/65/1506343854x3673677007.png)

##3.Make today's weather interface

先使用静态文本数据测试，采用嵌套布局的方式，放置一个线性布局，采用垂直方式分布，分别放置今日天气、7日天气信息以及广告信息，并制定背景图片。

效果如下（2017.10.02）：

![](http://chuantu.biz/t6/75/1506911199x2728278770.png)
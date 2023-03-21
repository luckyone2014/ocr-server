

## 项目介绍

一个基于paddle ocr，提供http调用方式的springboot工程。





提供http调用方式，调用



## 快速开始：

1.环境准备

jdk 1.8

python 3.9.6

maven 3.2.5



安装paddle图片识别库

python -m pip install paddlepaddle==2.3.2 -i https://mirror.baidu.com/pypi/simple

python -m pip install paddleocr==paddleocr==2.6.0.1 -i https://mirror.baidu.com/pypi/simple



2.通过maven编译本项目



3.项目启动入口

```
启动类：
com.luckyone.ocrserver.OcrServerApplication

启动命令：
java -Xms128m -Xmx4092m -Dfile.encoding=utf-8 -Dspring.profiles.active=prod -jar  /root/ocr.jar
--自定义配置文件 application-prod.yml
```



4.客户端调用测试类

```
com.luckyone.ocrserver.OcrServerApplicationTests
```












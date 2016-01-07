
#Java Base Project

[![Build Status](https://travis-ci.org/tiaoling/java-base.svg?branch=master)](https://travis-ci.org/tiaoling/java-base)
[![Coverage Status](https://coveralls.io/repos/tiaoling/java-base/badge.svg?branch=master&service=github)](https://coveralls.io/github/tiaoling/java-base?branch=master)
[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/hyperium/hyper/master/LICENSE)


基于spring boot 的java快速开发项目模版

### 运行
项目自带嵌入式数据库，如需其他数据库，自行更改，运行前需已安装jdk,
jdk 对应版本可以在build.gradle 中修改
```
1. git clone 本项目
2. 进入项目根目录
3. 运行 ./gradlew bootRun
```

### 部署
配置 [capistrnao](https://github.com/capistrano/capistrano) 部署
运行：
```
cap test deploy
```

### 导入ide
```
1. 执行 ./gradlew eclipse 生成 eclipse 项目后导入
2. 执行 ./gradlew idea 生成 idea 项目后导入
```

### 特点
```
1. 快速开发
2. 测试驱动
3. 自动部署
```

### 使用技术
```
1. spring boot,
2. gradle
3. spring mvc
4. spring test,
5. junit
6. moktio
7. capistrano
8. thymeleaf
```





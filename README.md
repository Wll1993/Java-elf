# java-elf 项目实行接口关键字+数据驱动模式开发测试

- 关键字

该项目可以通过”接口名称“关键字，匹配该接口名称下的测试用例。

- 数据驱动数据驱动目前实行两种途径

1、excel数据驱动（比较传统）
示例代码见：EasypoiWxCaseService类

2、数据库数据驱动（更易用于扩展）
示例代码见：JpaWXCaseService类

可以根据自己维护用例的习惯进行其中的数据驱动选择

3、持续测试集成
配置jdk环境和maven环境并运行：
```$xslt
mvn clean test
```
执行testng.xml文件中配置的测试套件



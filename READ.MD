* 启动eureka

* 根据项目中的sql，在本地创建三个数据库

* 下载seata
* 配置seata连接数据库、连接eureka。
* seata有自己控制全局事务的三张表：全局事务表、分支事务表、全局锁
* 要自己建库，自己建表。
* 各个app的undo_log表也要自己建。

* seata-server要配置registry.conf、file.conf，db模式8.*的mysql和之前版本的mysql
用的包不一样。

### one
* one充当TM
* 也要想seata那样配置registry.conf、file.conf


* two 和 three  充当RM
* two 和three也要去配置registry.conf、file.conf
* 三个都要引入seata的jar包

# 业务流程
* one往db插入一条数据，然后调用two、three应用也去加入数据。 
  
* 在one的方法调用中加入这个注解就行了@GlobalTransactional(rollbackFor = Exception.class)
* two和three不需要加这个注解


# 用例
AT 模式：com.example.one.controller.OneController.one
TCC模式：com.example.one.controller.OneController.oneTcc


* 这些配置，本地eureka是7001的端口，seata是默认的8091。

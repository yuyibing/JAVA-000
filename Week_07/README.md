###### 2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
1. JDBC未设置rewriteBatchedStatements，默认为false,
批量执行的一组sql语句拆散，一条一条地发给MySQL数据库，直接造成较低的性能。

**测试插入100万订单模拟数据执行时间：171347ms**

2. JDBC设置rewriteBatchedStatements参数置为true, 驱动才批量执行SQL

**测试插入100万订单模拟数据执行时间：24587ms**

3. 利用MyBatis分100次批量插入10000条记录

**测试插入100万订单模拟数据执行时间：63916ms**

###### 2.（必做）读写分离 - 动态切换数据源版本 1.0
jdbc-work文件中

###### 3.（必做）读写分离 - 数据库框架版本 2.0
shardingdemo文件中
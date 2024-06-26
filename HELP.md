# Getting Started

### SpringMvc中controller是基于单例模式创建，所需要注意的问题
#### 单例Bean使用注意
> 单例Bean在整个应用内是共享的，所以需要考虑到线程安全问题，之前在玩springmvc时候，
> spring中controller层默认是基于单例模式实现的，有些开发者在controller层创建了一
> 些变量，那么这些变量实际上就变成共享的了，controller可能会被喝多线程访问，这些线程
> 并发去改controller层的共享变量，可能会存在数据错乱的问题，所以在使用的时候需要特别注意下。


### mybatis框架集成Springboot框架学习
### 1、初始化工程，实现简单的接口访问查询
```java
// 根据id查询商品信息
http://localhost:8080/product/single?id=1
// 根据id和名称查询商品信息(精确查询)
http://localhost:8080/product/single?id=1&pname=xxx

// 根据ID获取商品信息(存在事务的前提下)
http://localhost:8080/product/single/testByTransactional?id=1        

// 查询所有商品
http://localhost:8080/product/listAll
// 模糊查询部分商品
http://localhost:8080/product/listAll?pname=%E7%AC%94

```
### 2、SQL查询问题
> 需要确认先执行表关联，还是执行Where条件。

```sql

-- SQL 先表关联和where查询有什么区别， 查询条件：AND a4.delete_status = 0

-- 可以查到数据
SELECT
	a1.*,
	a2.model_name,
	a2.terminal_type,
	a3.factory_code,
	a3.factory_name
	,a4.chl_merchant_no 
FROM
	jp_user_terminal_info a1
	LEFT JOIN jp_user_terminal_model a2 ON a2.ID = a1.model_id
	LEFT JOIN jp_user_terminal_factory a3 ON a3.ID = a1.factory_id
	LEFT JOIN jp_user_channel_merch_term a4 ON a1.terminal_sn = a4.term_sn  AND a4.delete_status = 0 
WHERE
	a1.delete_status = 0 
	AND a1.one_level_agent_no = '83000021' 
ORDER BY
	a1.ID DESC


-- 查不到数据
SELECT
    a1.*,
    a2.model_name,
    a2.terminal_type,
    a3.factory_code,
    a3.factory_name
        ,a4.chl_merchant_no
FROM
    jp_user_terminal_info a1
        LEFT JOIN jp_user_terminal_model a2 ON a2.ID = a1.model_id
        LEFT JOIN jp_user_terminal_factory a3 ON a3.ID = a1.factory_id
        LEFT JOIN jp_user_channel_merch_term a4 ON a1.terminal_sn = a4.term_sn  
WHERE
  
    a1.delete_status = 0
  AND a4.delete_status = 0
  
  AND a1.one_level_agent_no = '83000021'
ORDER BY
    a1.ID DESC
```

### Spring中BeanDefinitionRegistryPostProcessor注册
> Mybatis中拦截器逻辑那块需要注意下
![img.png](img.png)
#### 拦截器支持以下
> InterceptorChain#pluginAll()
* Executor
* StatementHandler
* ResultSetHandler
* ParameterHandler

### 关键方法
#### MapperScannerConfigurer
> 1、实现接口 BeanDefinitionRegistryPostProcessor 和 InitializingBean 接口,
> 重点关注方法: postProcessBeanDefinitionRegistry() 和 afterPropertiesSet()
> 

#### ClassPathMapperScanner和ClassPathBeanDefinitionScanner
> scan() 和 doScan() 方法，可以用来扫描类上的注解

### 一级缓存、二级缓存以及装饰器模式
#### 1、一级缓存
```java

public class PerpetualCache implements Cache {

    private final String id;
    
    /**
     * 基于HashMap存储
     */
    private Map<Object, Object> cache = new HashMap<>();

    public PerpetualCache(String id) {
        this.id = id;
    }

    @Override
    public void putObject(Object key, Object value) {
        // 存入缓存HashMap中
        cache.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        // 从缓存HashMap中获取Val值
        return cache.get(key);
    }
}
```
> 基于PerpetualCache类实现，主要实现是HashMap

#### 不开启事务，对比一级缓存和二级缓存
> 请求地址: http://localhost:8080/product/single/testByNoTransactional?id=1
 
> 一级缓存是基于SqlSession实现，在不开启事务的前提下，每次执行完数据库操作，都会执行closeSqlSession()方法
> 关闭SqlSession,所以当每次执行数据库操作时都会执行创建SqlSession和关闭SqlSession的操作。

> 二级缓存中Cache是基于Mapper实现的，相当于每个Mapper如果开启二级缓存时，会生成一个SynchronizedCache缓存对象，
> 且每个对象对应一个Mapper文件。
> 
> 所以，综上所述所得到的结论是：一级缓存是基于SqlSession实现的；二级缓存是基于Mapper实现的。

#### 开启事务，对比一级缓存和二级缓存
> 请求地址: http://localhost:8080/product/single/testByTransactional?id=1

#### 2、二级缓存

#### 3、Mybatis框架所涉及的装饰器模式
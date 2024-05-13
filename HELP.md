# Getting Started

### mybatis框架集成Springboot框架学习
### 1、初始化工程，实现简单的接口访问查询
```java
// 根据id查询商品信息
http://localhost:8080/product/single?id=1
// 根据id和名称查询商品信息(精确查询)
http://localhost:8080/product/single?id=1&pname=xxx

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

#### 2、二级缓存

#### 3、Mybatis框架所涉及的装饰器模式
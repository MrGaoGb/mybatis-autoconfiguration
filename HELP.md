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

### Guides




## 计算机协会社区

## 资料
[Spring 文档](https://spring.io/guides)
[Spring web文档](https://spring.io/guides/gs/serving-web-content)
[es](https://elasticsearch.cn/explore)
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[Bootstrap](https://v3.bootcss.com/getting-started/)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

## 工具
[Git](https://git-scm.com/download)
[Visual Paradigm](https://www.visual-paradigm.com)
[Flayway](https://flywaydb.org/)
[lombok](https://www.cnblogs.com/heyonggang/p/8638374.html)
[thymeleaf](https://www.thymeleaf.org/)
[spring-boot-devtools](https://www.jianshu.com/p/f658fed35786)
[springMVC](https://docs.spring.io/spring/docs/5.2.4.RELEASE/spring-framework-reference/)

## 脚本
```sql
-- auto-generated definition
create table user
(
    id           int auto_increment
        primary key,
    account_id   varchar(100) null,
    name         varchar(50)  null,
    token        char(36)     null,
    gmt_create   varchar(200)      null,
    gmt_modified varchar(200)       null
);
```
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite = true mybatis-generator：generate
```

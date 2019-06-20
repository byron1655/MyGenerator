### Mybatis generator

[mybatis-generator官方文档](http://www.mybatis.org/generator/)

#### 依赖项
```xml
<dependency>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-core</artifactId>
    <version>1.3.7</version>
</dependency>
```

#### 使用方法
pom.xml中build加入以下内容

```xml
<!-- mybatis generator 自动生成代码插件 -->
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.3.7</version>
    <configuration>
        <configurationFile>${basedir}/src/main/resources/generator/MybatisGenerator.xml</configurationFile>
        <overwrite>true</overwrite>
        <verbose>true</verbose>
    </configuration>
    <!-- 配置数据库链接及mybatis generator core依赖 生成mapper时使用 -->
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.16</version>
        </dependency>
        <dependency>
            <groupId>com.witvillage.tools</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.0.0</version>
        </dependency>

    </dependencies>
</plugin>
```

#### 默认generator位置
```
src/main/resources/generator/MybatisGenerator.xml
```
1. 复制根目录中的[generatorConfig.xml](https://github.com/byron1655/MyGenerator/blob/master/generatorConfig.xml)
2. 将generatorConfig.xml改名为MybatisGenerator.xml
3. 放置到src/main/resources/generator/中


#### 数据库配置
- 可修改jdbcConnection节点，也可将数据库配置放置于pom.xml中
- 修改table节点schema、tableName、domainObjectName

> schema 数据库名称

> tableName 表名

> domainObjectName 生成的对象名称


#### 生成文件情况
```
src/
  - main
      - java
          - com
              - wakanda
                  - tools
                      - mapper
                          - UserMapper.java
                          - UserSqlProvider.java
                      - model
                          - User.java


```

#### Intellij IDEA 运行方法
1. 新增一个Run运行，命名为**generator**
2. 在 “Command line” 选项中输入“mybatis-generator:generate  -e”
3. **run**

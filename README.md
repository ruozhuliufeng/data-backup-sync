# 数据备份与同步

> 默认使用H2数据库存储数据，可以在运行时指定数据库(暂时仅支持自定义MySQL数据库)
> 使用SpringSecurity进行权限控制，避免数据泄漏

## 开始
### Jar包运行
```shell
java -jar data-backup-sync.jar
```

### Docker运行
```shell
docker run --name data-backup-sync   \
    -d \
    -v <path-to-data-directory>:/data/backup/ \
    -p <port-on-host>:5525 \
    -e DB_HOST=<your-mysql-host> \
    -e DB_PORT=<your-mysql-port> \
    -e DB_NAME=<your-mysql-database> \
    -e DB_USER=<your-mysql-user> \
    -e DB_PASSWORD=<your-mysql-password> \
    ruozhuliufeng/data-backup-sync 
```
**启动参数说明：** <br/>
系统启动默认使用内置的H2数据库，升级、重启容器会丢失数据，建议使用自定义的MySQL数据库，配置信息如下：
- DB_HOST：数据库主机，当前仅支持MySQL数据库
- DB_PORT：数据库端口，当前仅支持MySQL数据库
- DB_NAME：数据库名称，当前仅支持MySQL数据库
- DB_USER：数据库用户，当前仅支持MySQL数据库
- DB_PASSWORD：数据库密码，当前仅支持MySQL数据库

还需要指定同步文件夹及端口，端口默认为5525端口
## 使用说明

- 默认H2数据库连接地址为：ip:port/h2-console,连接地址为 ~/data-backup-sync,默认密码为123456,可通过配置文件修改,建议更换为自定义的MySQL数据库
- 默认新增了一个管理员账户，用户名/密码：admin/admin
- 默认端口为5525,支持自定义

## 功能说明

- 使用Spring Data JPA维护表结构,使用MyBatis-Plus对数据表进行操作
- 配置信息保存在自定义的数据库中,由使用者自己维护数据安全
- 指定数据库(目前仅支持MySQL)进行备份并生成备份文件
- 将指定文件或目录的数据同步到云端(云端存储如阿里云OSS、华为云OBS、百度云BOS、腾讯云COS、七牛云Kodo、Webdav等)
- 界面使用光年后台模板，结合使用Vue、ElementUI与Axios进行界面展示与数据传输
- 根据数据库中的定时任务表达式,定时执行任务
## 页面展示

## 后续开发

- [ ] 使用Spring Security管理数据与接口权限
- [ ] 支持更多云端存储数据
- [ ] 支持备份其他自定义数据库
- [ ] 支持其他自定义数据库

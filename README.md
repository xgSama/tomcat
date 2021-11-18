启动类：`org.apache.catalina.startup.Bootstrap`

vm参数：
```bash
-Dcatalina.home=catalina-home -Dcatalina.base=catalina-home
-Djava.endorsed.dirs=catalina-home/endorsed -Djava.io.tmpdir=catalina-home/temp
-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
-Djava.util.logging.config.file=catalina-home/conf/logging.properties
-Dfile.encoding=UTF-8
```

国际化乱码问题修改的地方：
1. `org.apache.jasper.compiler.Localizer.getMessage(java.lang.String)`
2. `org.apache.tomcat.util.res.StringManager.getString(java.lang.String)`

修改方式：
```java
str = bundle.getString(key);
byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
str = new String(bytes, StandardCharsets.UTF_8);
```

server.xml修改
```xml
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443"
           URIEncoding="UTF-8"/>
```
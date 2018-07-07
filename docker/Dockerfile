FROM registry.zb100.com/java:8
MAINTAINER weiwen@zb100.com
VOLUME /tmp
RUN sh -c 'touch /app.jar'
RUN sh -c "mkdir -p /opt/app/logs && touch /opt/app/logs/gc.log"
ENV JVM_XMS 128m
ENV JVM_XMX 512m
ENV JAVA_OPTS -server -Xloggc:/opt/app/logs/gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Dfile.encoding=UTF8 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/app/logs/java.hprof -Djava.security.egd=file:/dev/urandom
ENV TOMCAT_PORT 8080
#ENV SPRING_PROFILE deploy
WORKDIR /opt/app
ADD app.jar app.jar
LABEL zb100.container.name="$SERVER_NAME"
EXPOSE $TOMCAT_PORT
#ENTRYPOINT java -Dcontainer.host.ip=$CONTAINER_HOST_IP $JAVA_OPTS -Dserver.name=$SERVER_NAME -Xms$JVM_XMS -Xmx$JVM_XMX -jar /opt/app/app.jar --spring.profiles.active=$SPRING_PROFILE --server.port=$TOMCAT_PORT
ENTRYPOINT java -Dcontainer.host.ip=$CONTAINER_HOST_IP $JAVA_OPTS -Dserver.name=$SERVER_NAME -Xms$JVM_XMS -Xmx$JVM_XMX -jar /opt/app/app.jar --server.port=$TOMCAT_PORT

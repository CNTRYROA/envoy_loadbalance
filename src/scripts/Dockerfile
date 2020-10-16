FROM  registry.cntryroa.com/javaruntime:5.1.0

COPY lib /opt/workdir/lib
COPY envoy_loadbalance-1.0-SNAPSHOT.jar /opt/workdir

CMD ["/jre1.8.0_202/bin/java","-jar","envoy-1.0-SNAPSHOT.jar"]

FROM quay.io/wildfly/wildfly:27.0.1.Final-jdk17
COPY target/weblab3.war /opt/jboss/wildfly/standalone/deployments/

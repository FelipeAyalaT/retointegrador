FROM jboss/keycloak:latest

ENV KEYCLOAK_USER=felipeayala
ENV KEYCLOAK_PASSWORD=felipeayala

COPY realm-export.json /opt/jboss/keycloak/realm-export.json

ENV JAVA_OPTS -server \
 -Xms1303m \
 -Xmx1303m \
 -XX:MetaspaceSize=96M \
 -XX:MaxMetaspaceSize=256m \
 -Djboss.modules.system.pkgs=org.jboss.byteman \
 -Djava.awt.headless=true \
 -Djava.net.preferIPv4Stack=true \
 -Dkeycloak.migration.strategy=OVERWRITE_EXISTING 

CMD ["-b", "0.0.0.0", "-Dkeycloak.profile.feature.upload_scripts=enabled", "-Dkeycloak.import=/opt/jboss/keycloak/realm-export.json", "-Dkeycloak.migration.strategy=OVERWRITE_EXISTING"]
EXPOSE 8080



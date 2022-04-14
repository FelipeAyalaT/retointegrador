FROM mysql:5.7

ENV MYSQL_ROOT_PASSWORD=B00tcamp2022
ENV MYSQL_DATABASE=BDPAGOSERVICIOS
ENV MYSQL_ROOT_HOST=%

COPY ./scripts/ /docker-entrypoint-initdb.d/
CMD ["--default-authentication-plugin=mysql_native_password"]
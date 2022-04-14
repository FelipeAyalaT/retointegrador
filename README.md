# RETO INTEGRADOR PAGO SERVICIOS

## 1. Artefactos
Se tiene la siguiente estructura de carpetas y contenido: 

database/
Contiene los script de creacion de bd, tablas y datos para de los servicios brindados
PagoServicios-backend/
	Contiene los proyectos para el backend
	apiConfigServer/ 
		Proyecto para obtener todas configuraciones de dev para los 3 servicios, 
		https://github.com/FelipeAyalaT/bootcamp-pagoservicios-configserver
	apiPagoServicios/ 
		Proyecto principal, invoca a pagos y favoritos
	apiPSFavoritos/
	apiPSPagos/
PagoServicios-config/ 
	Contiene los archivos de configuraciones de los proyectos, se implemento para dev
PagoServicios-keycloak/
	Se contenerizo el servicio de keycloak para la autorizacion, en desarrollo se exporto la configuracion de creacion de realm, roles y usuarios, en el Dockerfile y Deployment
	Se importa las configuraciones realm-export.json, para ingresar usar usuario y password felipeayala
docker-compose.yml
	Cada servicio cuenta con un Dockerfile que es ejecutado con el docker-compose con el objetivo de generar las imagenes que se subieron a la cuenta de dockehub
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_apiservicios
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_apipagos
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_apifavoritos
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_keycloak
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_apiconfigserver
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_mysql
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_mongo
integrador-keycloak-configmap.yaml
	Contiene las variables de inicio de sesion principal del keycloak
integrador-keycloak-deployment-svc.yaml
	Desliegue de keycloak con ip externa, una vez se tiene ip se configura en el confimap "integrador-retointegrador-configmap.yaml" en el key host_keycloak
integrador-retointegrador-configmap.yaml
	Contiene todas las variables a usar en los servicios y base de datos
integrador-retointegrador-deployment-svc.yaml
	Desliegue de los pods mediantes deployment y genera servicios externos para el apiservicios-service
	
## 2. Ejecucion de Docker-compose

Nos ubicamos en la raiz donde se encuentra el docker-compose.yml

Ejecutamos Docker-compose up, una vez implementado con control C salimos

C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>docker images
REPOSITORY                                   TAG           IMAGE ID       CREATED         SIZE
jboss/keycloak                               latest        dd72be25d3d7   17 hours ago    760MB
retointegrador_apipagos                      latest        234528e27072   19 hours ago    361MB
mysql                                        5.7           2aa65983aa4c   6 days ago      372MB
retointegrador_apifavoritos                  latest        c43c7fbd72b0   6 days ago      357MB
retointegrador_apiconfigserver               latest        5cb41727ef4b   7 days ago      344MB
dockercompose_apiblog                        latest        5e329ffaadbc   3 weeks ago     348MB
bcpretodockerhub                             latest        2bc82cc3d7cf   3 weeks ago     327MB
mongo                                        latest        5285cb69ea55   2 months ago    698MB
mcr.microsoft.com/mssql/server               2019-latest   d78e982c2f2b   2 months ago    1.48GB
mongo                                        3.6           2f21415cb85f   11 months ago   453MB


## 3. Creacion de tag e imagenes en dockerhub


Ejecutamos los siguientes comandos para crear los tag para dockerhub 

docker tag retointegrador_apiservicios:latest felipeayala/retointegrador_apiservicios
docker tag retointegrador_apipagos:latest felipeayala/retointegrador_apipagos
docker tag retointegrador_apifavoritos:latest felipeayala/retointegrador_apifavoritos
docker tag retointegrador_apiconfigserver:latest felipeayala/retointegrador_apiconfigserver
docker tag jboss/keycloak:latest felipeayala/retointegrador_keycloak
docker tag mysql:5.7 felipeayala/retointegrador_mysql
docker tag mongo:3.6 felipeayala/retointegrador_mongo

Enviamos las imagenes al dockerhub con push

docker push felipeayala/retointegrador_apiservicios
docker push felipeayala/retointegrador_apipagos
docker push felipeayala/retointegrador_apifavoritos
docker push felipeayala/retointegrador_apiconfigserver
docker push felipeayala/retointegrador_keycloak
docker push felipeayala/retointegrador_mysql
docker push felipeayala/retointegrador_mongo

Docker images

C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>docker images
REPOSITORY                                   TAG           IMAGE ID       CREATED         SIZE
felipeayala/retointegrador_keycloak          latest        dd72be25d3d7   17 hours ago    760MB
jboss/keycloak                               latest        dd72be25d3d7   17 hours ago    760MB
felipeayala/retointegrador_apipagos          latest        234528e27072   19 hours ago    361MB
retointegrador_apipagos                      latest        234528e27072   19 hours ago    361MB
felipeayala/retointegrador_mysql             latest        2aa65983aa4c   6 days ago      372MB
mysql                                        5.7           2aa65983aa4c   6 days ago      372MB
felipeayala/retointegrador_apifavoritos      latest        c43c7fbd72b0   6 days ago      357MB
retointegrador_apifavoritos                  latest        c43c7fbd72b0   6 days ago      357MB
felipeayala/retointegrador_apiconfigserver   latest        5cb41727ef4b   7 days ago      344MB
retointegrador_apiconfigserver               latest        5cb41727ef4b   7 days ago      344MB
dockercompose_apiblog                        latest        5e329ffaadbc   3 weeks ago     348MB
felipeayala/bcpretodockerhub                 latest        2bc82cc3d7cf   3 weeks ago     327MB
bcpretodockerhub                             latest        2bc82cc3d7cf   3 weeks ago     327MB
mongo                                        latest        5285cb69ea55   2 months ago    698MB
mcr.microsoft.com/mssql/server               2019-latest   d78e982c2f2b   2 months ago    1.48GB
mongo                                        3.6           2f21415cb85f   11 months ago   453MB
felipeayala/retointegrador_mongo             latest        2f21415cb85f   11 months ago   453MB


## 4. Autenticacion en az aks

Confirmado procedemos a logearnos al aks con la suscripcion ejecutando los siguiente comandos

az account set --subscription id-suscripcion
az aks get-credentials --resource-group rg-retointegrador --name aks-retointegrador


## 5. Depleyment en az aks

1. Ejecutamos el deployment del configmap para el keycloak
kubectl apply -f integrador-keycloak-configmap.yaml

configmap/retointegrador-configmap created

Opcional para eliminar
kubectl delete -f integrador-keycloak-configmap.yaml

2. Realizamos el deployment del keycloak
kubectl apply -f integrador-keycloak-deployment-svc.yaml

Opcional para eliminar
kubectl delete -f integrador-keycloak-deployment-svc.yaml

3. Ubicamos la ip externa generado para el servicio keycloak con los siguientes comandos
kubectl get service

4. Actualizamos la variable host_keycloak con la IP del Keycload

5. Ejecutamos el el configmap de los servicios
kubectl apply -f integrador-retointegrador-configmap.yaml

Opcional para eliminar
kubectl delete -f integrador-retointegrador-configmap.yaml
 

6. Realizamos el deployment de los servicios 
kubectl apply -f integrador-retointegrador-deployment-svc.yaml

C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>kubectl apply -f integrador-retointegrador-deployment-svc.yaml
deployment.apps/apiconfigserver created
service/apiconfigserver-service created
deployment.apps/mongodbserver created
service/mongodbserver-service created
deployment.apps/mysqlserver created
service/mysqlserver-service created
deployment.apps/apifavoritos created
service/apifavoritos-service created
deployment.apps/apipagos created
service/apipagos-service created
deployment.apps/apiservicios created
service/apiservicios-service created


Opcional para eliminar
kubectl delete -f integrador-retointegrador-deployment-svc.yaml

7. Podemos ver los Pods  y servicios
kubectl get pods
kubectl get pods -o wide
kubectl get service

C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>kubectl get pods
NAME                               READY   STATUS    RESTARTS   AGE
apiconfigserver-7d8dcf9b69-7pj9t   1/1     Running   0          13s
apifavoritos-7c97b6ff6f-cbwq6      1/1     Running   0          11s
apifavoritos-7c97b6ff6f-nkkcf      1/1     Running   0          11s
apipagos-7747dc5d7c-8gdp9          1/1     Running   0          10s
apipagos-7747dc5d7c-pt6ch          1/1     Running   0          10s
apiservicios-57c67848bc-866ff      1/1     Running   0          10s
apiservicios-57c67848bc-lcp54      1/1     Running   0          10s
apiservicios-57c67848bc-trz6n      1/1     Running   0          10s
keycloak-5d7cdf5766-cljbn          1/1     Running   0          124m
mongodbserver-869d4f9967-488gx     1/1     Running   0          13s
mysqlserver-fcb845dfc-wngt7        1/1     Running   0          12s

C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>kubectl get service
NAME                      TYPE           CLUSTER-IP     EXTERNAL-IP      PORT(S)          AGE
apiconfigserver-service   ClusterIP      10.0.5.195     <none>           8888/TCP         23s
apifavoritos-service      ClusterIP      10.0.201.94    <none>           8085/TCP         21s
apipagos-service          ClusterIP      10.0.191.132   <none>           8088/TCP         20s
apiservicios-service      LoadBalancer   10.0.4.45      20.75.128.84     8083:30811/TCP   19s
keycloak-service          LoadBalancer   10.0.149.78    20.121.100.125   8080:32527/TCP   124m
kubernetes                ClusterIP      10.0.0.1       <none>           443/TCP          136m
mongodbserver-service     ClusterIP      10.0.230.178   <none>           27017/TCP        22s
mysqlserver-service       ClusterIP      10.0.192.200   <none>           3306/TCP         22s


## 6. Pruebas Postman

Ejecutar el comando para ubicar la ip externa del api de servicios de pago y la Ip del keycload para la generacion del token
kubectl get service

apiservicios-service      LoadBalancer   10.0.4.45      20.75.128.84     8083:30811/TCP   19s
keycloak-service          LoadBalancer   10.0.149.78    20.121.100.125   8080:32527/TCP   124m


Ubicar e importar en el postman la collection Bootcamp.postman_collection.json, solo usar la carpeta "Retointegrador"

1. El metodo generaToken, tiene parametros ya establecidos con las configuraciones que se exporto e importo en el keycload, sin embargo, se puede configurar nuevos parametros.
En la URL se debe colocar la IP externa del servicio keycloak-service

2. El metodo realizarPago, enviar el token y ejecutar, internamente se genera el pago y se registra un favorito

{
  "suministro": "SSSEWQ3123125",
  "monto": 100,
  "servicioId": 4,
  "username": "felipeayala"
}


3. Opcionales consulta de favoritos generados por usuario username

/favoritos/felipeayala

# RETO INTEGRADOR PAGO SERVICIOS

## 1. Artefactos
Se tiene la siguiente estructura de carpetas y contenido: <br/>

database/<br/>
Contiene los script de creacion de bd, tablas y datos para de los servicios brindados<br/>
PagoServicios-backend/<br/>
	Contiene los proyectos para el backend<br/>
	apiConfigServer/ <br/>
		Proyecto para obtener todas configuraciones de dev para los 3 servicios, <br/>
		https://github.com/FelipeAyalaT/bootcamp-pagoservicios-configserver<br/>
	apiPagoServicios/ <br/>
		Proyecto principal, invoca a pagos y favoritos<br/>
	apiPSFavoritos/<br/>
	apiPSPagos/<br/>
PagoServicios-config/ <br/>
	Contiene los archivos de configuraciones de los proyectos, se implemento para dev<br/>
PagoServicios-keycloak/<br/>
	Se contenerizo el servicio de keycloak para la autorizacion, en desarrollo se exporto la configuracion de creacion de realm, roles y usuarios, en el Dockerfile y Deployment<br/>
	Se importa las configuraciones realm-export.json, para ingresar usar usuario y password felipeayala<br/>
docker-compose.yml<br/>
	Cada servicio cuenta con un Dockerfile que es ejecutado con el docker-compose con el objetivo de generar las imagenes que se subieron a la cuenta de dockehub<br/>
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_apiservicios
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_apipagos
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_apifavoritos
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_keycloak
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_apiconfigserver
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_mysql
	https://hub.docker.com/repository/docker/felipeayala/retointegrador_mongo
	<br/>
integrador-keycloak-configmap.yaml<br/>
	Contiene las variables de inicio de sesion principal del keycloak<br/>
integrador-keycloak-deployment-svc.yaml<br/>
	Desliegue de keycloak con ip externa, una vez se tiene ip se configura en el confimap "integrador-retointegrador-configmap.yaml" en el key host_keycloak<br/>
integrador-retointegrador-configmap.yaml<br/>
	Contiene todas las variables a usar en los servicios y base de datos<br/>
integrador-retointegrador-deployment-svc.yaml<br/>
	Desliegue de los pods mediantes deployment y genera servicios externos para el apiservicios-service<br/>
	
## 2. Ejecucion de Docker-compose

Nos ubicamos en la raiz donde se encuentra el docker-compose.yml<br/>

Ejecutamos Docker-compose up, una vez implementado con control C salimos
<br/>
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

<br/>
Ejecutamos los siguientes comandos para crear los tag para dockerhub 
<br/>
docker tag retointegrador_apiservicios:latest felipeayala/retointegrador_apiservicios<br/>
docker tag retointegrador_apipagos:latest felipeayala/retointegrador_apipagos<br/>
docker tag retointegrador_apifavoritos:latest felipeayala/retointegrador_apifavoritos<br/>
docker tag retointegrador_apiconfigserver:latest felipeayala/retointegrador_apiconfigserver<br/>
docker tag jboss/keycloak:latest felipeayala/retointegrador_keycloak<br/>
docker tag mysql:5.7 felipeayala/retointegrador_mysql<br/>
docker tag mongo:3.6 felipeayala/retointegrador_mongo<br/>
<br/>
Enviamos las imagenes al dockerhub con push
<br/>
docker push felipeayala/retointegrador_apiservicios<br/>
docker push felipeayala/retointegrador_apipagos<br/>
docker push felipeayala/retointegrador_apifavoritos<br/>
docker push felipeayala/retointegrador_apiconfigserver<br/>
docker push felipeayala/retointegrador_keycloak<br/>
docker push felipeayala/retointegrador_mysql<br/>
docker push felipeayala/retointegrador_mongo
<br/>
Docker images

C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>docker images<br/>
REPOSITORY                                   TAG           IMAGE ID       CREATED         SIZE<br/>
felipeayala/retointegrador_keycloak          latest        dd72be25d3d7   17 hours ago    760MB<br/>
jboss/keycloak                               latest        dd72be25d3d7   17 hours ago    760MB<br/>
felipeayala/retointegrador_apipagos          latest        234528e27072   19 hours ago    361MB<br/>
retointegrador_apipagos                      latest        234528e27072   19 hours ago    361MB<br/>
felipeayala/retointegrador_mysql             latest        2aa65983aa4c   6 days ago      372MB<br/>
mysql                                        5.7           2aa65983aa4c   6 days ago      372MB<br/>
felipeayala/retointegrador_apifavoritos      latest        c43c7fbd72b0   6 days ago      357MB<br/>
retointegrador_apifavoritos                  latest        c43c7fbd72b0   6 days ago      357MB<br/>
felipeayala/retointegrador_apiconfigserver   latest        5cb41727ef4b   7 days ago      344MB<br/>
retointegrador_apiconfigserver               latest        5cb41727ef4b   7 days ago      344MB<br/>
dockercompose_apiblog                        latest        5e329ffaadbc   3 weeks ago     348MB<br/>
felipeayala/bcpretodockerhub                 latest        2bc82cc3d7cf   3 weeks ago     327MB<br/>
bcpretodockerhub                             latest        2bc82cc3d7cf   3 weeks ago     327MB<br/>
mongo                                        latest        5285cb69ea55   2 months ago    698MB<br/>
mcr.microsoft.com/mssql/server               2019-latest   d78e982c2f2b   2 months ago    1.48GB<br/>
mongo                                        3.6           2f21415cb85f   11 months ago   453MB<br/>
felipeayala/retointegrador_mongo             latest        2f21415cb85f   11 months ago   453MB<br/>

<br/>
## 4. Autenticacion en az aks
<br/>
Confirmado procedemos a logearnos al aks con la suscripcion ejecutando los siguiente comandos<br/>
<br/>
az account set --subscription id-suscripcion
az aks get-credentials --resource-group rg-retointegrador --name aks-retointegrador
<br/>

## 5. Depleyment en az aks
<br/>
1. Ejecutamos el deployment del configmap para el keycloak
kubectl apply -f integrador-keycloak-configmap.yaml
<br/>
configmap/retointegrador-configmap created
<br/>
Opcional para eliminar
kubectl delete -f integrador-keycloak-configmap.yaml
<br/>
2. Realizamos el deployment del keycloak
kubectl apply -f integrador-keycloak-deployment-svc.yaml
<br/>
Opcional para eliminar
kubectl delete -f integrador-keycloak-deployment-svc.yaml
<br/>
3. Ubicamos la ip externa generado para el servicio keycloak con los siguientes comandos
kubectl get service
<br/>
4. Actualizamos la variable host_keycloak con la IP del Keycload
<br/>
5. Ejecutamos el el configmap de los servicios
kubectl apply -f integrador-retointegrador-configmap.yaml
<br/>
Opcional para eliminar
kubectl delete -f integrador-retointegrador-configmap.yaml
 <br/>

6. Realizamos el deployment de los servicios 
kubectl apply -f integrador-retointegrador-deployment-svc.yaml
<br/>
C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>kubectl apply -f integrador-retointegrador-deployment-svc.yaml<br/>
deployment.apps/apiconfigserver created<br/>
service/apiconfigserver-service created<br/>
deployment.apps/mongodbserver created<br/>
service/mongodbserver-service created<br/>
deployment.apps/mysqlserver created<br/>
service/mysqlserver-service created<br/>
deployment.apps/apifavoritos created<br/>
service/apifavoritos-service created<br/>
deployment.apps/apipagos created<br/>
service/apipagos-service created<br/>
deployment.apps/apiservicios created<br/>
service/apiservicios-service created<br/>
<br/>
<br/>
Opcional para eliminar<br/>
kubectl delete -f integrador-retointegrador-deployment-svc.yaml<br/>
<br/>
7. Podemos ver los Pods  y servicios<br/>
kubectl get pods<br/>
kubectl get pods -o wide<br/>
kubectl get service<br/>
<br/>
C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>kubectl get pods<br/>
NAME                               READY   STATUS    RESTARTS   AGE<br/>
apiconfigserver-7d8dcf9b69-7pj9t   1/1     Running   0          13s<br/>
apifavoritos-7c97b6ff6f-cbwq6      1/1     Running   0          11s<br/>
apifavoritos-7c97b6ff6f-nkkcf      1/1     Running   0          11s<br/>
apipagos-7747dc5d7c-8gdp9          1/1     Running   0          10s<br/>
apipagos-7747dc5d7c-pt6ch          1/1     Running   0          10s<br/>
apiservicios-57c67848bc-866ff      1/1     Running   0          10s<br/>
apiservicios-57c67848bc-lcp54      1/1     Running   0          10s<br/>
apiservicios-57c67848bc-trz6n      1/1     Running   0          10s<br/>
keycloak-5d7cdf5766-cljbn          1/1     Running   0          124m<br/>
mongodbserver-869d4f9967-488gx     1/1     Running   0          13s<br/>
mysqlserver-fcb845dfc-wngt7        1/1     Running   0          12s<br/>

C:\FELIPE\Proyectos\bcp\contenedores1\retointegrador>kubectl get service<br/>
NAME                      TYPE           CLUSTER-IP     EXTERNAL-IP      PORT(S)          AGE<br/>
apiconfigserver-service   ClusterIP      10.0.5.195     <none>           8888/TCP         23s<br/>
apifavoritos-service      ClusterIP      10.0.201.94    <none>           8085/TCP         21s<br/>
apipagos-service          ClusterIP      10.0.191.132   <none>           8088/TCP         20s<br/>
apiservicios-service      LoadBalancer   10.0.4.45      20.75.128.84     8083:30811/TCP   19s<br/>
keycloak-service          LoadBalancer   10.0.149.78    20.121.100.125   8080:32527/TCP   124m<br/>
kubernetes                ClusterIP      10.0.0.1       <none>           443/TCP          136m<br/>
mongodbserver-service     ClusterIP      10.0.230.178   <none>           27017/TCP        22s<br/>
mysqlserver-service       ClusterIP      10.0.192.200   <none>           3306/TCP         22s<br/>

<br/>
## 6. Pruebas Postman
<br/>
Ejecutar el comando para ubicar la ip externa del api de servicios de pago y la Ip del keycload para la generacion del token
kubectl get service
<br/>
apiservicios-service      LoadBalancer   10.0.4.45      20.75.128.84     8083:30811/TCP   19s
keycloak-service          LoadBalancer   10.0.149.78    20.121.100.125   8080:32527/TCP   124m

<br/>
Ubicar e importar en el postman la collection Bootcamp.postman_collection.json, solo usar la carpeta "Retointegrador"
<br/>
1. El metodo generaToken, tiene parametros ya establecidos con las configuraciones que se exporto e importo en el keycload, sin embargo, se puede configurar nuevos parametros.
En la URL se debe colocar la IP externa del servicio keycloak-service
<br/>
2. El metodo realizarPago, enviar el token y ejecutar, internamente se genera el pago y se registra un favorito
<br/>
{
  "suministro": "SSSEWQ3123125",
  "monto": 100,
  "servicioId": 4,
  "username": "felipeayala"
}

<br/>
3. Opcionales consulta de favoritos generados por usuario username
<br/>
/favoritos/felipeayala

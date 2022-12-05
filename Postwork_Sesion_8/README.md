# Manejo de Jenkins y Testing

Requisitos:
- Clonar repositorio de [pet-clinic-sonarqube](https://github.com/Eduardoscar/pet-clinic-sonarqube)
- Instalar Maven en el contenedor de jenkins
  - Buscar el nombre del container `docker container ls`
  - Entrar a la consola `docker exec -it -u root jenkins_eduardo /bin/bash` 
  - Instalar Maven `apt-get install maven` 
- Instalar los plugins
  - [HTML Publisher](https://plugins.jenkins.io/htmlpublisher/) (publicar reporte de pruebas)
  - [SonarQube Scanner](https://plugins.jenkins.io/sonar/) (conexión con sonarQube)

## 1) Infrastructura Jenkins y SonarQube con terraform
A nuestro repositorio de Infraestructura se le creo una nueva rama [Jenkins_y_SonarQube](https://github.com/Eduardoscar/Infraestructura/tree/Jenkins_y_SonarQube) donde se agrego el archivo `sonarqube.tf`
1. Para ejecutar `sonarqube.tf` nos colocaremos sobre nuestra carpeta que ya se inicializo con terraform en el postwork anterior y realizaremos los sigueintes pasos para levantarlo
   - `terraform validate` Validar los archivos .tf
   - `terraform fmt` Dar formato a los archivos .tf
   - `terraform plan` Hace plan para realizar el apply y muestra si hay cambios (solo para confirmar que veremos de cambios a `sonarqube.tf`)
   - `terraform apply` Realiza solo los cambios que visualizamos en `terraform plan`

   Obteniendo la siguiente salida donde podremos ver la direccion IP de SonarQube:  
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/Jenkins_SonarQube.png?raw=true)

   Verificamos que se contruyo todo correctamente:
   - Imagen   
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/imagen_sonarqube.png?raw=true)  
   - Contenedor   
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/contenedor_sonarqube.png?raw=true)

   Página de SonarQube: `http://localhost:9000/`  
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/Pagina_inicio_sonarqube.png?raw=true)

   Nota: para ingresar el usuario y contraseña es `admin`

## 2) Crear pipeline basado en SCM Git usando Jenkinsfile

1. Ingresamos a **Jenkins** con el **usuario administrador**.

2. En **Panel de control** nos dirigimos a **+ Nueva Tarea** agregamos nombre a nuestra tarea, después seleccionamos de tipo **Pipeline** seleccionamos **OK** para creala.

3.  Agregamos una descripción de nuestro Pipeline, después nos dirigimos a la parte de **Pipeline** en **Definicion** seleccionamos **`Pipeline scrip from SCM`**, en **SCM** seleccionamos **`Git`** , en el apartado **Repository URL** agregamos la url de nuestro repositorio de pet-clinic y en **Branch Specifier** seleccionamos la rama donde esta nuetro código a ejecutar, para finalizar seleccionamos **Apply** y después es **Guardar**.   
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/Pipeline_Pet_clinic.png?raw=true)

4. Ejecutamos **▶ Construir ahora** y esperamos a que termine de ejecutar.  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/ejecucion_pipeline.png?raw=true)

5. Ya terminado, verificamos que si se crearon los recursos  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/Build_terminado.png?raw=true)

   - Imagen   
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/imagen_pet.png?raw=true)  
   - Contenedor   
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/contenedor_pet.png?raw=true)
   - Página de PetClinic: `http://localhost:9966/`  
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/pagina_pet.png?raw=true)

## 3) Configurar SonarQube con token de admin para jenkins

1. En la página de SonarQube `http://localhost:9000/` nos dirigimos a nuestro perfil de **Administador** lo podemos identificar por un cuadro verde con la letra A, después seleccionamos **My Account** y seleccionamos **Security**, nos generamos un token y lo guardamos bien, ya que no se podra volver a ver.
2. Nos dirigimos a **⚙ Administrar Jenkins** después **🔑 Manage Credentials** y seleccionamos **Global**, después el boton azul **+ Add Credentials** y llenamos la siguiente configuración:
   - **Kind** : `Secret text`
   - **URL del servidor** : `http://172.17.0.4:9000` (la Ip es la mostrada cuando levantamos SonarQube)
   - **Secret** : (pegamos el token generado en SonarQube)
   - **ID** : `sonar_token`
   - **Description** : `Token para sonarQube`

   Para finalizar seleccionamos **Create**  
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/credencial_creada.png?raw=true)

3. En **Panel de control** nos dirigimos a **⚙ Administrar Jenkins** después **⚙ Configuración del sistema**, buscamos **SonarQube servers** y llenamos la siguiente configuración:
   - **Name** : `sonarqube`
   - **Scope** : `Global`
   - **Server authentication token** : `sonar_token` (**ID** que creamos en la credencial)

   Para finalizar seleccionamos **Guardar**

## 4) Configurar Jenkins para integrarse con SonarQube
Para confijurar nos dirigimos al archivo `Jenkinsfile` que se encuentra en nuestro repositorio de

y agregamos el siguiente código:

~~~
stage("SonarQube analysis") {
    steps {
        echo 'Executing Sonar analisis' 
        withSonarQubeEnv('sonarqube') {
        sh 'mvn clean package sonar:sonar'
      }
    }
}
~~~

En withSonarQubeEnv se le pasa el **Name** que se puso en la configuracion de **SonarQube servers**

Subimos nuestro cambio al repositorio y ejecutamos **▶ Construir ahora** en nuestro **Pipeline Postwork 8 Pet CLinic** y esperamos a que termine la ejecución:  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/ejecucion_SonarQube.png?raw=true)

Nos dirigimos a la página de SonarQube `http://localhost:9000/` para ver el Dashboard de la ejecución:  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/Dashboard_Sonar.png?raw=true)

Agregaremos otra configuración al archivo `Jenkinsfile` para abortar el Pipeline si no pasan las Pruebas de SonarQube (paso anterior)
~~~
stage ("Quality Gate Check"){
    steps {
        sleep 60
        waitForQualityGate abortPipeline: true
    }
}
~~~
Subimos nuestro cambio al repositorio y para ejecutar de forma automatica el Pipeline cada vez que se suba un push al repositorio serguir [Pipeline Automático](#Pipeline-Automático)

La ejecuación automatica con el nuevo cambio se ejecuto correctamente  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/Quality_Gate_Check.png?raw=true)


No aborto el Pipeline porque fueron aprovadas las pruebas en SonarQube  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/prubas_sonarqube_automatica.png?raw=true)

Nota: Verificación de que se genero Pipeline automatico  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/Pipeline_automatico_prueba.png?raw=true)


---
## Pipeline Automático
Nos dirigimos a nuestro Pipeline **Postwork 8 Pet CLinic** después **⚙ Configurar** y buscamos **Build Triggers**, seleccionamos **Consultar repositorio (SCM)** agregamos un **Programador** que revise el repositorio cada minuto (solo para este ejemplo) `* * * * * `  
 ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_8/assets/Pipeline_automatico.png?raw=true)

---
## ✅ Checklist
Considera que tu proyecto debe cumplir con lo siguiente:

[x] a. Configurar Pipeline basado en Groovy Jenkinsfile  
[x] b. Sumar pruebas de código estático con la herramienta  
[x] c. Sumar pruebas de código dinámico con la herramienta SonarQube  
[x] d. Con las pruebas estáticas y dinámicas incorporar quality gates para la aprobación entre las fases de construcción y de despliegue
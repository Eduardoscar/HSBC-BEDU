# Jenkins como herramienta básica

## 1) Instalación y configuracón de Jenkins
[Repositorio de Infraestructura](https://github.com/Eduardoscar/Infraestructura)

1. Inicializamos la carpeta donde se encuentra nuestro archivo de terraform `terraform.tf`  con: `terraform init`   
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/terraform_init.png?raw=true)

2. Validamos los archivos de configuración: `terraform validate`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/terraform_validate.png?raw=true)  

3. Le damos formato a nuestros archivos: `terraform fmt`    
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/terraform_fmt.png?raw=true)  

4. Creamos la infrastructura `terraform apply`, con `terraform apply -auto-approve` nos saltamos las confirmaciones manuales (no usar en despliegues productivos)  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/terraform_apply_1.png?raw=true)  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/terraform_apply_2.png?raw=true)  

5. Validamos que se crearon los recursos  
![Volumen](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Volumen_creado.png?raw=true)  
![imagenes](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/imagenes_creadas.png?raw=true)  
![contenedor Jenkins](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/contenedor_jenkins.png?raw=true)  

6. Al ingresar por primera vez a`http://localhost:8081` o al puerto que configuraste en tu archivo `jenkins.tf`.   
Veras lo siguiente  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/primera_pagina_jenkins.png?raw=true)  

7. Para obtener la contraseña, nos dirigimos a los  logs del contenedor de jenkins   
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/logs_jenkins_contraseña.png?raw=true)  

8. En la página principal de Jenkins, agregamos una descripción y estaremos listos para empezar a configurarlo.  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/jenkins_local.png?raw=true)  

9. Intalaremos los plugins que se van a necesitar  
Nos dirigimos a **⚙ Administrar Jenkins** después **🧩 Administrar Plugins** y en **👜 Available pluging** buscaremos el plugin a instalar y lo seleccionamos:  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/buscar_plugins_seleccionar.png?raw=true)   
Agregamos todos estos plugins
   - [Pipeline](https://plugins.jenkins.io/workflow-aggregator/)
   - [Docker](https://plugins.jenkins.io/docker-plugin/)
   - [Docker Pipeline](https://plugins.jenkins.io/docker-workflow/)
   - [Environment Injector](https://plugins.jenkins.io/envinject/)
   - [Git](https://plugins.jenkins.io/git/)
   - [GitHub](https://plugins.jenkins.io/github/)
   - [GitHub Authenticator](https://plugins.jenkins.io/github-oauth/)  
   - [Matrix Authorization Strategy](https://plugins.jenkins.io/matrix-auth/) (Nos servira para administrar los permisos de los usuarios)
   - [Pipeline: Stage View](https://plugins.jenkins.io/pipeline-stage-view/) (Sirve para ver de forma grafica el proceso de Pipeline) (Instalación Opcional)

   Ya que tenemos todos nuestos plugins seleccionados en la parte de abajo seleccionaremos **Install without restart** se empezaran a descargar e instalar los plugins   
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Instalando_dependencias_process.png?raw=true)

10. Administaremos los permisos de los usuarios seleccionando **⚙ Administrar Jenkins** después **🔒 Configuración global de la seguridad**
    - En **Seguridad** seleccionamos `Usar base de datos de Jenkins`
    - En **Autorización** seleccionamos `Estrategia de seguridad para proyecto` y se mostrara la siguiente tabla.   
    ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/administrador_permisos.png?raw=true)
    - Es obligatorio añadir a nuestro usuario 'Admin' seleccionamos el botón **Add user...** escribimos el nombre del usuario en mi caso es `Eduardo` si no creaste ningun usuario sera `admin`, seleccionaremos el cuadro que se encuentra enmarcado en color verde para activar todos los permisos   
    ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Activar_todos_permisos.png?raw=true)
    - Podemos ir agregando usuarios (que ya esten creados) y darles sus correspondientes permisos.
    ejemplo: `Usuario_read` solo tendra permisos de lectura  
    ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/usuario_read.png?raw=true)


1. Vamos a añadir las variables de entorno de la base de datos: En **Panel de control** nos dirigimos a **⚙ Administrar Jenkins** después **⚙ Configuración del sistema**, buscamos **Propiedades globales** y seleccionamos **Variables de entorno** vamos a añadir las siguientes:
    - MYSQL_IP = 172.17.0.3 (La IP Agregaremos la que nos mostro en cuando ejecutamos el archivo de terraform.tf)
    - MYSQL_PASSWORD = abcD_1234
    - MYSQL_USER = root
    
    ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/añadir_variables_entorno.png?raw=true)  
    Seleccionamos **Apply** y después es **Guardar** 

Con estos pasos ya tenemos Instalado y configurado Jenkins, esta configuración se mantendra ya que se guarda en el volumen creado.

--- 

## 2) Crear proyecto para tu microservicio
[Repositorio de myApi-books](https://github.com/Eduardoscar/myAPI-books)

*⚠ Nota: Todo este proceso solo se podra realizar con el usuario que declaramos como administrador*

1. En **Panel de control** nos dirigimos a **+ Nueva Tarea** agregamos nombre a nuestra tarea, después seleccionamos de tipo **Pipeline** seleccionamos **OK** para creala.  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Nombre_pipeline_ok.png?raw=true)   

2. Agregamos una descripción de nuestro Pipeline  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Agregamos_descripcion.png?raw=true)  

3. Nos dirigimos a la parte de **Pipeline** en Definicion seleccionamos **Pipeline scrip from SCM**  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Pipeline_SCM.png?raw=true)  

4. En **SCM** seleccionamos **Git**  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/SCM.png?raw=true)  

5. En el apartado **Repository URL** agregamos la url de nuestro repositorio, si nuestro repositorio es publico en **Credentials** se deja --none-- (si es privado tendremos que agregar nuetras credenciales)  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Url_repositorio.png?raw=true)  

6. Agregamos el nombre de nuestra rama donde se encuentra nuestros archivos.   
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/branch_git.png?raw=true)  

7. Agregamos el path y el nombre de nuestro archivo a ejecutar  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/path_archivo_jenkinsfile.png?raw=true)  

8. Para finalizar seleccionamos **Apply** y después es **Guardar** y nos mostrara la pagina de nuestro **Pipeline**  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Pipeline_Books.png?raw=true)  

--- 

## 3) Prueba el proyecto de tu microservicio
*⚠ Nota: Todo este proceso solo se podra realizar con el usuario que declaramos como administrador*

Si estas con un usuario con solo permisos de lectura solo veras las siguientes opciones en la vista de nuestro Pipeline **Postwork 7 My API books**   
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/vista_usuario_read.png?raw=true)

1. Estando en nuestro Pipeline **Postwork 7 My API books** seleccionaremos **▶ Construir ahora** y se contruir nustro proyecto.
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Pipeline_Books.png?raw=true)  

2. En la parte inferior izquierda se mostrara el primer build en ejecución  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/ejecucion_pipeline.png?raw=true)   
Si instalaste el plugin **Pipeline: Stage View** lo podras ver de la siguiente manera  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Si_stage_view.png?raw=true)  

3. Una vez finalizado podremos ver si concluyo correctamente  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/Construccion_correcta.png?raw=true)  
En caso  de que falle tenemos que seleccionar el build y despues ir a **Console Output**  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/detalle_build.png?raw=true)  

4. Verificamos que se contruyo todo correctamente:
   - Imagen   
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/books_imagen.png?raw=true)  
   - Contenedor   
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/books_contenedor.png?raw=true)

   Realizamos peticion al servicio de `myapi-books` para ver que responda `http://localhost:5001/health`  
   ![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_7/assets/books_peticion_health.png?raw=true)

---

## ✅ Checklist
Considera que tu proyecto debe cumplir con lo siguiente:

[X] a. Jenkins instalado en local.  
[X] b. Generar un pipeline para la construcción y despliegue de aplicación.  
[X] c. Configurar autenticación para el pipeline.  
[X] d. Agregar un disparador automático para la ejecución del pipeline.

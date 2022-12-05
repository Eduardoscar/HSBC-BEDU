
# Postwork Sesión 4
# Sesion 04 :  Fundamentos de DevSecOps

🎯 **Objetivo:**

- Comprender la diferencia entre una Imagen y un Contenedor.

- Utilizar un repositorio de Imagenes Docker (Docker Hub).

- Crear tu propia Imagen Docker.

---

## ⚙️ Setup

- Asegúrate de tener instalado en tu equipo Docker

    https://docs.docker.com/engine/install/

---

## 📒 Indicaciones generales

Con base en la aplicación que has ido desarrollando a lo largo del módulo, crearás imágenes Docker y lograr versionar las mismas, tomando en cuenta las siguientes indicaciones:

- Deberás descargar y manipular imágenes Docker.

- Versionar las imágenes creadas.

- Utilizar línea de comandos para comprender la Infraestructura.

- Desplegar un servidor de aplicaciones basado en Docker.

---

## 🚀 Desarrollo.
Nota: Tener instalado la ultima version de docker  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/Version_Docker.png?raw=true)

1.- Utiliza el repo de Apache
https://hub.docker.com/_/httpd

2.- Ejecuta el comando para descargar la Imagen a tu local:

`docker pull httpd`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/Descarga_Imagen_httpd.png?raw=true)


3.- Ejecuta el siguiente comando:
 
`docker images`  


4.- Verás una indicación similar como la siguiente:  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/docker_images.png?raw=true)


5.- Ahora, levantarás la imagen con lo siguiente:

`docker run -d --name apache-server -p 80:80 httpd`  

Con esto, tendrás el servidor de aplicaciones iniciado:

![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/levantar_image.png?raw=true)


6.- Ejecuta lo siguiente:

`docker ps`  


7.- Verás algo similar:

![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/docker_ps.png?raw=true)

8.- Ubica el Container ID y ejecútalo:  
Nota: el Container id sera el que obtuvo con el comando anterior

`docker exec -it bc52f73cf748 bash`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/ejecutar_Contenedor.png?raw=true)

9.- Una vez dentro de tu contenedor, actualiza:

`apt-get update & apt-get upgrade -y`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/actualizando_contenendor.png?raw=true)

10.- Instala un par de tools (wget, zip)

`apt install wget`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/installando_tool_wget.png?raw=true)

`apt install zip`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/installando_tool_zip.png?raw=true)

11.- Ubícate en el path:

`cd /usr/local/apache2/htdocs`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/ubicarte_path.png?raw=true)

12.- Descarga un zip, con el site a desplegar:

`wget https://github.com/beduExpert/DevOps-Fundamentals-2021/raw/main/Sesion-01/coming-soon-evening-html.zip`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/Descagar_zip_site.png?raw=true)

13.- Descomprime el zip:

`unzip coming-soon-evening-html.zip`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/Descomprimir_zip.png?raw=true)

14.- Actualiza el navegador, donde podrás ver el sitio actualizado como se muestra en la imagen.

![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_4/assets/iSite_actualizado.png?raw=true)

---

## ✅ Checklist

Requisito:  
[X] Tener ultima version Docker en local  
[X] Identificar los comandos Docker básicos   
[X] Iniciar el servidor de aplicaciones   
[X] Modificar la página de inicio del servidor de aplicaciones


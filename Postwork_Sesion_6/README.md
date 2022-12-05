# Operaciones de Infraestructura como código (Terraform)


## Tener instalado Terraform en local
Verificar con: `terraform --version`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/Terraform-local.png?raw=true)

## Clonar el repositorio para tener los archivos.
[Link del repositorio](https://github.com/Eduardoscar/Ed-myAPI-microservicio-a)

## Despliegue en Docker

1. Nos movemos a la carpeta donde se encuentra nuestro archivo de terraform `proyecto-tf.tf`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/movernos_carpeta.png?raw=true)

2. Inicializamos la carpeta de trabajo con: `terraform init`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/terraform_init.png?raw=true)

3. Validamos los archivos de configuración: `terraform validate`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/terraform_validate.png?raw=true)

4. Le damos formato a nuestros archivos: `terraform fmt`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/terraform_fmt.png?raw=true)

5. Creamos la infrastructura `terraform apply`, con `terraform apply -auto-approve` nos saltamos las confirmaciones manuales (no usar en despliegues productivos)    
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/terraform_apply_1.png?raw=true)  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/terraform_apply_2.png?raw=true)

6. Imagen en docker creada  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/image.png?raw=true)

7. Contenedor creado  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/contenedor.png?raw=true)

8. Petición a `localhost:5000`  para obtener respuesta del la app con Flask  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/respuesta.png?raw=true)

9. Ver logs del la app  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/logs.png?raw=true)

10. Destruimos la infraestructura creada: `terraform destroy`, con `terraform destroy -auto-approve`  nos saltamos las confirmaciones manuales (no usar en despliegues productivos)    
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/terraform_destroy_1.png?raw=true)  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/terraform_destroy_2.png?raw=true)  

## Despliegue en AWS

1. Inicializamos la carpeta donde se encuentra nuestro archivo de terraform `proyecto-tf-aws.tf`  con: `terraform init`   
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_terraform_init.png?raw=true)

2. Validamos los archivos de configuración: `terraform validate`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_terraform_validate.png?raw=true)

3. Le damos formato a nuestros archivos: `terraform fmt`  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_terraform_fmt.png?raw=true)

4. Creamos la infrastructura `terraform apply`, con `terraform apply -auto-approve` nos saltamos las confirmaciones manuales (no usar en despliegues productivos)    
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_terraform_apply_1.png?raw=true)  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_terraform_apply_2.png?raw=true)

5. Se levanta la instancia , la subnet y vpc en aws  
![Instancia](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_instancia.png?raw=true)  
![Subnet](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_subred.png?raw=true)  
![vpc](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_vpc.png?raw=true)  

6. Destruimos la infraestructura creada: `terraform destroy`, con `terraform destroy -auto-approve`  nos saltamos las confirmaciones manuales (no usar en despliegues productivos)    
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_terraform_destroy_1.png?raw=true)  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_terraform_destroy_2.png?raw=true)

7. Vemos que cierran todas la infraestructura creada.  
![Alt text](https://raw.githubusercontent.com/Eduardoscar/HSBC-BEDU/main/Postwork_Sesion_6/assets/aws_instancia.png?raw=true)

---
## ✅ Checklist

[X] a. Tener instalado Terraform en local  
[X] b. Identificar los comandos básicos para el despliegue y destrucción de ambientes  
[X] c. Crear nuestro primer despliegue  
[X] d. Crear un despliegue con un servidor en la nube de tu elección.
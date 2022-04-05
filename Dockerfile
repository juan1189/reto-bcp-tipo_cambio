#De la imagen que partimos
FROM openjdk:11-jre-slim

#Directorio de trabajo
WORKDIR /app

#Copiamos el uber-jar en el directorio de trabajo
COPY target/cambio-tipo-moneda-back.jar /app

#Exponemos el puerto 8090
EXPOSE 8090

#Comando que se ejecutará una vez ejecutemos el contendor
ENTRYPOINT ["java","-jar","cambio-tipo-moneda-back.jar"]
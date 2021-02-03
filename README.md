# API_Mutantes_Examen_Final_Programacion2

Proyecto final para programación 2 realizando una API con el controlador, servicio y dto. Exceptuando el repositorio y la base de datos.
Desarrollado en IntelliJ IDEA Community Edition 2020.3.1.

Instrucciones de como ejecutar la API Mutantes:

1- En Intellij IDEA Community Edition deberemos tener instalado el plugin Spring Assistant

2- Con git hacemos: un git clone https://github.com/GastonR22/API_Mutantes_Examen_Final_Programacion2 (Para descargar el repositorio en nuestro workspace)

3- Ejecutamos MutantesAppliaction con el boton derecho y Run 'MutantesAppli....Main()' o desde el boton de Run arriba en Intelij Idea

4- Una vez este corriendo nuestra App debemos abrir postman y hacer un NEW REQUEST de un POST con el Path: http://localhost:9002/api/v1/mutantes/mutant/

5-Debemos ir al BODY de nuestra REQUEST , poner RAW y elegir el tipo de envio de datos como JSON

6- Luego debemos introducir un objeto JSON en el BODY que tenga un ARRAY de String  con la !---- CLAVE: "dna" ----! y el VALOR: [NxN] el arreglo debe ser NxN (4x4,5x5,6x6,etc) tener por ejemplo 5 elementos String, con 5 Carateres en cada String (como sale en el ejemplo del .pdf enviador para realizar el examen) Las letras de los Strings solo pueden ser: (A,T,C,G) para que funcione correctamente. Ejemplo:

Envio al POST de un MUTANTE: 

{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

Envio al POST de un NO MUTANTE: 

{
 "dna": ["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]
}

7- .En el caso de que en el POST se envie un MUTANTE devolvera una respuesta al BODY como TRUE con un STATUS 200 OK
   .En el caso de que en el POST se envie un NO MUTANTE devolvera una respuesta al BODY como FALSE con un STATUS 403 FORBIDDEN


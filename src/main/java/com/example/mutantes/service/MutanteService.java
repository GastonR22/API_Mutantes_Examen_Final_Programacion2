package com.example.mutantes.service;

import com.example.mutantes.dto.MutanteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MutanteService {

    @Transactional
    public ResponseEntity<?> isMutant(MutanteDto dna) {
        String[] adn = dna.getDna();

        char[][] matrizMutante2 = convertirMatriz(adn);
        int contadorHorizontal2 = verificacionHorizontal(matrizMutante2);
        int contadorVertical2 = verificacionVertical(matrizMutante2);
        int contadorOblicuoI = verficacionOblicuaIzquierda(matrizMutante2);
        int contadorOblicuoD = verificacionOblicuadDerecha(matrizMutante2);
        int sumaContadores = contadorHorizontal2 + contadorVertical2 + contadorOblicuoI + contadorOblicuoD;

        //Devuelvo ResponseEntity para poder realizar toda la logica unicamente desde el service
        if (sumaContadores >= 2) {

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }

    }

    public char[][] convertirMatriz(String[] arrayCadena) {
        int contador = 0;
        char[] aux;
        char[][] matrizMutante = new char[arrayCadena.length][arrayCadena.length];

        //Convertimos el array de String a una Matriz de caracteres
        for (int i = 0; i < arrayCadena.length; i++) {
            //Utilizamos el metodo toUpperCase para asegurarnos que en caso de que se ingresen dentro del String datos desiguales como por ejemplo "AAattGa" los algoritmos no fallen
            aux = arrayCadena[i].toUpperCase().toCharArray();

            for (char j : aux) {
                matrizMutante[i][contador] = j;
                contador++;
            }

            contador = 0;
        }

        return matrizMutante;
    }

    public int verificacionHorizontal(char[][] matrizMutante2) {
        int contadorAuxiliar = 0;
        int contadorHorizontal = 0;

        for (int i = 0; i < matrizMutante2.length; i++) {
            contadorAuxiliar = 0;
            for (int j = 1; j < matrizMutante2.length; j++) {

                //Empezamos a comparar desde la posicion 1 de la matriz j en vez de la 0 para evitar un arrayOutOfBounds
                if (matrizMutante2[i][j - 1] == matrizMutante2[i][j]) {
                    contadorAuxiliar++;

                } else {

                    contadorAuxiliar = 0;
                }

                //Validamos que hayan 4 coincidencias (Solo necesitamos 3 contadores y no cuatro porque valida de 2 posciones en 2 posiciones, la primera y la siguiente, asi sucesivamente)
                if (contadorAuxiliar == 3) {
                    contadorHorizontal++;
                    contadorAuxiliar = 0;
                }

            }

        }
        return contadorHorizontal;

    }

    public int verificacionVertical(char[][] matrizMutante2) {
        int contadorAuxiliar = 0;
        int contadorVertical = 0;

        for (int i = 0; i < matrizMutante2.length; i++) {
            contadorAuxiliar = 0;
            for (int j = 1; j < matrizMutante2.length; j++) {

                /* .Usamos el mismo algoritmo que en la comparacion horizontal pero cambiando la posicion de J e I para que recorra el array de forma vertical y no horizontal como normalmente lo haria
                    .Empezamos a comparar desde la posicion 1 de la matriz j en vez de la 0 para evitar un arrayOutOfBounds*/
                if (matrizMutante2[j - 1][i] == matrizMutante2[j][i]) {
                    contadorAuxiliar++;

                } else {

                    contadorAuxiliar = 0;
                }

                //Validamos que hayan 4 coincidencias (Solo necesitamos 3 contadores y no cuatro porque valida de 2 posciones en 2 posiciones, la primera y la siguiente, asi sucesivamente)
                if (contadorAuxiliar == 3) {
                    contadorVertical++;
                    contadorAuxiliar = 0;
                }

            }

        }
        return contadorVertical;
    }

    public int verficacionOblicuaIzquierda(char[][] matrizMutante2) {

        //Cantidad de diagonales que tiene la matriz
        int cantidadDiagonales = (matrizMutante2.length * 2) - 1;
        int contadorDiagonalI = 0;
        String[] arrayDiagonalesI = new String[cantidadDiagonales];

        //Para evitar que en la suma de caracteres de las diagonales nos ingrese null, inicializamos el arreglo como vacio
        for (int i = 0; i < arrayDiagonalesI.length; i++) {
            arrayDiagonalesI[i] = "";
        }

        for (int i = 0; i < matrizMutante2.length; i++) {
            for (int j = 0; j < matrizMutante2.length; j++) {

                /*Cada indice i+j de las diagonales sumadas de izquierda a derecha da como resultado siempre el mismo numero 0,1,2,3 etc
                  Asignamos una concatenacion e igualdad al array de las diagonales cuando la suma de i+j sea = al indice k de la diagonal  */
                for (int k = 0; k < cantidadDiagonales; k++) {

                    if (i + j == k) {

                        arrayDiagonalesI[k] += matrizMutante2[i][j];

                    }

                }

            }
        }

        for (String i : arrayDiagonalesI) {
            if (i.contains("AAAA") || i.contains("TTTT") || i.contains("CCCC") || i.contains("GGGG")) {
                contadorDiagonalI++;
            }
        }
        return contadorDiagonalI;
    }

    private int verificacionOblicuadDerecha(char[][] matrizMutante2) {
        int cantidadDiagonales = (matrizMutante2.length * 2) - 1;
        int contador = 0;
        int contadorDiagonalD = 0;
        String[] arrayDiagonalesD = new String[cantidadDiagonales];

        //Para evitar que en la suma de caracteres de las diagonales nos ingrese null, inicializamos el arreglo como vacio
        for (int i = 0; i < arrayDiagonalesD.length; i++) {
            arrayDiagonalesD[i] = "";
        }

        /*.Para que la matriz comience contando desde la ultima fila (Ya que es mas comodo para contar las diagonales)
           .Es necesario restarle 1 a la matrizMutante2.length para evitar un arrayOutOfBounds ya que estamos empezando desde la ultima fila
           .Fue necesario hacer que: i > -1 porque no entraba en el 0 de la matriz
           .La matriz empieza desde el abajo hacia arriba, de izquierda a derecha
           .La matriz se lee desde abajo hacia arriba
           .El algoritmo funciona tomando las diagonales y concatenando los resultados desde abajo a la derecha hacia arriba empezando
            por la dioagonal 0,1,2... hasta llegar a la ultima.Entonces la diagonal 0 es igual al indice i:5 j:0, la diagonal 1 es igual al indice i:5 j:1 + i:4,j:0
            y asi sucesivamente hasta terminar de verificar la matriz (todo esto usando el ingreso de datos de ejemplo en el ejercicio)
            "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]) Si el ingreso es distinto, los indices de concatenacion cambian en el algoritmo*/
        for (int i = (matrizMutante2.length - 1); i > -1; i--) {
            for (int j = 0; j < matrizMutante2.length; j++) {

                arrayDiagonalesD[j + contador] += matrizMutante2[i][j];
            }
            contador++;
        }

        for (String string : arrayDiagonalesD) {
            if (string.contains("AAAA") || string.contains("TTTT") || string.contains("CCCC") || string.contains("GGGG")) {
                contadorDiagonalD++;
            }
        }
        return contadorDiagonalD;
    }

}


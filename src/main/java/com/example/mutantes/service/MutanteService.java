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

        for (int i = 0; i < arrayCadena.length; i++) {

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

                if (matrizMutante2[i][j - 1] == matrizMutante2[i][j]) {
                    contadorAuxiliar++;

                } else {

                    contadorAuxiliar = 0;
                }

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

                if (matrizMutante2[j - 1][i] == matrizMutante2[j][i]) {
                    contadorAuxiliar++;

                } else {

                    contadorAuxiliar = 0;
                }

                if (contadorAuxiliar == 3) {
                    contadorVertical++;
                    contadorAuxiliar = 0;
                }

            }

        }
        return contadorVertical;
    }

    public int verficacionOblicuaIzquierda(char[][] matrizMutante2) {

        int cantidadDiagonales = (matrizMutante2.length * 2) - 1;
        int contadorDiagonalI = 0;
        String[] arrayDiagonalesI = new String[cantidadDiagonales];

        for (int i = 0; i < arrayDiagonalesI.length; i++) {
            arrayDiagonalesI[i] = "";
        }

        for (int i = 0; i < matrizMutante2.length; i++) {
            for (int j = 0; j < matrizMutante2.length; j++) {

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

        for (int i = 0; i < arrayDiagonalesD.length; i++) {
            arrayDiagonalesD[i] = "";
        }

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


package org.example;

import java.nio.file.StandardWatchEventKinds;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {



        Queue<Integer> refugiados = new LinkedList<>();

        refugiados.add(0);
        refugiados.add(1);
        refugiados.add(0);
        refugiados.add(0);
        refugiados.add(1);
        refugiados.add(1);

        Stack<Integer> raciones = new Stack<>();

        raciones.push(1);
        raciones.push(0);
        raciones.push(0);
        raciones.push(1);
        raciones.push(1);
        raciones.push(1);

        int cantidadRefugiados = refugiados.size();

        boolean bloqueo = Detector(refugiados, raciones);

        int sinComer = refugiados.size();
        int comieron = cantidadRefugiados - sinComer;

        System.out.println("Refugiados que comieron: " + comieron);
        System.out.println("Refugiados que quedaron sin comer: " + sinComer);
        System.out.println("¿Hay bloqueo?: " + bloqueo);


    }
    public static boolean Detector(Queue<Integer> refugiados, Stack<Integer> raciones){
        int refugiado;
        int racion;
        int comieron = 0;
        int rechazos = 0;
        int cantidadRefugiados = refugiados.size();

        while (!refugiados.isEmpty() && !raciones.isEmpty()) {
            refugiado = refugiados.poll();
            racion = raciones.peek();
            if (refugiado == racion) {
                raciones.pop();
                comieron++;
                rechazos = 0;
            } else {
                refugiados.add(refugiado);
                rechazos++;
            }
            if (rechazos >= cantidadRefugiados) {
                return true;
            }
        }
        return false ;
    }


}



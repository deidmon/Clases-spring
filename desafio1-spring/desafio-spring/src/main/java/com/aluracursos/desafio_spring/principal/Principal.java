package com.aluracursos.desafio_spring.principal;

import com.aluracursos.desafio_spring.Model.Datos;
import com.aluracursos.desafio_spring.Model.DatosLibros;
import com.aluracursos.desafio_spring.Services.ConsumoAPI;
import com.aluracursos.desafio_spring.Services.ConvierteDatos;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    public void muestraElMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        //Top 10 libros más descargados
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);

        //Búsqueda de libros por nombre
        System.out.println("Ingresa el nombre del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado");
        }


    }
}
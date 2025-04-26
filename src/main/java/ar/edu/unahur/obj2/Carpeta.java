package ar.edu.unahur.obj2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Carpeta implements IElemento {
    private List<IElemento> elementos = new ArrayList<>();
    private String nombre;

    public Carpeta(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(IElemento elemento) {
        elementos.add(elemento);
    }

    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public Integer tamanio() {
        return elementos.stream().mapToInt(elemento -> elemento.tamanio()).sum();
    }

    @Override
    public void mostrar(Integer identacion) {
        System.out.println(" ".repeat(identacion) + "└ " + nombre + " (" + this.tamanio() + "-Bytes)");
        elementos.stream().forEach(e -> e.mostrar(identacion + 3));
    }

    @Override
    public IElemento archivoMasPesado() {
        return elementos.stream()
                .map(elemento -> elemento.archivoMasPesado())
                .max(
                        Comparator.comparingInt(elemento -> elemento.tamanio()))
                .orElseThrow();

    }
}

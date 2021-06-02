package mx.com.gm.peliculas.vista;

import java.util.Scanner;
import mx.com.gm.peliculas.servicio.CatalogoPeliculasImpl;
import mx.com.gm.peliculas.servicio.ICatalogoPeliculas;

public class CatalogoPeliculasVista {
    public static void main(String[] args) {
        
        var opcion = -1;
        var scanner=new Scanner(System.in);
        ICatalogoPeliculas catalogo=new CatalogoPeliculasImpl();
        
        
        while(opcion!=0){
            System.out.println("Elige una opcion: \n" 
            + "1. Iniciar catalogo peliculas\n"
            + "2. Agregar pelicula\n"
            + "3. Listar peliculas\n"
            + "4. Buscar pelicula\n"
            + "0. Salir");
            
            opcion = Integer.parseInt(scanner.nextLine());
            
            
            switch(opcion){
                
                case 1:
                    System.out.println("");
                    System.out.println("--------------------------");
                    catalogo.iniciarCatalogo();
                    System.out.println("--------------------------");
                    System.out.println("");
                    break;
                    
                case 2:
                    System.out.println("");
                    System.out.println("----------------------------------");    
                    System.out.println("Introduce el nombre de la pelicula, por favors:");
                    var nombre= scanner.nextLine();
                    catalogo.agregarPelicula(nombre);
                    System.out.println("-----------------------------------");
                    break;
                    
                case 3:
                    System.out.println("");
                    System.out.println("------------Lista Peliculas--------------------");
                    catalogo.listarPelicula();
                    System.out.println("------------------------------------");
                    System.out.println("");
                    break;
                    
                case 4:
                    System.out.println("");
                    System.out.println("------------Buscar Pelicula--------------------");
                    System.out.println("Introduce la pelicula a buscar, por favor:");
                    var buscar = scanner.nextLine();
                    catalogo.buscarPelicula(buscar);
                    System.out.println("-----------------------------------");
                    System.out.println("");
                    break;
                    
                case 0:
                    System.out.println("Hasta Luego!!");
                    break;
                
                default:
                    System.out.println("--------------------------------");
                    System.out.println("Opcion Invalida");
                    System.out.println("--------------------------------");
                    break;
                                        
            }
            
        }
    }
    
    
}

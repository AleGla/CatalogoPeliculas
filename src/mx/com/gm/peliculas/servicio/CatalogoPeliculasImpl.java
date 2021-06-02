package mx.com.gm.peliculas.servicio;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.gm.peliculas.datos.AccesoDatosImpl;
import mx.com.gm.peliculas.datos.IAccesoDatos;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas{

    private final IAccesoDatos datos;

    public CatalogoPeliculasImpl() {
        this.datos=new AccesoDatosImpl();
    }
       
    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula =new Pelicula(nombrePelicula);
        boolean anexar=false;
        try {
            anexar= datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datoss");
            ex.printStackTrace();
        }        
    }

    @Override
    public void listarPelicula() {
        try{
            var peliculas=this.datos.listar(NOMBRE_RECURSO);
            for(var pelicula: peliculas){
                System.out.println("Pelicula = " + pelicula);
            }            
        }catch(AccesoDatosEx ex){
            System.out.println("Error de acceso datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String informacion = "No se ha encontrado la pelicula en el catalogo";
        try {
            informacion=this.datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso datos");
            ex.printStackTrace();
        }
        System.out.println(informacion);        
    }

    @Override
    public void iniciarCatalogo() {
        try {
            if(this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }else{
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar catalogo");
            ex.printStackTrace(System.out);
        }
    }
    
    
    
}

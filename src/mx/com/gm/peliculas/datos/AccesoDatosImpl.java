package mx.com.gm.peliculas.datos;


import java.io.*;
import java.util.*;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class AccesoDatosImpl implements IAccesoDatos{

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo= new File(nombreArchivo);
       return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo=new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            BufferedReader entrada= new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while(linea!=null){
                Pelicula pelicula= new Pelicula(linea);
                peliculas.add(pelicula);
                linea=entrada.readLine();                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage());
        } catch (IOException ex) {
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage());
        }
        
        return peliculas;
        
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        File archivo=new File(nombreRecurso);
        try {
            PrintWriter salida=new PrintWriter(new FileWriter(archivo,anexar));
            salida.println(pelicula);
            salida.close();
            System.out.println("Se ha agregado la pelicula al catalogo: " + pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir peliculas: " + ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        File archivo=new File(nombreRecurso);
        String informacion="No se encuentra la pelicula en el catalogo";
        try {
            BufferedReader lectura=new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = lectura.readLine();
            int indice=1;
            while(linea!=null){
            if(buscar!=null && buscar.equalsIgnoreCase(linea)){
                informacion="La pelicula " + linea + ", Se encuentra en la posicion " + indice + " del catalogo";
                break;
            }
              linea = lectura.readLine();
              indice++;
            }
            lectura.close();
        } catch (FileNotFoundException ex) {
            throw new LecturaDatosEx("Excepcion al buscar pelicula: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: " + ex.getMessage());
        }
        return informacion;
        
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        File archivo=new File(nombreRecurso);
        try {
            PrintWriter salida=new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace();
              throw new AccesoDatosEx("Excepcion al crear pelicula: " + ex.getMessage());
        }
        
    }

    @Override
    public void borrar(String nombreRecurso) throws AccesoDatosEx {
        File archivo=new File(nombreRecurso);
        if(archivo.exists())
        archivo.delete();
        System.out.println("Se ha borrado el archivo");
    }
    
}

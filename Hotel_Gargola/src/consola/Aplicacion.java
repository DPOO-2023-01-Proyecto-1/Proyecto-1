package consola;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.Hotel;

public class Aplicacion 
{
		public void ejecutarAplicacion() throws FileNotFoundException, UnsupportedEncodingException
		{
		ejecutarCargarDatos();
		}
		public void ejecutarCargarDatos()
		{
			
	    	try
	        {
	            Hotel.cargarInfoHotel();
	        }
	        catch (FileNotFoundException e)
			{
				System.out.println("ERROR: el archivo no se encontró.");
			}
	        catch (IOException e)
			{
				System.out.println("Aqui pasó algo malo");
				System.out.println(e.getMessage());
			}
		}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
		Aplicacion consola = new Aplicacion();
        consola.ejecutarAplicacion();
    }
}

package modelo;

import java.util.Map;
import java.util.Random;

public class Recepcionista extends Usuario {

	public Recepcionista(String login, String password, String userType) {
		super(login, password, userType);
		// TODO Auto-generated constructor stub
	}
	
	public Integer generarIdParaReservasNuevas( Map<Integer, Reserva> mapaReservas)
	{	//para generar el id de las reservas toca que entre el mapa para que se checkee que el id no esté creado ya
		Random random = new Random();
		Boolean checkearId = true;
		int idGenerado = 0; #cambios bonitos 
		while (checkearId = true)
		{
			idGenerado = random.nextInt(1000);// aqui genera el id
			if (mapaReservas.get(idGenerado)!= null)
			{
				idGenerado = random.nextInt(1000);
			}
			else
			{
				checkearId = false;
			}
		}
		
		return idGenerado;
	}

}

package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Nequi;
import modelo.Tarjeta;
import modelo.MercadoPago;
import modelo.Paypal;

public class ClaseDinamica {

    public static List<Object> crearObjetos(List<String> nombresClases) {
        List<Object> objetos = new ArrayList<>();
        String paquete = "modelo";

        for (String nombreClase : nombresClases) {
            try {
                Class<?> clase = Class.forName(paquete + "." + nombreClase);
                Constructor<?>[] constructores = clase.getConstructors();

                // Obtener el primer constructor disponible (puedes personalizar la lógica para seleccionar un constructor específico)
                Constructor<?> constructor = constructores[0];
                Parameter[] parametros = constructor.getParameters();
                Object[] argumentos = new Object[parametros.length];

                for (int i = 0; i < parametros.length; i++) {
                    String nombreParametro = obtenerNombreParametro(parametros[i]);
                    String mensaje = "Ingrese el valor para el parámetro \"" + nombreParametro + "\" de tipo " + parametros[i].getType().getSimpleName() + " en la clase " + nombreClase;
                    String valorParametro = JOptionPane.showInputDialog(mensaje);
                    argumentos[i] = convertirValor(valorParametro, parametros[i].getType());
                }

                Object objeto = constructor.newInstance(argumentos);
                objetos.add(objeto);

                JOptionPane.showMessageDialog(null, "Se ha creado un objeto de la clase " + nombreClase + " correctamente.");
            } catch (ClassNotFoundException e) {
                System.out.println("Clase no encontrada: " + nombreClase);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                System.out.println("Error al crear objeto de la clase: " + nombreClase);
            }
        }

        return objetos;
    }

    private static String obtenerNombreParametro(Parameter parametro) {
        String nombreParametroCompleto = parametro.toString();
        int indiceEspacio = nombreParametroCompleto.lastIndexOf(' ');
        if (indiceEspacio != -1) {
            return nombreParametroCompleto.substring(indiceEspacio + 1);
        }
        return "";
    }

    private static Object convertirValor(String valor, Class<?> tipo) {
        if (tipo == int.class || tipo == Integer.class) {
            return Integer.parseInt(valor);
        } else if (tipo == double.class || tipo == Double.class) {
            return Double.parseDouble(valor);
        } else if (tipo == boolean.class || tipo == Boolean.class) {
            return Boolean.parseBoolean(valor);
        } else {
            // Puedes agregar conversiones adicionales según los tipos de parámetros que esperes
            return valor;
        }
    }
    
}
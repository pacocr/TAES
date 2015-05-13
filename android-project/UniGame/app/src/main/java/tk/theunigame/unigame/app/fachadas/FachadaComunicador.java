package tk.theunigame.unigame.app.fachadas;

import java.util.ArrayList;
import java.util.Objects;

import juego.taes.domainmodel.Model.Cliente.Asignatura;
import juego.taes.domainmodel.Model.Cliente.BDPreguntas;
import juego.taes.domainmodel.Model.Cliente.Carrera;
import juego.taes.domainmodel.Model.Cliente.Pregunta;
import juego.taes.domainmodel.Model.Cliente.Universidad;
import juego.taes.domainmodel.Model.Cliente.Usuario;
import tk.theunigame.unigame.app.logica_juego.juego.Estadisticas;
import tk.theunigame.unigame.app.presentacion.util.Comunicador;
import tk.theunigame.unigame.app.presentacion.util.IActivityListaDatos;

/**
 * Created by John on 25/04/2015.
 *
 * El envío de objetos se realizará dentro de un vector de Objetc.
 */
public class FachadaComunicador {

    public void ComunicarDestino(Class<?> datos){
        Comunicador.setObject(new Object[]{datos});
    }

    //Prepara una universidad para ser recibida por otra activity
    public void ComunicarUniversidad(Universidad u, Class<?> datos){
        Comunicador.setObject(new Object[]{u, datos});
    }

    //Prepara universidad y carrera para ser recibida por otra activity
    public void ComunicarUniversidadCarrera(Universidad u, Carrera c, Class<?> datos){
        Comunicador.setObject(new Object[]{u, c, datos});
    }

    public void ComunicarBDPreguntas(ArrayList<BDPreguntas> bdPreguntas, Class<?> destino) {
        Comunicador.setObject(new Object[]{bdPreguntas, destino});
    }

    //Prepara una universidad, carrera y asignatura para ser recibida por otra activity
    public void ComunicarUniversidadCarreraAsignatura(Universidad u, Carrera c, Asignatura a, Class<?> datos){
        Comunicador.setObject(new Object[]{u, c, a, datos});
    }

    //Prepara una universidad, carrera y asignaturas para ser recibida por otra activity
    public void ComunicarUniversidadCarreraAsignaturas(Universidad u, Carrera c, ArrayList<Asignatura> a, Class<?> datos){
        Comunicador.setObject(new Object[]{u, c, a, datos});
    }


    //Recupera una universidad enviada en la posición 0
    public Universidad RecibirUniversidadPosicion0(){
        return (Universidad)((Object[])Comunicador.getObject())[0];
    }

    //Recupera una pregunta enviada en la posición 0
    public Pregunta RecibirPregunta(){
        return (Pregunta)((Object[])Comunicador.getObject())[0];
    }

    //Recupera una carrera enviada en la posición 1
    public Carrera RecibirCarreraPosicion1(){
        return (Carrera)((Object[])Comunicador.getObject())[1];
    }

    //Recupera una asignatura enviada en la posición 2
    public Asignatura RecibirAsignaturaPosicion2(){
        return (Asignatura)((Object[])Comunicador.getObject())[2];
    }

    //Recupera un aray de asignaturas enviada en la posición 2
    public ArrayList<Asignatura> RecibirAsignaturasPosicion2(){
        return (ArrayList<Asignatura>)((Object[])Comunicador.getObject())[2];
    }

    //Recupera un aray de asignaturas enviada en la posición 2
    public Class<?> RecibirDestinoPosicionFinal(){
        return (Class<?>)((Object[])Comunicador.getObject())[((Object[])Comunicador.getObject()).length-1];
    }


    public ArrayList<BDPreguntas> RecibirBDPreguntasPosicion0() {
        return (ArrayList<BDPreguntas>)((Object[])Comunicador.getObject())[0];
    }

    public void ComunicarEstadisticas(Estadisticas estadisticas, Class<?> destino) {
        Comunicador.setObject(new Object[]{estadisticas, destino});
    }

    public Estadisticas RecibirEstadisticasPosicion0() {
        return (Estadisticas)((Object[])Comunicador.getObject())[0];

    }

    public void volverAtras()
    {
        Comunicador.getObjectAnterior();
    }


    public void ComunicarPregunta(Pregunta pregunta, Class<?> destino) {
        Comunicador.setObject(new Object[]{pregunta, destino});
    }

    public Pregunta RecibirPreguntaPosicion0()
    {
        return (Pregunta)((Object[])Comunicador.getObject())[0];
    }


    public Usuario RecibirUsuario()
    {
        return Comunicador.getUsuario();
    }

    public void ComunicarUsuario(Usuario usuario)
    {
        Comunicador.setUsuario(usuario);
    }

}

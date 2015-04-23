package tk.theunigame.unigame.app.logica_juego.bolsaPreguntas;
import com.j256.ormlite.dao.ForeignCollection;
import java.util.ArrayList;
import java.util.List;
import juego.taes.domainmodel.Model.Cliente.BDPreguntas;
import juego.taes.domainmodel.Model.Cliente.Pregunta;
import juego.taes.domainmodel.Repository.BDPreguntasRepository;
import juego.taes.domainmodel.Repository.PreguntaRepository;

public class BolsaPregunta {

    private List<Pregunta> preguntas_eliminadas;
    private List<Pregunta> preguntas_modificadas;
    private List<Pregunta> preguntas_creadas;
    private BDPreguntas pBDPreguntas;
    private ForeignCollection<Pregunta> pPreguntas;

    //Repositorys
    //private BDPreguntasRepository bDRepository = new BDPreguntasRepository();

    /**********************************************************************************************/
    private static BolsaPregunta sInstance = null;//Objeto session

    private BolsaPregunta()//Privado para el objeto session
    {
        preguntas_creadas = new ArrayList<Pregunta>();
        preguntas_eliminadas = new ArrayList<Pregunta>();
        preguntas_modificadas = new ArrayList<Pregunta>();
    }

    public static void init() {
        sInstance = new BolsaPregunta();
    }

    public static BolsaPregunta getInstance() {
        return sInstance;
    }
    /**********************************************************************************************/
    public void SetBDPreguntas(BDPreguntas bd)
    {
        pBDPreguntas = bd;
        pPreguntas = bd.getPreguntas();
        preguntas_creadas = new ArrayList<Pregunta>();
        preguntas_eliminadas = new ArrayList<Pregunta>();
        preguntas_modificadas = new ArrayList<Pregunta>();
    }
    public BDPreguntas getBDPreguntas()
    {
        return pBDPreguntas;
    }

    public void InsertarPregunta(Pregunta pregunta)
    {
        pregunta.setBdPreguntas(pBDPreguntas);
        pregunta.setId((preguntas_creadas.size() + 1)*(-1));//Setteo su id a un valor negativo
        preguntas_creadas.add(pregunta);
    }

    public void EliminarPregunta(Pregunta pregunta)
    {
        if(pregunta.getBdPreguntas().getId() == pBDPreguntas.getId()) {
            if (preguntas_creadas.contains(pregunta)) {
                int pos = (pregunta.getId() * (-1)) - 1;
                preguntas_creadas.remove(pregunta);

                if (pos < preguntas_creadas.size()) { //Si la posicion borrada no es la última
                    //Cambiar todos los Ids
                    for (int i = pos; i < preguntas_creadas.size(); i++) {
                        preguntas_creadas.get(i).setId((i + 1) * (-1));//Setteo su id a un valor negativo
                    }
                }
            } else if (pPreguntas.contains(pregunta))
                preguntas_eliminadas.add(pregunta);
        }
    }

    public void ModificarPreguntaInsertada(Pregunta pregunta)
    {
        if(pregunta.getBdPreguntas().getId() == pBDPreguntas.getId()) {
            if (preguntas_creadas.contains(pregunta)) {
                preguntas_creadas.remove(pregunta);
                preguntas_creadas.add(pregunta);
            } else if (pPreguntas.contains(pregunta))
                preguntas_modificadas.add(pregunta);
        }
    }

    public void RegistrarCambios()
    {}
}

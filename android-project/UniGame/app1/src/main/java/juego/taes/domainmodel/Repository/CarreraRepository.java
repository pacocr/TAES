package juego.taes.domainmodel.Repository;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import juego.taes.domainmodel.Data.Dao.CarreraUniversidadDao;
import juego.taes.domainmodel.Data.Dao.ICarreraDao;
import juego.taes.domainmodel.Data.Dao.ICarreraUniversidadDao;
import juego.taes.domainmodel.Data.Dao.IUniversidadDao;
import juego.taes.domainmodel.Data.DatabaseHelper;
import juego.taes.domainmodel.Data.DatabaseManager;
import juego.taes.domainmodel.Data.Example.DatabaseHelperExample;
import juego.taes.domainmodel.Model.Cliente.Carrera;
import juego.taes.domainmodel.Model.Cliente.CarreraUniversidad;
import juego.taes.domainmodel.Model.Cliente.Universidad;

/**
 * Created by Pedro on 21/04/2015.
 */
public class CarreraRepository {

    private DatabaseHelper db;
    private ICarreraDao dao;
    private ICarreraUniversidadDao carreraUniversidadDao;

    public CarreraRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            dao = db.getICarreraDao();
            carreraUniversidadDao = db.getICarreraUniversidadDao();

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(Carrera carrera)
    {
        try {
            return dao.create(carrera);
        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            e.printStackTrace();
        }
        return 0;
    }

    public int update(Carrera carrera)
    {
        try {
            return dao.update(carrera);
        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Carrera carrera)
    {
        try {
            return dao.delete(carrera);
        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            e.printStackTrace();
        }
        return 0;
    }

    public int refresh(Carrera carrera)
    {
        try {
            return dao.refresh(carrera);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Carrera> getAll()
    {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            e.printStackTrace();
        }
        return null;
    }

    public Carrera getById(int id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            e.printStackTrace();
        }
        return null;
    }

    public List<Carrera> getByUniversidad(int id)
    {
        try {

            QueryBuilder<CarreraUniversidad,Integer> builder = carreraUniversidadDao.queryBuilder();
            builder.where().eq(CarreraUniversidad.UNIVERSIDAD,id);

            return dao.queryBuilder().join(builder).query();

        } catch (SQLException e) {
            //TODO GESTION DE ERRORES
            e.printStackTrace();
        }
        return null;
    }
}

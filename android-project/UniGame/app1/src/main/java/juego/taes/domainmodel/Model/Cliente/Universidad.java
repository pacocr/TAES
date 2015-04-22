package juego.taes.domainmodel.Model.Cliente;

import android.text.TextUtils;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import juego.taes.domainmodel.Utilities.AeSimpleSHA1;

/**
 * Created by felix on 22-4-2015.
 */

@DatabaseTable(tableName = "universidad")
public class Universidad {

    //Nombres de las columnas
    public static final String ID="_id";
    public static final String NOMBRE="nombre";
    public static final String SIGLAS="siglas";

    //Relaciones
    public static final String CARRERAS="fk_carreras";
    public static final String BDS="fk_bds";

    //Atributos de la base de datos
    @DatabaseField(columnName=ID, id = true, useGetSet = true )
    private int id;

    @DatabaseField(columnName= NOMBRE, canBeNull = false, useGetSet = true)
    private String nombre;

    @DatabaseField(columnName= SIGLAS, canBeNull = false, useGetSet = true)
    private String siglas;

    //Relaciones
    @ForeignCollectionField(eager=false, foreignFieldName = "carreras", columnName = CARRERAS)
    private ForeignCollection<Carrera> carreras;
    
    @ForeignCollectionField(eager=false, foreignFieldName = "bds", columnName = BDS)
    private ForeignCollection<BDPreguntas> bds;

    public Universidad() {
        // ORMLite needs a no-arg constructor
    }

    public Universidad(String nombre, String siglas) {
        this.nombre=nombre;
        this.siglas=siglas;
    }

    //Setters y getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public ForeignCollection<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ForeignCollection<Carrera> carreras) {
        this.carreras = carreras;
    }

    public ForeignCollection<BDPreguntas> getBds() {
        return bds;
    }

    public void setBds(ForeignCollection<BDPreguntas> bds) {
        this.bds = bds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Universidad)) return false;

        Universidad that = (Universidad) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
package tk.theunigame.unigame.app.presentacion.controlador.impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.sql.SQLException;

import juego.taes.domainmodel.Data.DatabaseManager;
import juego.taes.domainmodel.Model.Cliente.Usuario;
import tk.theunigame.unigame.R;
import tk.theunigame.unigame.app.fachadas.FachadaComunicador;
import tk.theunigame.unigame.app.fachadas.FachadaUsuario;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see tk.theunigame.unigame.util.SystemUiHider
 */
public class LoginMain extends Activity {

    private Button btn_login;
    private Button btn_cancel;
    private Button btn_registrar;
    private EditText et_usuario, et_password;
    private FachadaUsuario fachadaUsuario;
    private FachadaComunicador comunicador;
    private RadioButton rb_offline;

    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);

        DatabaseManager manager = new DatabaseManager();
        manager.getHelper(this).getWritableDatabase();

        comunicador = new FachadaComunicador();
        c = this;

        fachadaUsuario = new FachadaUsuario();
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);

        et_usuario = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        rb_offline = (RadioButton) findViewById(R.id.modoOFFLINE);



        rb_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(et_password.getVisibility() == View.VISIBLE){ //Modo online -> offline
                   et_password.setEnabled(false);
                   et_password.setVisibility(View.INVISIBLE);
                   rb_offline.setChecked(true);

               }
                else //Modo offline -> online
               {
                   et_password.setEnabled(true);
                   et_password.setVisibility(View.VISIBLE);
                   rb_offline.setChecked(false);
               }

            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = ProgressDialog.show(LoginMain.this, "",
                        "Iniciando sesión...", true);
                String user = et_usuario.getText().toString();
                String pass = et_password.getText().toString();

                Usuario usu = null;
                try {
                    usu = fachadaUsuario.loguear(c, user, pass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(usu == null)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);

                    builder.setMessage("El nombre de usuario o la contraseña no son correctos").
                            setTitle("Inicio de sesión denegado").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.create().show();
                }else
                {
                    comunicador.ComunicarUsuario(usu);
                    Intent intent = new Intent(LoginMain.this, MainActivity.class);
                    startActivity(intent);
                }
                dialog.cancel();
            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = ProgressDialog.show(LoginMain.this, "",
                        "Preparando formulario de registro...", true);
                Intent intent = new Intent(LoginMain.this, Registry.class);
                startActivity(intent);
                dialog.cancel();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {


        super.onBackPressed();
    }

}

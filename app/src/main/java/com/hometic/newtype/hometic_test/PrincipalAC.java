package com.hometic.newtype.hometic_test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalAC extends AppCompatActivity implements View.OnClickListener {

    private Button btnEncender, btnApagar;
    private TextView txtEnvio, txtRespuesta, txtColor;
    private Toolbar toolbar;
    private Conexion app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_ac);

        btnEncender = (Button) findViewById(R.id.btnEncender);
        btnApagar = (Button) findViewById(R.id.btnApagar);
        txtEnvio = (TextView) findViewById(R.id.txtEnvio);
        txtRespuesta = (TextView) findViewById(R.id.txtRespuesta);
        txtColor = (TextView) findViewById(R.id.txtColor);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        btnEncender.setOnClickListener(this);
        btnApagar.setOnClickListener(this);

        /*a este nivel ya puedo emitir un mensaje de conexión*/
        app = new Conexion(PrincipalAC.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal_ac, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEncender:
                app.enviarMensaje("SensorPIR","true");
                modificarTxtMensaje("Mensaje enviado: true");
                break;
            case R.id.btnApagar:
                modificarTxtMensaje("Mensaje enviado: false");
                app.enviarMensaje("SensorPIR","false");
                break;
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

        }

    }

    @Override
    public void onDestroy(){
        app.cerrarConexion();
        super.onDestroy();
    }

    public void modificarTxtMensaje(String m){txtEnvio.setText(m);}
    public void modificarTxtRespuesta(String txt){ txtRespuesta.setText(txt);}
    public void modificarColorTxtColor(int color){
        txtColor.setBackgroundColor(color);
    }
}

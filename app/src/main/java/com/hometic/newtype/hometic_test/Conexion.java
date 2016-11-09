package com.hometic.newtype.hometic_test;

import android.graphics.Color;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Camilo on 09/11/2016.
 */

public class Conexion {

    private Socket ws;
    private PrincipalAC principal;

    public Conexion(PrincipalAC pr) {
        try {
            this.principal = pr;
            this.ws = IO.socket("http://192.168.0.8:8000");
            this.ws.on("status", estado());
            this.ws.open();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion(){
        ws.close();
    }

    /*Eventos Emmit*/
    public void enviarMensaje(String evento, String msg){
        this.ws.emit(evento,msg);
    }
    /*Eventos Listener*/
    public Emitter.Listener estado(){
        Emitter.Listener estado = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                principal.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String)args[0];
                        principal.modificarTxtRespuesta("Respuesta del servidor: "+ data);
                        if(data.equalsIgnoreCase("green")){
                            principal.modificarColorTxtColor(Color.GREEN);
                        }else{
                            principal.modificarColorTxtColor(Color.RED);
                        }
                    }
                });
                //new Tarea(principal).execute((String)args[0]);
            }
        };
        return estado;
    }
}

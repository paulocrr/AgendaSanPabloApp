package com.example.paulo.agendasanpablo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by paulo on 7/12/2017.
 */

public class wsAccess {
    String URL = "http://localhost/wsAgendaSanPablo/wsAgendaSanpablo.php";
    String NAMESPACE = "urn:wsAgendaSanPablo";
    public Vector<String> validarUsuario(String usuario, String pass){
        String SOAP_ACTION="wsAgendaSanPablo#validarUsuario";
        String METHOD = "validarUsuario";
        Vector<String> respuesta = new Vector<String>();
        try{
            SoapObject request = new SoapObject(NAMESPACE,METHOD);
            request.addProperty("usuario",usuario);
            request.addProperty("pass",pass);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION,envelope);
            SoapObject response = (SoapObject) envelope.bodyIn;
            Vector<?>responseVector = (Vector<?>)response.getProperty(0);
            respuesta = (Vector<String>) responseVector;
            transportSE.getServiceConnection().disconnect();
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}

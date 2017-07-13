package com.example.paulo.agendasanpablo;

import android.content.SharedPreferences;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.SimpleTimeZone;
import java.util.Vector;

/**
 * Created by paulo on 7/12/2017.
 */

public class wsAccess {
    String URL = "http://104.236.233.108/wsAgendaSanPablo/wsAgendaSanpablo.php";
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
            Log.d("Mi Debug", "validarUsuario: "+respuesta.size());
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
    public Vector<String> registrarUsuario(String nombre,String apellido,String correo,String usuario,String password){
        String SOAP_ACTION="wsAgendaSanPablo#registrarUsuario";
        String METHOD = "registrarUsuario";
        Vector<String> respuesta = new Vector<String>();
        try{
            SoapObject request = new SoapObject(NAMESPACE,METHOD);
            request.addProperty("nombre",nombre);
            request.addProperty("apellido",apellido);
            request.addProperty("correo",correo);
            request.addProperty("usuario",usuario);
            request.addProperty("password",password);
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
    public String listadoProfesores(){
        String SOAP_ACTION="wsAgendaSanPablo#listarProfesores";
        String METHOD = "listarProfesores";
        String respuesta = "0";
        try{
            SoapObject request = new SoapObject(NAMESPACE,METHOD);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION,envelope);
            respuesta = envelope.getResponse().toString();
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
    public String listadoCriterios(){
        String SOAP_ACTION="wsAgendaSanPablo#getCriterios";
        String METHOD = "getCriterios";
        String respuesta = "0";
        try{
            SoapObject request = new SoapObject(NAMESPACE,METHOD);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION,envelope);
            respuesta = envelope.getResponse().toString();
            transportSE.getServiceConnection().disconnect();
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  respuesta;
    }
    public Vector<String> calificarProfesor(String nota,String id_profesor,String id_criterio){
        String SOAP_ACTION="wsAgendaSanPablo#calificarProfesor";
        String METHOD = "calificarProfesor";
        Vector<String> respuesta = new Vector<String>();
        try{
            SoapObject request = new SoapObject(NAMESPACE,METHOD);
            request.addProperty("nota",nota);
            request.addProperty("id_profesor",id_profesor);
            request.addProperty("id_criterio",id_criterio);
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
    public String getDias(){
        String SOAP_ACTION="wsAgendaSanPablo#getDias";
        String METHOD = "getDias";
        String respuesta = "0";
        try{
            SoapObject request = new SoapObject(NAMESPACE,METHOD);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION,envelope);
            respuesta = envelope.getResponse().toString();
            transportSE.getServiceConnection().disconnect();
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  respuesta;
    }
    public String getHorariosDia(String idDia, String idUsuario){
        String SOAP_ACTION="wsAgendaSanPablo#getHorarioDia";
        String METHOD = "getHorarioDia";
        String respuesta = "0";
        try{
            SoapObject request = new SoapObject(NAMESPACE,METHOD);
            request.addProperty("idDia",idDia);
            request.addProperty("idUsuario",idUsuario);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION,envelope);
            respuesta = envelope.getResponse().toString();
            transportSE.getServiceConnection().disconnect();
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  respuesta;
    }
}

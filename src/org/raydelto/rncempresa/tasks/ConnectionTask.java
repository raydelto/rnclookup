package org.raydelto.rncempresa.tasks;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.raydelto.rncempresa.util.Util;

import android.os.AsyncTask;
import android.widget.Toast;

public class ConnectionTask extends AsyncTask<String, String, String> {
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://gobdata.cloudapp.net/RncManagementService.svc";
	private static final String SOAP_ACTION = "http://tempuri.org/IRncService/GetEmpresa";
	private static final String METHOD_NAME = "GetEmpresa";
	private SoapObject result;

	@Override
	protected void onPreExecute() {
		Util.searching = true;
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			request.addProperty("busqueda", params[0]);
			envelope.setOutputSoapObject(request);
			envelope.setAddAdornments(false);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			result = (SoapObject) envelope.getResponse();
			publishProgress("EXITO", result.getProperty(0).toString());
		} catch (Exception e) {
			publishProgress("ERROR", e.getMessage());
			e.printStackTrace();
			return "ERROR";
		}

		return "SUCCESS";
	}

	@Override
	protected void onProgressUpdate(String... values) {
		if ("EXITO".equals(values[0])) {
			Util.mainActivity.showResponse(result);
		} else if ("ERROR".equals(values[0])) {
			Util.mainActivity.resetView();
			Toast.makeText(Util.mainActivity, "Error al buscar: " + values[1], Toast.LENGTH_LONG).show();
		}

	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Util.searching = false;
	}

}

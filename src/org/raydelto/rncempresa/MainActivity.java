package org.raydelto.rncempresa;

import org.ksoap2.serialization.SoapObject;
import org.raydelto.rncempresa.entities.RncData;
import org.raydelto.rncempresa.tasks.ConnectionTask;
import org.raydelto.rncempresa.ui.RncAdapter;
import org.raydelto.rncempresa.util.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView lblBuscando;
	private EditText txtRnc;
	private Button btnBuscar;
	private ProgressBar pbSearching;
	private ListView listRnc;

	 
	@Override
	public void onBackPressed() {
		exit();
	}
	
	private void init(){
		listRnc = (ListView) findViewById(R.id.listRnc);
		listRnc.setAdapter(RncAdapter.getInstance());
		lblBuscando = (TextView) findViewById(R.id.lblBuscando);
		txtRnc = (EditText) findViewById(R.id.txtRnc);
		pbSearching = (ProgressBar) findViewById(R.id.pbSearching);
		btnBuscar = (Button) findViewById(R.id.btnBuscar);
		
		btnBuscar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				search();
				
			}
		});
		resetView();
		setConnectionListener();
	}
	
	private void exit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String message = "¿Confirma que desea salir?";
		builder.setMessage(message).setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				finish();

			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void setConnectionListener() {
		BroadcastReceiver receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				if (!wifi.isWifiEnabled()) {
					Toast.makeText(MainActivity.this,"No hay acceso a Internet", Toast.LENGTH_SHORT).show();
				}
			}
		};
		registerReceiver(receiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
	}

	
	public void search(){
		if(!Util.checkInternetConnection()){
			Toast.makeText(this, "No hay acceso a Internet", Toast.LENGTH_SHORT).show();
		}
		new ConnectionTask().execute(txtRnc.getText().toString());
		listRnc.setVisibility(View.GONE);
		lblBuscando.setVisibility(View.VISIBLE);
		pbSearching.setVisibility(View.VISIBLE);
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Util.mainActivity = this;
		init();
		if(Util.result != null && !Util.searching){
			showResponse(Util.result);
		}

	}
	
	public void showResponse(SoapObject result){
		Util.result  = result;
		listRnc.setVisibility(View.VISIBLE);
		lblBuscando.setVisibility(View.GONE);
		pbSearching.setVisibility(View.GONE);
		String actividadPrincipal = "";
		String direccionCalle  = "";
		String direccionNumero = "";
		String direccionSector  = "";
		String estado  = "";
		String fechaConstitucion  = "";
		String nombreComercial  = "";
		String numeroRnc = "";
		String razonSocial = "";
		String regimenPago = "";
		String telefono = "";

		RncAdapter.getInstance().clear();
		for(int i = 0 ; i < result.getPropertyCount(); i++){
			String data = result.getProperty(i).toString();
			data = data.replaceAll("anyType" , "");
			data = data.replaceAll("\\{" , "");
			data = data.replaceAll("; \\}" , "");
			String[] nodes = data.split(";");
			
			//TODO: escribir este código de manera más elegante
			for(String node : nodes){
				String[] detail = node.split("=");
				System.out.println("Key:" + detail[0] +", value:" + detail[1]);
				detail[0] = detail[0].trim();
				if("ActividadPrincipal".equals(detail[0])){
					actividadPrincipal =  detail[1];
				}else if("DireccionCalle".equals(detail[0])){
					direccionCalle =  detail[1];
				}else if("DireccionNumero".equals(detail[0])){
					direccionNumero =  detail[1];
				}else if("DireccionSector".equals(detail[0])){
					direccionSector  =  detail[1];
				}else if("Estado".equals(detail[0])){
					estado  =  detail[1];
				}else if("FechaConstitucion".equals(detail[0])){
					fechaConstitucion =  detail[1];
				}else if("NombreComercial".equals(detail[0])){
					nombreComercial =  detail[1];
				}else if("NumeroRnc".equals(detail[0])){
					numeroRnc =  detail[1];
				}else if("RazonSocial".equals(detail[0])){
					razonSocial =  detail[1];
				}else if("RegimenPago".equals(detail[0])){
					regimenPago =  detail[1];
				}else if("Telefono".equals(detail[0])){
					telefono =  detail[1];
				}
			}
			RncData rnc = new RncData(actividadPrincipal, direccionCalle, direccionNumero, direccionSector, estado, fechaConstitucion, nombreComercial, numeroRnc, razonSocial, regimenPago, telefono);
			RncAdapter.getInstance().add(rnc);
		}
	}
	public void resetView(){
		listRnc.setVisibility(View.GONE);
		lblBuscando.setVisibility(View.GONE);
		pbSearching.setVisibility(View.GONE);
	}
}
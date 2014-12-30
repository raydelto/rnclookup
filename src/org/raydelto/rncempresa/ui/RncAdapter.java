package org.raydelto.rncempresa.ui;

import java.util.ArrayList;

import org.raydelto.rncempresa.R;
import org.raydelto.rncempresa.entities.RncData;
import org.raydelto.rncempresa.util.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RncAdapter extends BaseAdapter {


	private LayoutInflater inflater = null;
	private static RncAdapter instance;
	private ArrayList<RncData> rncList;


	public static synchronized RncAdapter getInstance() {
		if (instance == null) {
			instance = new RncAdapter();
		}
		return instance;
	}

	// applying Singleton design pattern.
	private RncAdapter() {
		inflater = (LayoutInflater) Util.mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rncList = new ArrayList<RncData>();

	}
	
	public void add(RncData rnc){
		rncList.add(rnc);
		notifyDataSetChanged();
	}
	
	public void clear(){
		rncList.clear();
		notifyDataSetChanged();
	}


	public void update() {
		notifyDataSetChanged();
	}


	@Override
	public View getView(int position, View row, ViewGroup parent) {
		row = inflater.inflate(R.layout.rnc_row,null);
		RncData rnc = rncList.get(position);
		TextView txtRazonSocial = (TextView) row.findViewById(R.id.txtRazonSocial);
		TextView txtRnc = (TextView) row.findViewById(R.id.txtRnc);
		TextView txtFechaConstitucion = (TextView) row.findViewById(R.id.txtFechaConstitucion);
		TextView txtActividadPrincipal = (TextView) row.findViewById(R.id.txtActividadPrincipal);
		TextView txtTelefono = (TextView) row.findViewById(R.id.txtTelefono);
		TextView txtDireccionSector = (TextView) row.findViewById(R.id.txtDireccionSector);
		TextView txtDireccionCalle = (TextView) row.findViewById(R.id.txtDireccionCalle);
		TextView txtDireccionNumero = (TextView) row.findViewById(R.id.txtDireccionNumero);
		
		txtRazonSocial.setText(rnc.getRazonSocial());
		txtRnc.setText(rnc.getNumeroRnc());
		txtFechaConstitucion.setText(rnc.getFechaConstitucion());
		txtActividadPrincipal.setText(rnc.getActividadPrincipal());
		txtTelefono.setText(rnc.getTelefono());
		txtDireccionSector.setText(rnc.getDireccionSector());
		txtDireccionCalle.setText(rnc.getDireccionCalle());
		txtDireccionNumero.setText(rnc.getDireccionNumero());
		
		
		return row;
	}

	@Override
	public int getCount() {
		return rncList.size();
	}

	@Override
	public Object getItem(int position) {
		return rncList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}

package com.byappsoft.sap.launcher;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.byappsoft.sap.R;
import com.byappsoft.sap.main.Sap_act_main;
import com.byappsoft.sap.utils.Sap_Func;

import java.util.Locale;

public class Sap_act_main_launcher{

	private static AlertDialog mDialog;

	@SuppressLint("InflateParams")
	public static void initsapStart(final Context context, final String sAgkey, final boolean NOTIBA, final boolean URLSEARCH) {

		try {

			if (Build.VERSION.SDK_INT > 14) {

				Sap_Func.initkeypreference(context);

				if (NOTIBA == false) {
					Sap_act_main.initSapStartapp(context, sAgkey, false, URLSEARCH);
					return;
				}

				if (mDialog!= null && mDialog.isShowing()) {
					mDialog.dismiss();
					mDialog = null;
				}

				if (Sap_Func.isNotibarPopState(context) == false) {

					AlertDialog.Builder 	builder 	= new AlertDialog.Builder(context);
					LayoutInflater 			inflater 	= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View 					layout 		= inflater.inflate(R.layout.lay_sap_act_pop, null);
					ImageView 				bgImg 		= layout.findViewById(R.id.sap_alert_popup_bg);

					if (Locale.getDefault().getLanguage().equals("ko")) {
						bgImg.setImageDrawable(context.getResources().getDrawable(R.drawable.res_sap_notiba_img_ko));
					} else if (Locale.getDefault().getLanguage().equals("zh")) {
						bgImg.setImageDrawable(context.getResources().getDrawable(R.drawable.res_sap_notiba_img_cn));
					} else if (Locale.getDefault().getLanguage().equals("vi")) {
						bgImg.setImageDrawable(context.getResources().getDrawable(R.drawable.res_sap_notiba_img_vi));
					} else if (Locale.getDefault().getLanguage().equals("ja")) {
						bgImg.setImageDrawable(context.getResources().getDrawable(R.drawable.res_sap_notiba_img_ja));
					} else {
						bgImg.setImageDrawable(context.getResources().getDrawable(R.drawable.res_sap_notiba_img_en));
					}

					layout.findViewById(R.id.sap_res_btn_ok).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							Sap_Func.setNotibarPopState(context, true);
							Sap_act_main.initSapStartapp(context, sAgkey, true, URLSEARCH);
							mDialog.dismiss();
						}
					});

					layout.findViewById(R.id.sap_res_btn_can).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							Sap_Func.setNotibarPopState(context, true);
							Sap_act_main.initSapStartapp(context, sAgkey, false, URLSEARCH);
							mDialog.dismiss();
						}
					});

					builder.setView(layout);
					mDialog = builder.create();
					mDialog.show();

				} else {
					Sap_act_main.initSapStartapp(context, sAgkey, Sap_Func.isNotiBarState(context), URLSEARCH);
				}

			}
		} catch (Exception e) {
		}

	}



}

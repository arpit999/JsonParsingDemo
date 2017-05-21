package com.production.hometech.jsonparsingdemo.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.production.hometech.jsonparsingdemo.R;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OkhttpFragment extends Fragment {

    View rootView;
    private TextView tv_response;
    private ProgressDialog progressDialog;

    public OkhttpFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_okhttp, container, false);

        tv_response = (TextView) rootView.findViewById(R.id.tv_okhttp);

//        new GetOkhttpData().execute();

        new PostOkhttpData().execute();

        return rootView;
    }

    public class GetOkhttpData extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait....");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(Void... voids) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://pratikbutani.x10.mx/json_data.json")
                    .build();

            String responString = null;

            try {
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                responString = response.body().string();
                System.out.println(responString);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return responString;
        }


        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (response == null) {

                Toast.makeText(getActivity(), "Response not found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            } else {

                Toast.makeText(getActivity(), "Response Success", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                tv_response.setText(response);
            }

        }
    }

    public class PostOkhttpData extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait....");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(Void... voids) {

            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                    .add("api_key", "7e8f60e325cd06e164799af1e317d7a7")
                    .build();

            Request request = new Request.Builder()
                    .url("http://api.themoviedb.org/3/movie/top_rated")
                    .post(requestBody)
                    .build();

            String responseString = null;
            try {
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code : " + response);
                responseString = response.body().string();
                System.out.println(responseString);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (response == null) {

                Toast.makeText(getActivity(), "Response not found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            } else {

                Toast.makeText(getActivity(), "Response Success", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                tv_response.setText(response);
            }


        }
    }

}

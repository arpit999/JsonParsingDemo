package com.production.hometech.jsonparsingdemo.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.production.hometech.jsonparsingdemo.R;
import com.production.hometech.jsonparsingdemo.model.AndroidHive;
import com.production.hometech.jsonparsingdemo.model.Movie;
import com.production.hometech.jsonparsingdemo.model.Pratik;
import com.production.hometech.jsonparsingdemo.utils.ApiClient;
import com.production.hometech.jsonparsingdemo.utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.production.hometech.jsonparsingdemo.utils.ApiClient.BASE_URL;
import static com.production.hometech.jsonparsingdemo.utils.ApiClient.BASE_URL_ANDROIDHIVE;
import static com.production.hometech.jsonparsingdemo.utils.ApiClient.BASE_URL_PRATIKBUTANI;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetrofitFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = RetrofitFragment.class.getSimpleName();
    String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";
    private ProgressDialog progressDialog;
    LinearLayout lin_buttons;
    Button bt_imdb, bt_androidhive, bt_pratik;
    TextView tv_retrofit;
    ApiInterface apiInterface;

    public RetrofitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_retrofit, container, false);

        tv_retrofit = (TextView) rootView.findViewById(R.id.tv_retrofit);

        bt_imdb = (Button) rootView.findViewById(R.id.bt_imdb);
        bt_androidhive = (Button) rootView.findViewById(R.id.bt_androidhive);
        bt_pratik = (Button) rootView.findViewById(R.id.bt_paratikbutani);

        bt_imdb.setOnClickListener(this);
        bt_androidhive.setOnClickListener(this);
        bt_pratik.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_imdb:

//                Toast.makeText(getActivity(), "IMDB click", Toast.LENGTH_SHORT).show();
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Please wait....");
                progressDialog.setIndeterminate(true);
                progressDialog.show();

                /**
                 * Note that if you want different base URl then you have to pass it manually rather then ApiClient class
                 */

//                apiInterface = ApiClient.getClient(BASE_URL).create(ApiInterface.class);

                apiInterface = new Retrofit.Builder()
                        .baseUrl("http://api.themoviedb.org/3/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ApiInterface.class);

                Call<Movie> call = apiInterface.getTopRatedMovies(API_KEY);

                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {

                        List<Movie.ResultsBean> movies = response.body().getResults();

                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            tv_retrofit.setText("Page : " + response.body().getPage() + "\n Toatal Results : " + response.body().getTotal_results() + "\n Total Pages : " + response.body().getTotal_pages() + "\n\n\n\n OBJECT 3 \n" + "Poster Path : " + movies.get(3).getPoster_path() + "\n Release Date : " + movies.get(3).getRelease_date() + "\n Gener IDS : " + movies.get(3).getGenre_ids() + "\n Title : " + movies.get(3).getTitle());

                        } else {
                            Toast.makeText(getActivity(), "Status code : " + response.message(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        System.out.println(t);
                        progressDialog.dismiss();
                    }
                });

                break;

            case R.id.bt_androidhive:

                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Please wait....");
                progressDialog.setIndeterminate(true);
                progressDialog.show();

//                NOTE : when json start from array then you can pass direct list in to interface and Call method so retrofit directly cover to list and give you a list.

//                apiInterface = ApiClient.getClient(BASE_URL_ANDROIDHIVE).create(ApiInterface.class);

                apiInterface = new Retrofit.Builder()
                        .baseUrl(BASE_URL_ANDROIDHIVE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ApiInterface.class);

                final Call<List<AndroidHive>> hiveCall = apiInterface.getAllMovies();

                hiveCall.enqueue(new Callback<List<AndroidHive>>() {
                    @Override
                    public void onResponse(Call<List<AndroidHive>> call, Response<List<AndroidHive>> response) {

                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            List<AndroidHive> hiveList = response.body();

                            tv_retrofit.setText("Title : " + hiveList.get(3).getTitle() + "\n Image : " + hiveList.get(3).getImage() + "\n Rating : " + hiveList.get(3).getRating() + " \n Release Year : " + hiveList.get(3).getReleaseYear() + "\n  Gener : " + hiveList.get(3).getGenre());

                        } else {
                            Toast.makeText(getActivity(), "Response Failed Code : " + response.message(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<AndroidHive>> call, Throwable t) {
                        System.out.println(t.getStackTrace().toString());
                        progressDialog.dismiss();
                    }
                });


                break;

            case R.id.bt_paratikbutani:

                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Please Wait....");
                progressDialog.setCancelable(false);
                progressDialog.show();

//                apiInterface = ApiClient.getClient(BASE_URL_PRATIKBUTANI).create(ApiInterface.class);

                apiInterface = new Retrofit.Builder()
                        .baseUrl(BASE_URL_PRATIKBUTANI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ApiInterface.class);

                Call<Pratik> pratikCall = apiInterface.getAllContacts();

                pratikCall.enqueue(new Callback<Pratik>() {
                    @Override
                    public void onResponse(Call<Pratik> call, Response<Pratik> response) {

                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            List<Pratik.ContactsBean> beanList = response.body().getContacts();

                            Pratik.ContactsBean bean = beanList.get(3);
                            tv_retrofit.setText("");
                            tv_retrofit.setText("ID : " + bean.getId() + "\n NAME : " + bean.getName() + "\n EMIAL : " + bean.getEmail() + "\n ADDRESS : " + bean.getAddress() + "\n GENDER : " + bean.getGender() + "\n PROFILE PIC : " + bean.getProfile_pic() + "\n\n PHONE " + "\n MOBILE : " + bean.getPhone().getMobile() + "\n HOME : " + bean.getPhone().getHome() + "\n OFFICE : " + bean.getPhone().getOffice());

                        } else {
                            Toast.makeText(getActivity(), "Response failed code : " + response.message(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                    }

                    @Override
                    public void onFailure(Call<Pratik> call, Throwable t) {
                        System.out.println(t.getStackTrace().toString());
                        Toast.makeText(getActivity(), "Response failed", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

                break;
        }
    }


}

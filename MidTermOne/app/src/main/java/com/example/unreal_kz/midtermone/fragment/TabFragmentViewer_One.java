package com.example.unreal_kz.midtermone.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unreal_kz.midtermone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Unreal_KZ on 07.10.2015.
 */
public class TabFragmentViewer_One extends Fragment {

    private static final int LAYOUT = R.layout.fragment_one;
    private static final int FRAGMENT_VIEW_DELAY = 1000;
    private ListView listView;
    public static String[] K_G_Names;
    public static String[] K_G_Drectors_Name;
    public static String[] K_G_Phone_Numbers;

    private static final String LOG_TAG = "my_LOG";

    private static HttpURLConnection urlConnection;
    private static BufferedReader reader;
    private static String resultJSON;
    private static JSONObject jsonObject;

    public static TabFragmentViewer_One getFragment() {
        Bundle args = new Bundle();
        TabFragmentViewer_One tabFragmentViewerOne = new TabFragmentViewer_One();
        tabFragmentViewerOne.setArguments(args);

        return tabFragmentViewerOne;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //String [] inforToListView = {"qwe1","qwe2","qwe3","qwe4","qwe5","qwe6","qwe7","qwe8"};

        View view = inflater.inflate(LAYOUT, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        ListAdapter theAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, K_G_Names);
        listView.setAdapter(theAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String myStr = "You have clicked: " + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(view.getContext(), myStr, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public static class JasonTaskManager extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://data.egov.kz/api/v2/kindergartens");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                resultJSON = buffer.toString();
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultJSON;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Log.d(LOG_TAG, s);

            try {
                JSONArray jsonArray = new JSONArray(s);
                K_G_Names = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    K_G_Names[i] = jsonObject.getString("Name");
                    Log.d(LOG_TAG, K_G_Names[i]);
                    /*JSONInfo.K_G_Phone_Numbers[i] = jsonObject.getString("PhoneNumber");
                    JSONInfo.K_G_Drectors_Name[i] = jsonObject.getString("DirectorFullName");*/
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

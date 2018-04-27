package com.johannlau.popularmovies.utilities;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getName();

    private final static String apiKey = "fe88ffc8466992c2f7765aefe90f0388";
    private final static String baseURL = " http://image.tmdb.org/t/p/";
    private final static String phoneSize = "w185";

    private final static String popularURL = "http://api.themoviedb.org/3/movie/popular?api_key=";
    private final static String topratedURL = "http://api.themoviedb.org/3/movie/top_rated?api_key=";
    //Sample URL https://api.themoviedb.org/3/movie/76341?api_key={api_key}
    public static URL buildURL(boolean choice){
        String baseURL;
        if(choice)  baseURL = popularURL;
        else{ baseURL = topratedURL; }

        Uri builtUri = Uri.parse(baseURL + apiKey).buildUpon()
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        Log.v(TAG,"Built URL " +url);
        return url;
    }

    public static String getURLResponse(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if(hasInput) {
                return scanner.next();
            }
            else {
                return null;
            }
        }
        finally {
            urlConnection.disconnect();
        }
    }

}
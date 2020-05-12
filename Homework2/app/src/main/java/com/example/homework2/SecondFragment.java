package com.example.homework2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.homework2.datamodel.City;
import com.example.homework2.datamodel.Weather;
import com.example.homework2.datamodel.WeatherType;
import com.example.homework2.network.CityResultsObserver;
import com.example.homework2.network.ForecastForACityResultsObserver;
import com.example.homework2.network.IpmaWeatherClient;
import com.example.homework2.network.WeatherTypesResultsObserver;

import java.util.HashMap;
import java.util.List;

public class SecondFragment extends Fragment {

    TextView mTitleTv, mDescriptionTv;
    private TextView feedback;
    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private HashMap<Integer, WeatherType> weatherDescriptions;

    public static SecondFragment newInstance() {

        return new SecondFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View v = inflater.inflate(R.layout.fragment_second, container, false);

        mTitleTv = v.findViewById(R.id.textView3);
        feedback = v.findViewById(R.id.feedback);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            mTitleTv.setText(bundle.getString("title"));
            callWeatherForecastForACityStep1(bundle.getString("title"));
        }
        else {
            mTitleTv.setText("Error");
        }

        return v;
    }

    private void callWeatherForecastForACityStep1(String city) {

        feedback.append("\nGetting forecast for: " + city); feedback.append("\n");

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                SecondFragment.this.weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2( city);
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append("Failed to get weather conditions!");
            }
        });

    }

    private void callWeatherForecastForACityStep2(String city) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                SecondFragment.this.cities = citiesCollection;
                City cityFound = cities.get(city);
                if( null != cityFound) {
                    int locationId = cityFound.getGlobalIdLocal();
                    callWeatherForecastForACityStep3(locationId);
                } else {
                    feedback.append("unknown city: " + city);
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                feedback.append("Failed to get cities list!");
            }
        });
    }

    private void callWeatherForecastForACityStep3(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                for (Weather day : forecast) {
                    feedback.append(day.toString());
                    feedback.append("\t");
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append( "Failed to get forecast for 5 days");
            }
        });

    }
}

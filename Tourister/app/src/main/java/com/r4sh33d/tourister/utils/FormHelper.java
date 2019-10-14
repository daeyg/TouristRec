package com.r4sh33d.tourister.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.r4sh33d.tourister.R;
import com.r4sh33d.tourister.network.KeyTitleLovModel;
import com.r4sh33d.tourister.network.StateWithCityLOVModel;
import com.r4sh33d.tourister.network.StatesWithCityListWrapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FormHelper {
    private Context context;

    public FormHelper(Context context) {
        this.context = context;
    }


    public void populateStatesAndCitySpinnerWithData(Spinner statesSpinner, Spinner citiesSpinner) {
        StateWithCityLOVModel hintStateWithCityLOVModel = new StateWithCityLOVModel();
        hintStateWithCityLOVModel.name = "state";
        hintStateWithCityLOVModel.key = "city";
        ArrayList<StateWithCityLOVModel> statesWithCitiesArrayList = new ArrayList<>(getStatesWithCities());
        statesWithCitiesArrayList.add(0, hintStateWithCityLOVModel);
        CustomSpinnerAdapter<StateWithCityLOVModel> spinnerAdapter = new CustomSpinnerAdapter<>(context,
                android.R.layout.simple_spinner_dropdown_item, statesWithCitiesArrayList);
        statesSpinner.setAdapter(spinnerAdapter);

        statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    citiesSpinner.setEnabled(true);
                    ArrayList<KeyTitleLovModel> citiesInSelectedState =
                            new ArrayList<>(((StateWithCityLOVModel) adapterView.getSelectedItem()).cities);
                    String hintString = "city";
                    KeyTitleLovModel hintKeyTitleLovModel = new KeyTitleLovModel("0", hintString, hintString);
                    citiesInSelectedState.add(0, hintKeyTitleLovModel);
                    CustomSpinnerAdapter<KeyTitleLovModel> spinnerAdapter = new CustomSpinnerAdapter<>(context,
                            android.R.layout.simple_spinner_dropdown_item, citiesInSelectedState);
                    citiesSpinner.setAdapter(spinnerAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public ArrayList<StateWithCityLOVModel> getStatesWithCities() {
        InputStream raw = context.getResources().openRawResource(R.raw.states_with_cities);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Type type = new TypeToken<StatesWithCityListWrapper>() {
        }.getType();
        StatesWithCityListWrapper statesWithCityListWrapper = new Gson().fromJson(rd, type);
        return statesWithCityListWrapper.statesWithCities;
    }

    public int getItemPositionKeyTitleLovModelList(ArrayList<KeyTitleLovModel> arrayList,
                                                   KeyTitleLovModel keyTitleLovModel) {
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i).key.equalsIgnoreCase(keyTitleLovModel.key)) {
                return i;
            }
        }
        return 0;
    }

    public static int getItemPositionFromStatesWithCityArrayList(ArrayList<StateWithCityLOVModel> arrayList,
                                                                 KeyTitleLovModel keyTitleLovModel) {
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i).key.equalsIgnoreCase(keyTitleLovModel.key)) {
                return i;
            }
        }
        return 0;
    }

    public boolean validateSpinners(Spinner... spinners) {
        for (Spinner spinner : spinners) {
            if (spinner.getSelectedItemPosition() < 1) {
                if (spinner.getSelectedView() != null && spinner.getSelectedView() instanceof TextView) {
                    TextView textView = (TextView) spinner.getSelectedView();
                    textView.setError("Field required");
                }
                return false;
            }
        }
        return true;
    }


    public String getSelectedItemKeyFromSpinner(Spinner spinner) {
        return ((KeyTitleLovModel) spinner.getSelectedItem()).key;
    }

    public String getSelectedItemKeyFromStateSpinner(Spinner stateSpinner) {
        return ((StateWithCityLOVModel) stateSpinner.getSelectedItem()).key;
    }


    public void destroy() {
        context = null;
    }
}

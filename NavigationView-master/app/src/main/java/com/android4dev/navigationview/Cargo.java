package com.android4dev.navigationview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by S. Harsono on 2/3/2016.
 */
public class Cargo extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final View v = inflater.inflate(R.layout.cargo, container, false);


        String [] values = {"Issue Date","Subject","GA Info","Refer"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

       // ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(v.getContext(), android.R.layout.simple_spinner_item, values);
       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
       // spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String selected = parentView.getItemAtPosition(position).toString();
                Context context = parentView.getContext();
                CharSequence text = selected;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

              //  TextView bk = (TextView) v.findViewById(R.id.by);

                switch (position) {
                    case 0:

                        break;
                    case 1:


                        break;
                    case 2:


                        break;
                    default:
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return v;
    }
}

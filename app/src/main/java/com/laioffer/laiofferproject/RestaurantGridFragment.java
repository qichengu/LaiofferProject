package com.laioffer.laiofferproject;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantGridFragment extends Fragment {

    GridView gridView;
    public RestaurantGridFragment() {
        // Required empty public constructor
    }
    OnItemSelectListener mCallback;

    public interface OnItemSelectListener {
        public void onItemSelectedGrid(int position);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnItemSelectListener) context;
        } catch (ClassCastException e) {
            //do something
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_grid, container, false);
        gridView = (GridView) view.findViewById(R.id.restaurant_grid);
        gridView.setAdapter(new RestaurantAdapter(getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int idx = 0; idx < gridView.getChildCount(); idx++){
                    if (i == idx) {
                        gridView.getChildAt(idx).setBackgroundColor(Color.BLUE);
                    } else {
                        gridView.getChildAt(idx).setBackgroundColor(Color.parseColor("#EEEEEE"));
                    }
                }
                mCallback.onItemSelectedGrid(i);
            }
        });

        return view;
    }

    public void onItemSelected(int position){
        for(int i = 0; i < gridView.getChildCount(); i++){
            if (position == i) {
                gridView.getChildAt(i).setBackgroundColor(Color.BLUE);
            } else {
                gridView.getChildAt(i).setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
        }
    }


}

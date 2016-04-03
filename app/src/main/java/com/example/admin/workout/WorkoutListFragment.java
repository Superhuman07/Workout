package com.example.admin.workout;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {


    public WorkoutListFragment() {
        // Required empty public constructor
    }
    static interface WorkoutListener
    {
        void itemClicked(long id);
    }
    private WorkoutListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; ++i)
        {
            names[i] = Workout.workouts[i].getName();
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1, names
        );
        setListAdapter(listAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);

    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.listener = (WorkoutListener)activity;
    }

    @Override
    public void onListItemClick(ListView l,View v, int position, long id)
    {
        if(listener != null)
        {
            listener.itemClicked(id);
        }
    }

}

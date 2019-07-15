package com.example.booknoc;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.booknoc.Fragment.AdvancedSearch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFormulaire.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFormulaire#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFormulaire extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //On déclare les attributs dont on aura besoin pour la suite.
    private EditText date;
    private Button searchButton;
    private Spinner spinner;
    private TextView labalError;

    private OnFragmentInteractionListener mListener;
    DatePickerDialog datePickerDialog;

    public SearchFormulaire() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFormulaire.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFormulaire newInstance(String param1, String param2) {
        SearchFormulaire fragment = new SearchFormulaire();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private void initDatePicker(Context context) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(context, (view, year1, month1, dayOfMonth) -> {
            String format = "dd/MM/yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);

            calendar.set(Calendar.YEAR, year1);
            calendar.set(Calendar.MONTH, month1);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            date.setText(simpleDateFormat.format(calendar.getTime()));
        }, year, month, day);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search_formulaire, container, false);
        initDatePicker(getActivity());
        this.date = view.findViewById(R.id.search_date);
        //On paramètre le Spinner :
        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,CateogoryBestSellerTitle.getInstance().getListCategory());
        spinner.setAdapter(arrayAdapter);
        this.labalError = view.findViewById(R.id.label_error);
        this.searchButton = view.findViewById(R.id.search_chercher);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!date.getText().toString().equals("")) {
                    AdvancedSearch advanced = new AdvancedSearch();
                    advanced.setDate(date.getText().toString());
                    advanced.setCategorie(spinner.getSelectedItem().toString());
                    ((MainActivity) getActivity()).changeFragment(advanced);
                }
                else {
                    labalError.setVisibility(View.VISIBLE);
                }
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

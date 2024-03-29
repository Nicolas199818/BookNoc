package com.example.booknoc.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booknoc.CateogoryBestSellerTitle;
import com.example.booknoc.Data_Application.Book;
import com.example.booknoc.RecyclerView.BookAdapter;
import com.example.booknoc.R;
import com.example.booknoc.Services.NetworkProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter mAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_recent_best_seller, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_recent);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //On creer une liste de test que l'on va passé à la recycler View
        CateogoryBestSellerTitle categoryList = CateogoryBestSellerTitle.getInstance();
        final List<Book> listBooksToAdapter = new ArrayList<>();
        Random r = new Random();
        int index = r.nextInt(categoryList.getListCategory().size() - 1);

        NetworkProvider.getInstance().getBookHistory(new NetworkProvider.Listener<List<Book>>() {
            @Override
            public void onSuccess(List<Book> data) {
                for(final Book book:data){
                    Log.d("FrontDebug",book.getTitle());
                    NetworkProvider.getInstance().getBookGoogleImage(book.getTitle(),new NetworkProvider.Listener<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Log.i("DEBUGFRONTGOOGLE",data);
                            //C'est ici que l'on ajoute les livres
                            book.setImage(data);
                            mAdapter.aditem(book);
                            mAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onError(Throwable t) {
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable t) {

            }
        });
        Log.i("TEST",""+listBooksToAdapter.size());

        List<Book> listBook = new ArrayList<>();


        //Pour chaque
        mAdapter = new BookAdapter(listBook);
        recyclerView.setAdapter(mAdapter);



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

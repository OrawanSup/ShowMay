package khumpong.orawan.showmay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import khumpong.orawan.showmay.R;
import khumpong.orawan.showmay.manager.EbookAdapter;
import khumpong.orawan.showmay.manager.GetAllData;
import khumpong.orawan.showmay.manager.MyConstant;

/**
 * Created by May on 25/8/2560.
 */

public class ReadEbookFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_ebook, container, false);
        return view;
    }   //onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView
        createListView();

    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.livEbook);
        String tag = "28AugV1";
        MyConstant myConstant = new MyConstant();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlEbookString());
            String strJSON = getAllData.get();
            Log.d(tag, "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] nameStrings = new String[jsonArray.length()];
            String[] detailStrings = new String[jsonArray.length()];
            String[] imageStrings = new String[jsonArray.length()];
            String[] pdfStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                detailStrings[i] = jsonObject.getString("Detai;");
                imageStrings[i] = jsonObject.getString("Cover");
                pdfStrings[i] = jsonObject.getString("pdf");

            }   // for

            EbookAdapter ebookAdapter = new EbookAdapter(getActivity(),
                    imageStrings, nameStrings, detailStrings);
            listView.setAdapter(ebookAdapter);

        } catch (Exception e) {
            Log.d(tag, "e createLisView ==>" + e.toString());
        }

    }   //createLisView

}   // Main Class

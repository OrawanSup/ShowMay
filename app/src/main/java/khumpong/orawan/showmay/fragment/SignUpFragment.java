package khumpong.orawan.showmay.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import khumpong.orawan.showmay.MainActivity;
import khumpong.orawan.showmay.R;
import khumpong.orawan.showmay.manager.MyAlert;

/**
 * Created by May on 23/8/2560.
 */

public class SignUpFragment extends Fragment{

    //Explicit
    private String nameString, userString, passwordString, rePasswordString, genderString;
    private boolean genderABoolean = true;  // true ==> Not Choose Gender



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    }   // onCreatview

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //  Create ToolBar
        createToolBar();

        // SignUp Controller
        signUpController();

        //Gender Controller
        genderController();

    }   // onActivityCreate

    private void genderController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragGender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                genderABoolean = false;
                switch (i) {
                    case R.id.ragMale:
                        genderString = "Male";
                        break;
                    case R.id.ragFemale:
                        genderString = "Female";
                        break;
                    default:
                        genderString = "n/a";
                        break;
                }   // switch

            }   //onChecked
        });
    }

    private void signUpController() {
        Button button = getView().findViewById(R.id.btnSignUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initial EditText view
                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);
                EditText rePassEditText = getView().findViewById(R.id.edtRePassword);

                // Get Value Form EditText
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                rePasswordString = rePassEditText.getText().toString().trim();

                // Check Space
                if (nameString.equals("") ||
                        userString.equals("") ||
                        passwordString.equals("") ||
                        rePasswordString.equals("")) {
                    // Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));
                } else if (!passwordString.equals(rePasswordString)) {
                    //Password Not Math
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Password Not Math", "Please Fill Same Password");
                } else if (genderABoolean) {
                    //Not Choose Gender
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Not Choose Gender", "Please Choose Gender");


                } else {
                    uploadValueToServer();
                }



            }   // onClick
        });
    }

    private void uploadValueToServer() {

    }

    private void createToolBar() {
        //SetUp Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolBarSignUp);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(R.string.register);
        ((MainActivity)getActivity()).getSupportActionBar().setSubtitle(getString(R.string.sub_register));


        //setUp Navagation Icon
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .popBackStack();

            }   // onClick
        });
    }
}   // main Class

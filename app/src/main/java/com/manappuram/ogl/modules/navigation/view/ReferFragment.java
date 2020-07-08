package com.manappuram.ogl.modules.navigation.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.databinding.FragmentReferBinding;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReferFragment extends BaseFragment {

    FragmentReferBinding binding;
    static final int PICK_CONTACT=1;


    public ReferFragment() {
        // Required empty public constructor
    }

    public static ReferFragment newInstance() {

        Bundle args = new Bundle();

        ReferFragment fragment = new ReferFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReferBinding.inflate(inflater, container, false);

        mActivity = getActivity();

        binding.selectContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });


        return binding.getRoot();
    }

//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            // Check for the request code, we might be usign multiple startActivityForReslut
//            if (requestCode == 501) {
//
//                Log.d("contact", "here");
//
//                Cursor cursor;
//                try {
//                    String phoneNo = null;
//                    String name = null;
//                    Uri uri = data.getData();
//                    cursor = mActivity.getContentResolver().query(uri, null, null, null, null);
//                    cursor.moveToFirst();
//                    int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//                    phoneNo = cursor.getString(phoneIndex);
//
//                    Log.d("phoneNo", phoneNo);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            Log.e("MainActivity", "Failed to pick contact");
//        }

//
//    }


    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    Cursor c =mActivity. managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {


                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones =mActivity. getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                    null, null);
                            phones.moveToFirst();
                            String cNumber = phones.getString(phones.getColumnIndex("data1"));
                            System.out.println("number is:" + cNumber);

                            binding.edtmobile.setText(cNumber);
                        }
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                        binding.edtCustomerId.setText(name);
                    }
                }
                break;

        }}




    public static void hideKeyboard(Activity activity) {
        View v = activity.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null && v != null;
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
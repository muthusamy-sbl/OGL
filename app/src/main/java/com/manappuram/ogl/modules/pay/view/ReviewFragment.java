package com.manappuram.ogl.modules.pay.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.manappuram.ogl.R;
import com.manappuram.ogl.modules.dashboardoptions.ItemClicked;
import com.manappuram.ogl.modules.dashboardoptions.model.ApplicationFormResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.DetailsModel;
import com.manappuram.ogl.modules.dashboardoptions.view.DetailAdapter;
import com.manappuram.ogl.modules.dashboardoptions.view.LanguageInterface;
import com.manappuram.ogl.modules.dashboardoptions.view.SubmitActivity;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.modules.pay.PaymentViewModel;
import com.manappuram.ogl.modules.pay.model.PayuInputRequest;
import com.manappuram.ogl.modules.pay.model.repledge;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.CustomLoader;
import com.manappuram.ogl.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReviewFragment extends BottomSheetDialogFragment implements LanguageInterface, ItemClicked {

    ArrayList<DetailsModel> detailList = new ArrayList<>();
    PaymentViewModel viewModel;
    DashboardViewModel viewModel1;
    String pledgeNo;
    PayuInputRequest request;
     repledge repledge;
    ApplicationFormResponse applicationFormResponse;
    public ReviewFragment(String pledgeNo, PayuInputRequest request) {
       this.pledgeNo=pledgeNo;
       this.request=request;
    }

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    private CustomLoader customLoader;
    public Activity mActivity;

    public interface OptionListener {
        void optionSelected(String item);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NotNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.fragment_review, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        viewModel=new PaymentViewModel();
        viewModel1=new DashboardViewModel();
        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        System.out.println(pledgeNo);
        viewModel.checkrepldge(pledgeNo,getActivity());
        Context mActivity = getContext();
        RecyclerView recyclerView = contentView.findViewById(R.id.recycler);
        sharedPreferences = getContext().getSharedPreferences("ogl-app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        DetailAdapter adapter = new DetailAdapter( getActivity(), detailList, this, this,"2");
        recyclerView.setAdapter(adapter);



        viewModel.getRepledge().observe(this, response -> {
            System.out.println(response);
            String rId=response.getRequestid();
            updateid(rId);
            repledge=response;
            System.out.println(response.getInvid()+"test"+ pledgeNo+"test13"+ getActivity());
            viewModel1.getApplicationFormDetails(response.getInvid(), pledgeNo,  getActivity());


        });

        viewModel1.getApplicationFormLiveData().observe(this, new Observer<ApplicationFormResponse>() {
            @Override
            public void onChanged(ApplicationFormResponse applicationFormResponse) {

                Log.d("applicationFormResponse", applicationFormResponse.toString());
                String rId=applicationFormResponse.getRequestid();
                updateid(rId);

                detailList.get(0).setRepledge(repledge);
                System.out.println("com"+repledge);
                detailList.get(1).setApplicationFormResponse(applicationFormResponse);
                adapter.notifyDataSetChanged();
                getTerms("EN");
            }
        });

        viewModel1.getTermsLiveData().observe(this, baseResponse -> {
            Utility.cancelProgressbar();
            String rId=baseResponse.getRequestid();
            updateid(rId);
            if (baseResponse.getStatus().equals("111")) {
                Log.d("baseResponse", baseResponse.getResult());
                detailList.get(3).setTerms(baseResponse.getResult());
                adapter.notifyDataSetChanged();
            }
        });

        detailList.add(new DetailsModel("personal", null, ""));
        detailList.add(new DetailsModel("inventory", null, ""));
        detailList.add(new DetailsModel("pledge", null, ""));
        detailList.add(new DetailsModel("terms", null, ""));
        adapter.notifyDataSetChanged();


    }
    @Override
    public void languageSelected(String item) {
        getTerms(item);
    }

    @Override
    public void itemClicked(String type, Object object) {

        if (type.equals("click")) {


            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            intent.putExtra(Constants.PAY_REQUEST, request);
            startActivity(intent);
        }

    }

    private void updateid(String rId) {
        if (!rId.equals("") && !rId.equals("null")) {
            System.out.println(rId);
            UserSession.getInstance().setRequestId(rId);
            editor.putString("requestId", rId);
            editor.apply();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void getTerms(String lang) {
//        Utility.setProgressbar(mActivity);
        viewModel1.getTermsandConditionsDetails(lang,  getActivity());
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);

    }


}

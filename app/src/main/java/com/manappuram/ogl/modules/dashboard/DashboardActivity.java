package com.manappuram.ogl.modules.dashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.databinding.ActivityHomeBinding;
import com.manappuram.ogl.modules.login.LoginFragment;
import com.manappuram.ogl.modules.navigation.model.AccDetailsRequest;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.FragmentUtils;
import com.manappuram.ogl.util.Utility;
import com.manappuram.ogl.util.navigator.Navigator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    boolean doubleBackToExitPressedOnce = false;
    private static final int LANG_CHANGE_REQ_CODE = 101;
    private static final int CHANGE_MPIN_REQ = 102;
    private static final int CHANGE_PASS_REQ = 103;
    private static final int SET_MPIN_REQ = 104;

    DashboardViewModel viewmodel;
    private ActivityHomeBinding binding;
    private Navigator mNavigator;
    List<String> menuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewmodel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = this;

        mNavigator = new Navigator(this);
        setSupportActionBar(binding.toolbar);

        menuList = Arrays.asList(getResources().getStringArray(R.array.nav_menu));
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, R.layout.layout_nav_menu_item, menuList);
//        binding.layoutNav.menuListView.setAdapter(itemsAdapter);
//        itemsAdapter.notifyDataSetChanged();
binding.layoutNav.customerName.setText(UserSession.getInstance().getCustomerName());
binding.layoutNav.customerId.setText(UserSession.getInstance().getCustomerId());
         binding.layoutNav.menuListView.setOnItemClickListener(this);
        int[] flags = new int[]{
                R.drawable.home,
                R.drawable.profile,
                R.drawable.addneet,
                R.drawable.ref_friend,
                R.drawable.payout,
                R.drawable.down,
                R.drawable.cus_feed,
                R.drawable.tran_history,
                R.drawable.ogl_history,
                R.drawable.bookappoinment,
                R.drawable.co_brand,
                R.drawable.setmpin,
                R.drawable.changempin,
                R.drawable.locateus,
                R.drawable.contact,
                R.drawable.aboutus,
                R.drawable.faq,
                R.drawable.setmpin
        };


        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < menuList.size(); i++) {
            Map<String, Object> datum = new HashMap<String, Object>(2);
            datum.put("thumbnail",flags[i]);
            datum.put("name", menuList.get(i));
            data.add(datum);
        }
        binding.layoutNav.menuListView.setAdapter(new SimpleAdapter(this, data, R.layout.layout_nav_menu_item, new String[] {"thumbnail","name"}, new int[] {R.id.image1, R.id.text1}));



        binding.navIcon.setOnClickListener(view -> binding.drawerLayout.openDrawer(GravityCompat.START));

        FragmentUtils.replaceFragment(getSupportFragmentManager(),
                DashBoardFragment.newInstance(), false, R.id.container, false);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d("position", String.valueOf(position));
        binding.drawerLayout.closeDrawer(GravityCompat.START);

        Intent intent = new Intent(mActivity, SideNavActivity.class);
        intent.putExtra("position", String.valueOf(position));
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

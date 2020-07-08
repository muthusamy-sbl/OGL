package com.manappuram.ogl.util;/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;

import io.reactivex.annotations.NonNull;


/**
 * This provides methods to help Activities load their UI.
 */
public class FragmentUtils {

    /**
     * Add Fragment with stack
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, boolean canAddBackStrace, int frameId) {
        if (fragment != null && fragmentManager != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            if (canAddBackStrace) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        if (fragment != null && fragmentManager != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.commit();
        }
    }

    /**
     * <p>Open new fragment to existing fragment. Provides developer to add this fragment to backstack.</p>
     *
     * @param manager
     * @param fragment,         fragment to add
     * @param viewID            , layout id to replace the view.
     * @param canAddBackStrace, is added to backstrace
     */

    public static void replaceFragment(FragmentManager manager, Fragment fragment,
                                       boolean canAddBackStrace, int viewID) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right);
        ft.replace(viewID, fragment, fragment.getClass().getSimpleName());
        if (canAddBackStrace) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commitAllowingStateLoss();
    }

    public static void replaceFragment(FragmentManager manager, Fragment fragment,
                                       boolean canAddBackStrace, int viewID, boolean isEnableAnim) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(viewID, fragment, fragment.getClass().getSimpleName());
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        if (canAddBackStrace) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * <p>
     * Open new fragment to existing fragment. Provides developer to add this
     * fragment to backstack.
     * </p>
     *
     * @param manager
     * @param fragment,         fragment to add
     * @param args,             Bundle arguments to pass into next fragment
     * @param viewID            , layout id to replace the view.
     * @param canAddBackStrace, is added to backstrace
     */
    public static void replaceFragment(FragmentManager manager,
                                       Fragment fragment, Bundle args, boolean canAddBackStrace, int viewID) {
        fragment.setArguments(args);
        replaceFragment(manager, fragment, canAddBackStrace, viewID);
    }

    /**
     * <p>Close the number of fragments.</p>
     *
     * @param numBackStack, number of fragments to pop up.
     */
    public static void popBackStack(FragmentManager manager, int numBackStack) {
        int fragCount = manager.getBackStackEntryCount();
        for (int i = 0; i < fragCount - numBackStack; i++) {
            manager.popBackStack();
        }
    }

    public static BaseFragment getCurrentFragment(FragmentManager manager, int containerId) {
        return (BaseFragment) manager.findFragmentById(containerId);
    }

    public static void removeFragmentStack(FragmentManager fragmentManager) {
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Method to get previous fragment
     *
     * @param manager
     * @return
     */
    public static Fragment getPreviousFragment(FragmentManager manager) {
        try {
            FragmentManager.BackStackEntry backEntry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 2);
            Fragment fragment = manager.findFragmentByTag(backEntry.getName());
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

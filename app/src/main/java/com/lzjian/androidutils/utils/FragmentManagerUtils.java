package com.lzjian.androidutils.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * @Description: 管理fragment的工具类
 */

public class FragmentManagerUtils {

    public static void switchFragment(Fragment parentFrag, List<Fragment> fragments, int viewId, int index) {
        FragmentTransaction transaction = parentFrag.getChildFragmentManager().beginTransaction();
        switchFragment(transaction, fragments, viewId, index);
    }

    public static void switchFragment(AppCompatActivity parentAty, List<Fragment> fragments, int viewId, int index) {
        FragmentTransaction transaction = parentAty.getSupportFragmentManager().beginTransaction();
        switchFragment(transaction, fragments, viewId, index);
    }

    private static void switchFragment(FragmentTransaction transaction, List<Fragment> fragments, int viewId, int index) {
        Fragment fragment;
        // 先隐藏其他fragment
        for (int i = 0, j = fragments.size(); i < j; i++) {
            if (i != index) {
                fragment = fragments.get(i);
                if (fragment.isAdded()) {
                    if (!fragment.isHidden()){
                        transaction.hide(fragment);
                    }
                }
            }
        }
        // 再展示你想展示的fragment
        fragment = fragments.get(index);
        if (fragment.isAdded()) {
            if (fragment.isHidden()){
                transaction.show(fragment);
            }
        } else {
            transaction.add(viewId, fragment);
        }
        transaction.commitAllowingStateLoss();
    }
}

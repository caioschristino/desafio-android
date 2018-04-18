package com.v2.desafionubank.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.v2.desafionubank.R;

import java.util.Stack;


/**
 * Created by CaioSChristino on 20/02/2018.
 */

public class NavigationManager {
    private FragmentManager mFragmentManager;
    private Stack<BaseFragment> mFragmentStack;

    public interface BackFragmentNavigationListener {

        /**
         * Callback on backstack changed.
         */
        void doInFinishBackNavigation();
    }

    public void init(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
        mFragmentStack = new Stack<BaseFragment>();
    }

    public void popFragment(BackFragmentNavigationListener listener) {
        if (mFragmentStack.size() > 1) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            mFragmentStack.lastElement().onPause();
            ft.remove(mFragmentStack.pop());
            mFragmentStack.lastElement().onResume();
            ft.show(mFragmentStack.lastElement());
            ft.commit();
            mFragmentStack.lastElement().doInBackFragment();
        } else {
            listener.doInFinishBackNavigation();
        }
    }

    public void pushFragment(BaseFragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.show(fragment);

            ft.add(R.id.main_container, fragment);
            if (mFragmentStack.size() > 0) {
                Fragment last = mFragmentStack.lastElement();
                if (last != null) {
                    last.onPause();
                    ft.hide(last);
                }
            }
            mFragmentStack.push(fragment);
            ft.commit();
        }
    }

    private void popEveryFragment() {
        // Clear all back stack.
        int backStackCount = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            // Get the back stack fragment id.
            int backStackId = mFragmentManager.getBackStackEntryAt(i).getId();
            mFragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}

package com.example.mvc_kotlin.screens.common.fragmentframehelper

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentFrameHelper(
    val mActivity: Activity,
    val mFragmentFrameWrapper: FragmentFrameWrapper,
    val mFragmentManager: FragmentManager
) {

    fun replaceFragment(newFragment: Fragment) {
        replaceFragment(newFragment, true, false)
    }

    fun replaceFragmentDontAddToBackstack(newFragment: Fragment) {
        replaceFragment(newFragment, false, false)
    }

    fun replaceFragmentAndClearBackstack(newFragment: Fragment) {
        replaceFragment(newFragment, false, true)
    }

    fun navigateUp() {
        if (mFragmentManager.isStateSaved()) {
            return
        }

        val currentFragment = getCurrentFragment()

        if (mFragmentManager.getBackStackEntryCount() > 0) {
            removeCurrentFragment()

            if (mFragmentManager.popBackStackImmediate()) {
                return
            }
        }

        if (currentFragment is HierarchicalFragment) {
            val parentFragment: Fragment? =
                (currentFragment as HierarchicalFragment).getHierarchicalParentFragment()
            if (parentFragment != null) {
                replaceFragment(parentFragment, false, true)
                return
            }
        }

        if (mActivity.onNavigateUp()) {
            return
        }

        mActivity.onBackPressed()
    }

    private fun getCurrentFragment(): Fragment {
        return mFragmentManager.findFragmentById(getFragmentFrameId())!!
    }

    private fun replaceFragment(
        newFragment: Fragment,
        addToBackStack: Boolean,
        clearBackStack: Boolean
    ) {
        if (clearBackStack) {
            if (mFragmentManager.isStateSaved()) {
                return
            }
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        val ft = mFragmentManager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(null)
        }

        ft.replace(getFragmentFrameId(), newFragment, null)

        if (mFragmentManager.isStateSaved()) {
            ft.commitAllowingStateLoss()
        } else {
            ft.commit()
        }
    }

    private fun removeCurrentFragment() {
        val ft = mFragmentManager.beginTransaction()
        ft.remove(getCurrentFragment())
        ft.commit()
    }

    private fun getFragmentFrameId(): Int {
        return mFragmentFrameWrapper.getFragmentFrame().getId()
    }
}
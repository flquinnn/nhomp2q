package com.thick124.loplttd03.nhom03.homeTab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
public class HomeAdapter extends FragmentStatePagerAdapter{
    public HomeAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new lastMonthFragment();
            case 1:
                return new thisMonthFragment();
            default:
                return new thisMonthFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tháng trước";
            case 1:
                return "Tháng này";
            default:
                return "Tháng này";
        }
    }
}

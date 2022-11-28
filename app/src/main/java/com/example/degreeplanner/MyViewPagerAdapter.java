package com.example.degreeplanner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.degreeplanner.fragments.AddCourseFragment;
import com.example.degreeplanner.fragments.ListFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new AddCourseFragment();
            case 1:
                return new ListFragment();
            default:
                return new AddCourseFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

package com.caio.cervejeiros_sa.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.caio.cervejeiros_sa.fragment.FavoritesFragment;
import com.caio.cervejeiros_sa.fragment.HomeFragment;

public class AdapterFragments extends FragmentStateAdapter {


    public AdapterFragments(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return  new FavoritesFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

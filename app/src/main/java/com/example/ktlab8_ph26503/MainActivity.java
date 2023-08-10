package com.example.ktlab8_ph26503;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.ktlab8_ph26503.Fragment.InfoFragment;
import com.example.ktlab8_ph26503.Fragment.SubjectFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private List<String> fragmentTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Initialize the fragment list
        fragmentList = new ArrayList<>();
        fragmentList.add(new SubjectFragment());
        fragmentList.add(new InfoFragment());

        // Initialize the fragment title list
        fragmentTitleList = new ArrayList<>();
        fragmentTitleList.add("DANH SÁCH MÔN HỌC");
        fragmentTitleList.add("THÔNG TIN");

        // Set up the ViewPager2 with the fragment adapter
        viewPager.setAdapter(new FragmentAdapter(this, fragmentList));

        // Set up the TabLayout with the ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(fragmentTitleList.get(position))).attach();
    }

    private static class FragmentAdapter extends FragmentStateAdapter {
        private List<Fragment> fragmentList;

        public FragmentAdapter(FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
            super(fragmentActivity);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}
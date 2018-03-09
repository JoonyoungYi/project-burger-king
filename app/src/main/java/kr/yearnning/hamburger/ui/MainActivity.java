package kr.yearnning.hamburger.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import kr.yearnning.hamburger.R;
import kr.yearnning.hamburger.utils.lib_auil.AnimateFirstDisplayListener;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mViewPagerTabStrip;

    /**
     *
     */
    private ViewPagerAdapter mViewPagerAdapter;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        /**
         *
         */
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPagerTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //
        mViewPager.setOffscreenPageLimit(3);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPagerTabStrip.setViewPager(mViewPager);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (position == 3) {
            

        } else if (position == 0) {
            fragmentManager.beginTransaction().replace(R.id.container, FoodFragment.newInstance("set_menu")).commit();

        } else {
            fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();

        }


    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_delivery_menu);
                break;
            case 1:
                mTitle = getString(R.string.title_store);
                break;
            case 2:
                mTitle = getString(R.string.title_faq);
                break;
            case 3:
                mTitle = getString(R.string.title_online);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
/**

 @Override public boolean onCreateOptionsMenu(Menu menu) {
 if (!mNavigationDrawerFragment.isDrawerOpen()) {
 // Only show items in the action bar relevant to this screen
 // if the drawer is not showing. Otherwise, let the drawer
 // decide what to show in the action bar.
 getMenuInflater().inflate(R.menu.main, menu);
 restoreActionBar();
 return true;
 }
 return super.onCreateOptionsMenu(menu);
 }

 @Override public boolean onOptionsItemSelected(MenuItem item) {
 // Handle action bar item clicks here. The action bar will
 // automatically handle clicks on the Home/Up button, so long
 // as you specify a parent activity in AndroidManifest.xml.
 int id = item.getItemId();
 if (id == R.id.action_settings) {
 return true;
 }
 return super.onOptionsItemSelected(item);
 }
 **/
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main_fragment, container, false);
            return rootView;
        }
    }

    /**
     * Handle Press Back key twice.
     */
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "한 번 더 누르시면, \"KAIST 보드\"에서 빠져나갑니다.", Toast.LENGTH_SHORT)
                .show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;

            }
        }, 2000);
    }


    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/mViewPagerTabStrip/pages.
     */
    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private android.support.v4.app.FragmentManager fm;

        private int[] delivery_menu_titles = {R.string.title_delivery_menu_burger, R.string.title_delivery_menu_breakfast, R.string.title_delivery_menu_drink, R.string.title_delivery_menu_side, R.string.title_delivery_menu_delivery_pack};
        private String[] delivery_menu_slugs = {"burger", "breakfast", "drink", "side", "delivery_pack"};

        /**
         * @param fm
         */
        public ViewPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        /**
         * getItem is called to instantiate the fragment for the given page.
         * Return a PlaceholderFragment (defined as a static inner class below)
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            return FoodFragment.newInstance(delivery_menu_slugs[position]);
        }

        /**
         * @param object
         * @return
         */
        @Override
        public int getItemPosition(Object object) {
            //if (object instanceof MainFragment) {
            //    ((MainFragment) object).requestRefresh();
            //}
            return POSITION_NONE;
        }

        /**
         * Show 5 total pages.
         *
         * @return
         */
        @Override
        public int getCount() {
            return delivery_menu_titles.length;
        }

        /**
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return getString(delivery_menu_titles[position]);
        }

        /**
         * @param container
         * @param position
         * @return
         */
        public Fragment getFragment(ViewPager container, int position) {
            String tag = makeFragmentName(container.getId(), position);
            return this.fm.findFragmentByTag(tag);
        }

        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AnimateFirstDisplayListener.displayedImages.clear();
    }
}

package kr.yearnning.hamburger.ui;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.ArrayList;

import kr.yearnning.hamburger.R;
import kr.yearnning.hamburger.api.ApiBase;
import kr.yearnning.hamburger.api.FoodListApi;
import kr.yearnning.hamburger.model.Food;
import kr.yearnning.hamburger.utils.Application;
import kr.yearnning.hamburger.utils.Argument;
import kr.yearnning.hamburger.utils.lib_auil.AnimateFirstDisplayListener;


public class FoodFragment extends Fragment {
    private static final String TAG = "FoodFragment";
    private static final String ARG_SLUG = "arg_slug";

    private String mSlug = null;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private FoodListApiTask mFoodListApiTask = null;

    /**
     *
     */

    private View mErrorView;
    private TextView mErrorTv;
    private ProgressBar mErrorPb;

    private ListView mLv;
    private LvAdapter mLvAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MenuFragment.
     */
    public static FoodFragment newInstance(String slug) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SLUG, slug);
        fragment.setArguments(args);
        return fragment;
    }

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSlug = getArguments().getString(ARG_SLUG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.food_fragment, container, false);

        /**
         * UI indexing
         */
        mLv = (ListView) rootView.findViewById(R.id.lv);
        mErrorView = rootView.findViewById(R.id.error_view);
        mErrorTv = (TextView) mErrorView.findViewById(R.id.error_tv);
        mErrorPb = (ProgressBar) mErrorView.findViewById(R.id.error_pb);
        // mFooterPb = (ProgressBar) lvFooterView.findViewById(R.id.pb);

        /*

         */
        //ListView.LayoutParams params = new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        mLv.addHeaderView(new View(getActivity()));
        mLv.addFooterView(new View(getActivity()));
        mLv.setOnScrollListener(new PauseOnScrollListener(((Application) getActivity().getApplication()).imageLoader, true, true));

        /*
         * ListView Setting
		 */
        ArrayList<Food> foods = new ArrayList<Food>();
        mLvAdapter = new LvAdapter(getActivity(), R.layout.food_fragment_lv, foods);
        mLv.setAdapter(mLvAdapter);

        /**
         *
         */
        requestRefresh();

        return rootView;
    }

    /**
     *
     */

    public void requestRefresh() {
        if (mFoodListApiTask == null) {
            mFoodListApiTask = new FoodListApiTask();
            mFoodListApiTask.execute();
        }

    }

    /**
     * ListView Apdater Setting
     */

    private class LvAdapter extends ArrayAdapter<Food> {
        private static final String TAG = "FoodFragment LvAdapter";

        private final int TAG_KEY_DETAIL = R.string.title_activity_detail;

        /**
         *
         */
//        private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

        /**
         *
         */
        private Resources r;
        private ViewHolder viewHolder = null;
        public ArrayList<Food> foods;
        private int textViewResourceId;


        /**
         * @param context
         * @param textViewResourceId
         * @param foods
         */
        public LvAdapter(Activity context, int textViewResourceId,
                         ArrayList<Food> foods) {
            super(context, textViewResourceId, foods);

            this.textViewResourceId = textViewResourceId;
            this.foods = foods;

            this.r = context.getResources();
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public int getCount() {
            return (foods.size() / 2) + (foods.size() % 2);
        }

        @Override
        public Food getItem(int position) {
            return foods.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

			/*
             * UI Initiailizing : View Holder
			 */

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(textViewResourceId, null);

                viewHolder = new ViewHolder();

                /**
                 * Find View By ID
                 */

                viewHolder.mContainer[0] = convertView.findViewById(R.id.left);
                viewHolder.mContainer[1] = convertView.findViewById(R.id.right);

                for (int i = 0; i < 2; i++) {
                    viewHolder.mIv[i] = (ImageView) viewHolder.mContainer[i].findViewById(R.id.iv);
                    viewHolder.mNameTv[i] = (TextView) viewHolder.mContainer[i].findViewById(R.id.name_tv);
                    viewHolder.mDescriptionTv[i] = (TextView) viewHolder.mContainer[i].findViewById(R.id.description_tv);

                    /**
                     * Init Set On Click Listener
                     */
                    viewHolder.mContainer[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Food food = (Food) v.getTag(TAG_KEY_DETAIL);
                            DetailActivity.startActivity(getActivity(), "", food.getName(), food.getImg_url(), food.getPrice(), food.getKcal());
                        }
                    });
                }
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Food[] food = new Food[2];
            String[] name = new String[2];
            String[] img_url = new String[2];
            int[] price = new int[2];
            int[] kcal = new int[2];

            for (int i = 0; i < 2; i++) {
                food[i] = foods.get(position * 2 + i);
                viewHolder.mContainer[i].setTag(TAG_KEY_DETAIL, food[i]);

                img_url[i] = food[i].getImg_url();
                ((Application) getActivity().getApplication()).imageLoader
                        .displayImage(img_url[i], viewHolder.mIv[i], ((Application) getActivity().getApplication()).options, animateFirstListener);

                name[i] = food[i].getName();
                viewHolder.mNameTv[i].setText(name[i]);

                price[i] = food[i].getPrice();
                kcal[i] = food[i].getKcal();
                viewHolder.mDescriptionTv[i].setText(price[i] + "원(" + kcal[i] + "kcal)");
            }

            return convertView;
        }

        private class ViewHolder {

            View mContainer[] = new View[2];

            ImageView mIv[] = new ImageView[2];
            TextView mNameTv[] = new TextView[2];
            TextView mDescriptionTv[] = new TextView[2];
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class FoodListApiTask extends AsyncTask<Void, Void, ArrayList<Food>> {
        private int request_code = Argument.REQUEST_CODE_UNEXPECTED;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<Food> doInBackground(Void... param) {
            ArrayList<Food> foods = null;

            try {

                FoodListApi storeListApi = new FoodListApi(getActivity().getApplication());
                request_code = storeListApi.getRequestCode();
                if (request_code == Argument.REQUEST_CODE_SUCCESS)
                    foods = storeListApi.getResult();

            } catch (Exception e) {
                e.printStackTrace();
                cancel(true);
            }

            return foods;
        }

        @Override
        protected void onPostExecute(ArrayList<Food> foods) {
            mFoodListApiTask = null;

            ApiBase.showToastMsg(getActivity().getApplication(), request_code);

            if (request_code == Argument.REQUEST_CODE_SUCCESS) {

                if (foods.size() > 0) {
                    showErrorView(false, "");
                    mLvAdapter.foods.addAll(foods);
                    mLvAdapter.notifyDataSetChanged();

                } else if (mLvAdapter.foods.size() == 0) {
                    showErrorView(true, "데이터가 없습니다");
                }

            } else {
                showErrorView(true, "오류가 발생해 메뉴를 불러오지 못했습니다");
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mFoodListApiTask = null;

        }

    }

    /**
     * @param show
     */
    private void showErrorView(final boolean show, String msg) {

        if (show) {
            mLv.setVisibility(View.GONE);
            mErrorView.setVisibility(View.VISIBLE);
            mErrorTv.setText(msg);

            if (msg.equals("")) {
                mErrorPb.setVisibility(View.VISIBLE);
            } else {
                mErrorPb.setVisibility(View.GONE);
            }

        } else {
            mLv.setVisibility(View.VISIBLE);
            mErrorPb.setVisibility(View.VISIBLE);
            mErrorView.setVisibility(View.GONE);
        }
    }

    /**
     *
     */
    public void onDestroy() {
        super.onDestroy();

        if (mFoodListApiTask != null) {
            mFoodListApiTask.cancel(true);
        }
    }

    /**
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SLUG));
    }
}

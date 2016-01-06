package com.example.myfirst.navdrawdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener
{
    private DrawerLayout drawerLayout;
    ListView listView;
    private ActionBarDrawerToggle drawerListener;
    private MyAdapter myAdapter;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        listView=(ListView)findViewById(R.id.drawerList);
        myAdapter=new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);

        drawerListener=new ActionBarDrawerToggle(this,drawerLayout,R.drawable.ic_drawer,R.string.navigation_drawer_open,R
        .string.navigation_drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if(drawerListener.onOptionsItemSelected(item))
                {
                    return true;
                }
                return super.onOptionsItemSelected(item);
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                super.onConfigurationChanged(newConfig);
                drawerListener.onConfigurationChanged(newConfig);
            }

            @Override
            protected void onPostCreate(Bundle savedInstanceState) {
                super.onPostCreate(savedInstanceState);
                drawerListener.syncState();
            }

            @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            selectItem(position);
        }

            public void selectItem(int position)
            {
                listView.setItemChecked(position,true);
            }
            public void setTitle(String title)
            {
                getSupportActionBar().setTitle(title);
            }

    public void onNavigationDrawerItemSelected(int position)
    {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
class MyAdapter extends BaseAdapter
{
    private Context context;
    String[] ShopList={"Home","Event","Mail","Shop","Travel"};
    int[] icon={R.drawable.home_logo,R.drawable.event_logo,R.drawable.mail_logo,R.drawable.shop_logo,R.drawable.travel_logo};

    MyAdapter(Context context)
    {
        this.context=context;
    }

    @Override
    public int getCount() {
        return ShopList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View row=null;
        if(convertView == null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.custome_row,parent,false);
        }
        else
        {
            row=convertView;
        }
        TextView titleTextView=(TextView)row.findViewById(R.id.textView);
        ImageView titleImageView=(ImageView)row.findViewById(R.id.imageView);
        titleTextView.setText(ShopList[position]);
        titleImageView.setImageResource(icon[position]);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context,"You clicked "+ShopList[position],Toast.LENGTH_SHORT).show();
                if(ShopList[position] == "Event")
                {
                    Intent intent=new Intent(context,Event.class);
                    context.startActivity(intent);
                }
                if(ShopList[position] == "Home")
                {
                    Toast.makeText(context,"In home..",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,Home.class);
                    context.startActivity(intent);
                }
                if(ShopList[position] == "Mail")
                {
                    Intent intent=new Intent(context,Mail.class);
                    context.startActivity(intent);
                }
                if(ShopList[position] == "Shop")
                {
                    Intent intent=new Intent(context,Shop.class);
                    context.startActivity(intent);
                }
                if(ShopList[position] == "Travel")
                {
                    Intent intent=new Intent(context,Travel.class);
                    context.startActivity(intent);
                }
            }
        });
        return row;
    }
}
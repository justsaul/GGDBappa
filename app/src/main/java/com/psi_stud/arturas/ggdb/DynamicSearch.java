package com.psi_stud.arturas.ggdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;


/**
 * Shows a list that can be filtered in-place with a SearchView in non-iconified mode.
 */
public class DynamicSearch extends Activity implements SearchView.OnQueryTextListener {

    private static final String TAG = "SearchViewFilterMode";

    ArrayList<Game> itemListTest;

    private SearchView mSearchView;
    ListView mListView;
    private ArrayAdapter<String> mAdapter;

    //private final String[] mStrings = Cheeses.sCheeseStrings;
    private String[] mStrings = {"bbb", "a", "aa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemListTest = new ArrayList();

        Game x = new Game(123, "bbb");
        Game y = new Game(1234, "a");
        Game z = new Game(12341, "aa");

        itemListTest.add(x);
        itemListTest.add(y);
        itemListTest.add(z);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_dynamic_search);

        mSearchView = (SearchView) findViewById(R.id.search_view);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mStrings));
        mListView.setTextFilterEnabled(true);
        setupSearchView();

        mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("lol");
                System.out.println(position);
                System.out.println(mListView.getItemAtPosition(position));

                for (int i = 0; i < itemListTest.size(); i++) {
                    if (itemListTest.get(i).getName() == mListView.getItemAtPosition(position)) {
                        System.out.println("match");
                        System.out.println(itemListTest.get(i).getGameID());
                        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("gameID", itemListTest.get(i).getGameID());
                        intent.putExtras(b);
                        startActivity(intent);
                        break;
                    }
                }
                System.out.println(mListView.getItemAtPosition(position));

            }
        });
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(false);
        //mSearchView.setQueryHint(getString(R.string.cheese_hunt_hint));
    }

    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(newText.toString());
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}

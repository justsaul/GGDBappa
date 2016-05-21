package com.psi_stud.arturas.ggdb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    ArrayList<Game> gamesList;

    private SearchView mSearchView;
    ListView mListView;
    private ArrayAdapter<String> mAdapter;

    int ageOfUser;
    //private final String[] mStrings = Cheeses.sCheeseStrings;
    private String[] mStrings = new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int userAge;
        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userAge = saved_values.getInt("age", -1);

        System.out.println(userAge);

        gamesList = new ArrayList();

        Game gameEntity = new Game();

        for (int i = 0; i < mStrings.length; i++) {
            mStrings[i] = "";
        }

        gamesList = gameEntity.getGamesList();
        for (int i = 0; i < gamesList.size(); i++) {
            mStrings[i] = gamesList.get(i).getName();
        }

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
                for (int i = 0; i < gamesList.size(); i++) {
                    if (gamesList.get(i).getName() == mListView.getItemAtPosition(position)) {
                        ageOfUser = userAge;
                        System.out.println(ageOfUser);
                        if(gamesList.get(i).getAge() == 0) {
                            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("gameID", gamesList.get(i).getGameID());
                            intent.putExtras(b);
                            startActivity(intent);
                        }else if(ageOfUser >= gamesList.get(i).getAge()) {
                            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("gameID", gamesList.get(i).getGameID());
                            intent.putExtras(b);
                            startActivity(intent);
                        } else {
                            Intent intentErrorMessage = new Intent(getApplicationContext(), MessageActivity.class);
                            Bundle bErrMessage = new Bundle();
                            if(ageOfUser == -1) {
                                bErrMessage.putString("ErrorMessage", "Prisijunkite, siam pasirinkimui reikia jusu amziuas");
                                intentErrorMessage.putExtras(bErrMessage);
                            } else {
                                bErrMessage.putString("ErrorMessage", "Jusu amzius nera tinkamas siam zaidimui");
                                intentErrorMessage.putExtras(bErrMessage);
                            }
                            startActivity(intentErrorMessage);
                        }
                        break;
                    }
                }
            }
        });
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(false);
        mListView.setVisibility(View.INVISIBLE);
    }

    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            mListView.setVisibility(View.INVISIBLE);
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(newText.toString());
            mListView.setVisibility(View.VISIBLE);
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}

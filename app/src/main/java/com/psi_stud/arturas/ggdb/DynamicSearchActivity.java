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
public class DynamicSearchActivity extends Activity implements SearchView.OnQueryTextListener {

   // private static final String TAG = "SearchViewFilterMode";

    private SearchView mSearchView;
    ListView mListView;
    private ArrayAdapter<String> mAdapter;
    int userAge;
    int ageOfUser;
    ArrayList<Game> gamesList;
    SharedPreferences saved_values;
    private String[] mStrings = new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //userAge = saved_values.getInt("age", -1);

        gamesList = new ArrayList();
        fillList();

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
                        System.out.println(ageOfUser);

                        GamePresenter gamePresenter = new GamePresenter();
                        UserPresenter userPresenter = new UserPresenter();

                        userAge = saved_values.getInt("age", -1);
                        ageOfUser = userAge;
                        if(gamePresenter.isGameCensored(gamesList.get(i).getAge())) {
                            if(userPresenter.isUserSignedUp(ageOfUser)) {
                                if(userPresenter.isAppropriateAge(ageOfUser, gamesList.get(i).getAge())) {
                                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                                    Bundle b = new Bundle();
                                    b.putInt("gameID", gamesList.get(i).getGameID());
                                    intent.putExtras(b);
                                    startActivity(intent);
                                } else {
                                    Intent intentErrorMessage = new Intent(getApplicationContext(), MessageActivity.class);
                                    Bundle bErrMessage = new Bundle();
                                    bErrMessage.putString("ErrorMessage", "Jusu amzius nera tinkamas siam zaidimui");
                                    intentErrorMessage.putExtras(bErrMessage);
                                    startActivity(intentErrorMessage);
                                }
                            } else {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("gameID", gamesList.get(i).getGameID());
                            intent.putExtras(b);
                            startActivity(intent);
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

    public void fillList() {
        SearchPresenter searchPresenter = new SearchPresenter();
        mStrings = searchPresenter.getmStrings();
        gamesList = searchPresenter.getgameList();
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}

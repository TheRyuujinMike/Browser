package edu.temple.browser;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BrowserFragment.OnFragmentInteractionListener{

    private ArrayList<BrowserFragment> fragList = new ArrayList<>();
    private int currentBrowser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Action Bar Code
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        fragList.add(new BrowserFragment());

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.browser_space, fragList.get(0))
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_left:

                if (currentBrowser > 0) {

                    currentBrowser--;

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.browser_space, fragList.get(currentBrowser))
                            .commit();

                }

                else Toast.makeText(this, "This is the farthest left tab.", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.action_right:

                if (currentBrowser < (fragList.size() - 1)) {

                    currentBrowser++;

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.browser_space, fragList.get(currentBrowser))
                            .commit();

                }

                else Toast.makeText(this, "This is the farthest right tab.", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.action_new:
                fragList.add(new BrowserFragment());
                currentBrowser = fragList.size() - 1;

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.browser_space, fragList.get(currentBrowser))
                        .commit();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}

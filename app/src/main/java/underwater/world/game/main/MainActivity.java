package underwater.world.game.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import underwater.world.game.R;
import underwater.world.game.fragment.DefeatFragment;
import underwater.world.game.fragment.GameFragment;
import underwater.world.game.fragment.MenuFragment;
import underwater.world.game.fragment.SplashScreenFragment;
import underwater.world.game.fragment.VictoryFragment;

public class MainActivity extends AppCompatActivity {

    private static final String SPLASH_SCREEN_FRAGMENT_TAG = "SplashScreenFragmentTag";
    private static final String MENU_FRAGMENT_TAG = "MenuFragmentTag";
    private static final String GAME_FRAGMENT_TAG = "GameFragmentTag";
    private static final String VICTORY_FRAGMENT_TAG = "VictoryFragmentTag";
    private static final String DEFEAT_FRAGMENT_TAG = "DefeatFragmentTag";

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        // Show splash screen on app startup
        showSplashScreenFragment();
    }

    // Show splash screen fragment
    private void showSplashScreenFragment() {
        Fragment splashScreenFragment = new SplashScreenFragment();
        replaceFragment(splashScreenFragment, SPLASH_SCREEN_FRAGMENT_TAG);
    }

    // Show menu fragment
    public void showMenuFragment() {
        Fragment menuFragment = new MenuFragment();
        replaceFragment(menuFragment, MENU_FRAGMENT_TAG);
    }

    // Show game fragment
    public void showGameFragment() {
        Fragment gameFragment = new GameFragment();
        replaceFragment(gameFragment, GAME_FRAGMENT_TAG);
    }

    // Show victory fragment
    public void showVictoryFragment() {
        Fragment victoryFragment = new VictoryFragment();
        replaceFragment(victoryFragment, VICTORY_FRAGMENT_TAG);
    }

    // Show defeat fragment
    public void showDefeatFragment() {
        Fragment defeatFragment = new DefeatFragment();
        replaceFragment(defeatFragment, DEFEAT_FRAGMENT_TAG);
    }

    // Replace current fragment with a new one
    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Handle back button press
    @Override
    public void onBackPressed() {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 1) {
            // Pop the fragment from the back stack
            fragmentManager.popBackStackImmediate();
        } else {
            // If there is only one fragment left in the back stack, finish the activity
            finish();
        }
    }

    // Show game over fragment
    public void showGameOverFragment(int score) {
        Fragment gameOverFragment = new DefeatFragment();
        replaceFragment(gameOverFragment, DEFEAT_FRAGMENT_TAG);
    }

    // Show game won fragment
    public void showGameWonFragment(int score) {
        Fragment gameWonFragment = new VictoryFragment();
        replaceFragment(gameWonFragment, VICTORY_FRAGMENT_TAG);
    }


    public void onRestartButtonClick() {
        // Remove all fragments from back stack and show the game fragment
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        showGameFragment();
    }

}

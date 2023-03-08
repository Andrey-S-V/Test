package underwater.world.game.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import underwater.world.game.main.MainActivity;
import underwater.world.game.R;

public class SplashScreenFragment extends Fragment {

    private static final int SPLASH_SCREEN_DURATION = 3000; // 3 seconds

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        // Delay showing menu screen for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) getActivity()).showMenuFragment();
            }
        }, SPLASH_SCREEN_DURATION);

        return view;
    }
}

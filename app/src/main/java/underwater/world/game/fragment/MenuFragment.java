package underwater.world.game.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import underwater.world.game.main.MainActivity;
import underwater.world.game.R;


public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        // Set up button click listeners
        view.findViewById(R.id.start_game_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show game fragment
                ((MainActivity) getActivity()).showGameFragment();
            }
        });
        view.findViewById(R.id.quit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder quitDialog = new AlertDialog.Builder(getContext());
                quitDialog.setTitle(getString(R.string.exit_dialog));
                quitDialog.setPositiveButton(getString(R.string.button_yes), (dialogInterface, i) -> System.exit(0));
                quitDialog.setNegativeButton(getString(R.string.button_no), (dialogInterface, i) -> { });
                quitDialog.create().show();
            }
        });
        return view;
    }
}
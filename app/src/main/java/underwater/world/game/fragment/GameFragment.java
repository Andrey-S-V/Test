package underwater.world.game.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import underwater.world.game.main.MainActivity;
import underwater.world.game.R;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameFragment extends Fragment implements View.OnClickListener {

    private static final int MAX_SCORE = 100;
    private static final int MAX_LIVES = 3;
    private int bonusCount = 0;

    private ImageView submarine;
    private TextView scoreTextView;
    private TextView livesTextView;
    private View obstacle1;
    private View obstacle2;
    private View obstacle3;
    public int score = 0;
    private int lives = MAX_LIVES;

    private Handler handler;
    private boolean isRunning = false;

    private final Runnable gameLoop = new Runnable() {
        @Override
        public void run() {
            moveObstacles();
            checkCollisions();
            updateScore();
            checkGameOver();
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        submarine = view.findViewById(R.id.submarine);
        scoreTextView = view.findViewById(R.id.score_text_view);
        livesTextView = view.findViewById(R.id.lives_text_view);
        obstacle1 = view.findViewById(R.id.obstacle_1);
        obstacle2 = view.findViewById(R.id.obstacle_2);
        obstacle3 = view.findViewById(R.id.obstacle_3);

        livesTextView.setText(getString(R.string.lives_format, lives));

        view.findViewById(R.id.move_left_button).setOnClickListener(this);
        view.findViewById(R.id.move_right_button).setOnClickListener(this);

        handler = new Handler(Looper.getMainLooper());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        startGame();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopGame();
    }

    private void startGame() {
        if (!isRunning) {
            isRunning = true;
            handler.post(gameLoop);
        }
    }

    private void stopGame() {
        if (isRunning) {
            isRunning = false;
            handler.removeCallbacks(gameLoop);
        }
    }

    private void moveObstacles() {
        moveObstacle(obstacle1, 40);
        moveObstacle(obstacle2, 60);
        moveObstacle(obstacle3, 30);
        generateBonus();
    }

    private void moveObstacle(View obstacle, int speed) {
        obstacle.setY(obstacle.getY() + speed);
        if (obstacle.getY() > submarine.getY() + submarine.getHeight()) {
            resetObstacle(obstacle);
        }
    }

    private void resetObstacle(View obstacle) {
        Random random = new Random();
        int minX = 0;
        int maxX = getActivity().findViewById(R.id.game_container).getWidth() - obstacle.getWidth();
        int randomX = random.nextInt(maxX - minX + 1) + minX;
        int minY = -(int) obstacle.getHeight() * 2;
        int maxY = -(int) obstacle.getHeight(); // Set the obstacle to a random position above the screen
        obstacle.setX(randomX);
        obstacle.setY(random.nextInt(maxY - minY + 1) + minY);
    }

    private void checkCollisions() {
        if (obstacle1.getVisibility() == View.VISIBLE && checkCollision(submarine, obstacle1)) {
            obstacle1.setVisibility(View.INVISIBLE);
            loseLife();
        }
        if (obstacle2.getVisibility() == View.VISIBLE && checkCollision(submarine, obstacle2)) {
            obstacle2.setVisibility(View.INVISIBLE);
            loseLife();
        }
        if (obstacle3.getVisibility() == View.VISIBLE && checkCollision(submarine, obstacle3)) {
            obstacle3.setVisibility(View.INVISIBLE);
            loseLife();
        }
    }

    private boolean checkCollision(View view1, View view2) {
        int[] view1Location = new int[2];
        view1.getLocationOnScreen(view1Location);
        int view1Left = view1Location[0];
        int view1Right = view1Left + view1.getWidth();
        int view1Top = view1Location[1];
        int view1Bottom = view1Top + view1.getHeight();

        int[] view2Location = new int[2];
        view2.getLocationOnScreen(view2Location);
        int view2Left = view2Location[0];
        int view2Right = view2Left + view2.getWidth();
        int view2Top = view2Location[1];
        int view2Bottom = view2Top + view2.getHeight();

        return view1Left < view2Right && view1Right > view2Left && view1Top < view2Bottom && view1Bottom > view2Top;
    }

    private void updateScore() {
        score++;
        scoreTextView.setText(getString(R.string.score_format, score));
    }

    private void checkGameOver() {
        if (lives == 0) {
            stopGame();
            ((MainActivity) getActivity()).showGameOverFragment(score);
        } else if (score == MAX_SCORE) {
            stopGame();
            ((MainActivity) getActivity()).showGameWonFragment(score);
        }
    }

    private void loseLife() {
        lives--;
        livesTextView.setText(getString(R.string.lives_format, lives));
        if (lives == 0) {
            checkGameOver();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.move_left_button:
                moveSubmarine(-submarine.getWidth() / 4);
                break;
            case R.id.move_right_button:
                moveSubmarine(submarine.getWidth() / 4);
                break;
        }
    }

    private void moveSubmarine(int distance) {
        int newX = (int) submarine.getX() + distance;
        int minX = 0;
        int maxX = getActivity().findViewById(R.id.game_container).getWidth() - submarine.getWidth();
        submarine.setX(Math.min(Math.max(newX, minX), maxX));
    }

    private void generateBonus() {
        ImageView bonus = new ImageView(getContext());
        bonus.setImageResource(R.drawable.bonus);
        int bonusSize = getResources().getDimensionPixelSize(R.dimen.bonus_size);
        int randomX = new Random().nextInt(getActivity().findViewById(R.id.game_container).getWidth() - bonusSize);
        bonus.setX(randomX);
        bonus.setY(-bonusSize);
        ((ViewGroup) getActivity().findViewById(R.id.game_container)).addView(bonus);
        animateBonus(bonus);
    }

    private void animateBonus(View bonus) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(bonus, "y", bonus.getY(), submarine.getY() + submarine.getHeight());
        animator.setDuration(5000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ((ViewGroup) getActivity().findViewById(R.id.game_container)).removeView(bonus);
                if (++bonusCount == 3) {
                    bonusCount = 0;
                    lives++;
                    livesTextView.setText(getString(R.string.lives_format, lives));
                }
            }
        });
        animator.start();
    }

}
package elbossily.footballscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Text Views declaration
    private TextView teamAScoreTextView;
    private TextView teamAFoulTextView;
    private TextView teamBScoreTextView;
    private TextView teamBFoulTextView;
    //Button Declaration
    private Button teamAGoalButton;
    private Button teamBGoalButton;
    private Button teamAFoulButton;
    private Button teamBFoulButton;
    private Button resetButton;
    //team A score
    private int teamAScore = 0;
    //team B Score
    private int teamBScore = 0;
    //team A fouls
    private int teamAFouls = 0;
    //team A fouls
    private int teamBFouls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find text views by their id
        teamAScoreTextView = (TextView) findViewById(R.id.team_a_score_text_id);
        teamAFoulTextView = (TextView) findViewById(R.id.team_a_foul_text_id);
        teamBScoreTextView = (TextView) findViewById(R.id.team_b_score_text_id);
        teamBFoulTextView = (TextView) findViewById(R.id.team_b_foul_text_id);

        //find Buttons by their id
        teamAGoalButton = (Button) findViewById(R.id.team_a_goal_button);
        teamBGoalButton = (Button) findViewById(R.id.team_b_goal_button);
        teamAFoulButton = (Button) findViewById(R.id.team_a_foul_button);
        teamBFoulButton = (Button) findViewById(R.id.team_b_foul_button);
        resetButton = (Button) findViewById(R.id.reset_button_id);

        //initiate text views
        teamAScoreTextView.setText("" + teamAScore);
        teamAFoulTextView.setText("" + teamAFouls);
        teamBScoreTextView.setText("" + teamBScore);
        teamBFoulTextView.setText("" + teamBFouls);

        //increase team A score by one in case of Goal button of team A clicked
        teamAGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore++;
                teamAScoreTextView.setText("" + teamAScore);
            }
        });

        //increase team B score by one in case of Goal button of team B clicked
        teamBGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore++;
                teamBScoreTextView.setText("" + teamBScore);
            }
        });


        //increase team A foul by one in case of foul button of team A clicked
        teamAFoulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAFouls++;
                teamAFoulTextView.setText("" + teamAFouls);
            }
        });

        //increase team B foul by one in case of foul button of team B clicked
        teamBFoulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBFouls++;
                teamBFoulTextView.setText("" + teamBFouls);
            }
        });

        //reset all all text views to be Zero in case of reset button is clicked
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // set the saved values to zero
                teamAScore = 0;
                teamBScore = 0;
                teamAFouls = 0;
                teamBFouls = 0;

                //set the TextViews to zero
                teamAScoreTextView.setText("" + teamAScore);
                teamAFoulTextView.setText("" + teamAFouls);
                teamBScoreTextView.setText("" + teamBScore);
                teamBFoulTextView.setText("" + teamBFouls);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //put every value in corresponding key
        outState.putInt("teamAScoreKey", teamAScore);
        outState.putInt("teamBScoreKey", teamBScore);
        outState.putInt("teamAFoulKey", teamAFouls);
        outState.putInt("teamBFoulKey", teamBFouls);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //get the saved values in case of restore
        teamAScore = savedInstanceState.getInt("teamAScoreKey");
        teamBScore = savedInstanceState.getInt("teamBScoreKey");
        teamAFouls = savedInstanceState.getInt("teamAFoulKey");
        teamBFouls = savedInstanceState.getInt("teamBFoulKey");

        //set the TextViews to corresponding saved value
        teamAScoreTextView.setText("" + teamAScore);
        teamAFoulTextView.setText("" + teamAFouls);
        teamBScoreTextView.setText("" + teamBScore);
        teamBFoulTextView.setText("" + teamBFouls);
    }
}

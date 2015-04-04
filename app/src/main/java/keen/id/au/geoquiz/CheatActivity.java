package keen.id.au.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by joe on 4/04/15.
 */
public class CheatActivity extends Activity {

    protected static final String EXTRA_ANSWER_IS_TRUE = "au.id.keen.geoquiz.answer_is_true";
    protected static final String EXTRA_ANSWER_SHOWN = "au.id.keen.geoquiz.answer_shown";
    private static final String KEY_ANSWER_SHOWN = "answer_show";

    private Button mShowAnswerButton;
    private TextView mAnswerTextView;
    private boolean mAnswerIsTrue;
    private boolean mAnswerWasShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        if (null != savedInstanceState) {
            mAnswerWasShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN, false);
        }
        setAnswerShownResult(mAnswerWasShown);

        mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnswerWasShown = true;
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWER_SHOWN, mAnswerWasShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}

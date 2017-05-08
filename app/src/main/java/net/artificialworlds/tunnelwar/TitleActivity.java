package net.artificialworlds.tunnelwar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class TitleActivity extends AppCompatActivity
{
    private static class Widgets
    {
        final Spinner red1Spinner;
        final Spinner red2Spinner;
        final Spinner red3Spinner;
        final Spinner red4Spinner;
        final Spinner green1Spinner;
        final Spinner green2Spinner;
        final Spinner green3Spinner;
        final Spinner green4Spinner;

        private final List<Spinner> reds;
        private final List<Spinner> greens;

        //private static final int DISABLED = 0;
        private static final int AI = 1;
        private static final int HUMAN = 2;

        private Widgets(
            Spinner red1Spinner,
            Spinner red2Spinner,
            Spinner red3Spinner,
            Spinner red4Spinner,
            Spinner green1Spinner,
            Spinner green2Spinner,
            Spinner green3Spinner,
            Spinner green4Spinner
        )
        {
            this.red1Spinner = red1Spinner;
            this.red2Spinner = red2Spinner;
            this.red3Spinner = red3Spinner;
            this.red4Spinner = red4Spinner;
            this.reds = Arrays.asList(
                this.red1Spinner,
                this.red2Spinner,
                this.red3Spinner,
                this.red4Spinner
            );

            this.green1Spinner = green1Spinner;
            this.green2Spinner = green2Spinner;
            this.green3Spinner = green3Spinner;
            this.green4Spinner = green4Spinner;
            this.greens = Arrays.asList(
                this.green1Spinner,
                this.green2Spinner,
                this.green4Spinner,
                this.green4Spinner
            );
        }

        public int redHumans()
        {
            return count_chosen(HUMAN, reds);
        }

        public int redAis()
        {
            return count_chosen(AI, reds);
        }

        public int greenHumans()
        {
            return count_chosen(HUMAN, greens);
        }

        public int greenAis()
        {
            return count_chosen(AI, greens);
        }

        private static int count_chosen(int choice, List<Spinner> spinners)
        {
            int ret = 0;

            for (Spinner spinner : spinners)
            {
                if (spinner.getSelectedItemPosition() == choice)
                {
                    ret++;
                }
            }

            return ret;
        }
    }

    private Widgets widgets;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        widgets = findWidgets();
        makeDefaultPlayerSelections();
    }

    private Widgets findWidgets()
    {
        return new Widgets(
            (Spinner)findViewById(R.id.red1Spinner),
            (Spinner)findViewById(R.id.red2Spinner),
            (Spinner)findViewById(R.id.red3Spinner),
            (Spinner)findViewById(R.id.red4Spinner),
            (Spinner)findViewById(R.id.green1Spinner),
            (Spinner)findViewById(R.id.green2Spinner),
            (Spinner)findViewById(R.id.green3Spinner),
            (Spinner)findViewById(R.id.green4Spinner)
        );
    }

    private void makeDefaultPlayerSelections()
    {
        widgets.red1Spinner.setSelection(2);
        widgets.green1Spinner.setSelection(1);
    }

    public void startClicked(View startButton)
    {
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra("red_humans", widgets.redHumans());
        gameIntent.putExtra("red_ais", widgets.redAis());
        gameIntent.putExtra("green_humans", widgets.greenHumans());
        gameIntent.putExtra("green_ais", widgets.greenAis());
        startActivity(gameIntent);
    }
}

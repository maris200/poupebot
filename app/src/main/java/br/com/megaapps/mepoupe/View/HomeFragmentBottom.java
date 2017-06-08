package br.com.megaapps.mepoupe.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.R;

/**
 * Created by duh on 5/21/17.
 */

public class HomeFragmentBottom extends MyFragment {

    private LinearLayout layHome;
    private LinearLayout layDream;
    private LinearLayout layChart;
    private LinearLayout layBot;
    private LinearLayout layAccount;

    private AppCompatImageView home;
    private AppCompatImageView goal;
    private AppCompatImageView chart;
    private AppCompatImageView bot;
    private AppCompatImageView account;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_bottom_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layHome = (LinearLayout) getActivity().findViewById(R.id.layHome);
        layDream = (LinearLayout) getActivity().findViewById(R.id.layDream);
        layChart = (LinearLayout) getActivity().findViewById(R.id.layChart);
        layBot = (LinearLayout) getActivity().findViewById(R.id.layBot);
        layAccount = (LinearLayout) getActivity().findViewById(R.id.layAccount);

        home = (AppCompatImageView) getActivity().findViewById(R.id.home);
        goal = (AppCompatImageView) getActivity().findViewById(R.id.goal);
        chart = (AppCompatImageView) getActivity().findViewById(R.id.chart);
        bot = (AppCompatImageView) getActivity().findViewById(R.id.bot);
        account = (AppCompatImageView) getActivity().findViewById(R.id.account);

        home.setImageResource(R.drawable.ic_home);
        layHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home.setImageResource(R.drawable.ic_home);
                goal.setImageResource(R.drawable.trophy);
                chart.setImageResource(R.drawable.chart);
                bot.setImageResource(R.drawable.bot);
                account.setImageResource(R.drawable.account);

                HomeActivity homeactivity = (HomeActivity) getActivity();
                HomeFragmentContent frag = new HomeFragmentContent();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("home_app");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });
        layDream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home.setImageResource(R.drawable.home);
                goal.setImageResource(R.drawable.ic_trophy_black);
                chart.setImageResource(R.drawable.chart);
                bot.setImageResource(R.drawable.bot);
                account.setImageResource(R.drawable.account);

                HomeActivity homeactivity = (HomeActivity) getActivity();
                ObjectiveFragment frag = new ObjectiveFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("goal_dream");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });
        layChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home.setImageResource(R.drawable.home);
                goal.setImageResource(R.drawable.trophy);
                chart.setImageResource(R.drawable.ic_chart);
                bot.setImageResource(R.drawable.bot);
                account.setImageResource(R.drawable.account);

                HomeActivity homeactivity = (HomeActivity) getActivity();
                ReportSelectFragment frag = new ReportSelectFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("chart_report");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });
        layBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home.setImageResource(R.drawable.home);
                goal.setImageResource(R.drawable.trophy);
                chart.setImageResource(R.drawable.chart);
                bot.setImageResource(R.drawable.ic_bot);
                account.setImageResource(R.drawable.account);

                HomeActivity homeactivity = (HomeActivity) getActivity();
                BotFragment frag = new BotFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("bot");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });
        layAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home.setImageResource(R.drawable.home);
                goal.setImageResource(R.drawable.trophy);
                chart.setImageResource(R.drawable.chart);
                bot.setImageResource(R.drawable.bot);
                account.setImageResource(R.drawable.ic_account);

                HomeActivity homeactivity = (HomeActivity) getActivity();
                ProfileFragment frag = new ProfileFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("account_profile");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });
    }
}
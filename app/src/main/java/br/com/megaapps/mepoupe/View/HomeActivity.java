package br.com.megaapps.mepoupe.View;

import android.content.Intent;
import android.os.Bundle;
import br.com.megaapps.mepoupe.Extendables.MyFragActivity;
import br.com.megaapps.mepoupe.R;
import br.com.megaapps.mepoupe.Util.ActivityResultBus;
import br.com.megaapps.mepoupe.Util.ActivityResultEvent;

public class HomeActivity extends MyFragActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeFragmentContent HomeContent = new HomeFragmentContent();
        HomeFragmentBottom HomeBottom = new HomeFragmentBottom();

        getMyFragTransaction().add(R.id.frContent, HomeContent);
        getMyFragTransaction().add(R.id.frBottomMenu, HomeBottom);
        getMyFragTransaction().commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultBus.getInstance().postQueue(
                new ActivityResultEvent(requestCode, resultCode, data));
    }
}

package br.com.megaapps.mepoupe.Util;

import android.view.View;

/**
 * Created by duh on 5/28/17.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
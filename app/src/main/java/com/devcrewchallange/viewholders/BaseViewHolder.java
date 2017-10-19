package com.devcrewchallange.viewholders;

import android.view.View;

import com.devcrewchallange.R;
import com.devcrewchallange.activities.BaseActivity;

/**
 * Created by asifj on 9/8/2016.
 */

public class BaseViewHolder
{
    private View layoutProgress = null;
    public BaseViewHolder(BaseActivity view)
    {

    }

    public BaseViewHolder(View view)
    {

    }
    public View getLayoutProgress() {
        return layoutProgress;
    }
}

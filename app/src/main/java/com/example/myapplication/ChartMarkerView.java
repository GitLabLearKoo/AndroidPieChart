package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class ChartMarkerView extends MarkerView {

    private TextView tvContent;
    private TextView tvFundType;

    public ChartMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        // find your layout components
        tvContent = findViewById(R.id.tvMarker);
        tvFundType = findViewById(R.id.tvFundType);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Log.d("marker", String.valueOf(e.getData()));
        tvContent.setText("test" + e.getData());
        tvFundType.setText("name of : " + e.getData());

        // this will perform necessary layouting
        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {

        if (mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-(getWidth() / 2) + (getWidth() / 4), -getHeight() + (-getHeight() / 2));
        }

        return mOffset;
    }
}

package com.example.myapplication

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.WindowManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import java.security.AccessController.getContext
import java.util.ArrayList

class MainActivity : AppCompatActivity(), OnChartValueSelectedListener {
    private var chart: PieChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        title = "PieChartActivity"


        chart = findViewById(R.id.pieChart)
        chart!!.setUsePercentValues(true)
        chart!!.description.isEnabled = false
        chart!!.setExtraOffsets(5f, 10f, 5f, 5f)

        chart!!.dragDecelerationFrictionCoef = 0.95f

        chart!!.centerText = generateCenterSpannableText()

        chart!!.isDrawHoleEnabled = true
        chart!!.setHoleColor(Color.TRANSPARENT)

        chart!!.setTransparentCircleColor(Color.WHITE)
        chart!!.setTransparentCircleAlpha(110)

        chart!!.holeRadius = 80f
        chart!!.transparentCircleRadius = 61f

        chart!!.setDrawCenterText(true)

        chart!!.rotationAngle = 0f
        // enable rotation of the chart by touch
        chart!!.isRotationEnabled = true
        chart!!.isHighlightPerTapEnabled = true

        // add a selection listener
        chart!!.setOnChartValueSelectedListener(this)

        chart!!.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);


        // entry label styling
        chart!!.setEntryLabelColor(Color.DKGRAY)
        chart!!.setEntryLabelTextSize(12f)
        chart!!.setUseCustomHighLightColor(true)
        setData(5, 5f)
        setChartLegend()
    }

    private fun setChartLegend() {
        val marker: IMarker = ChartMarkerView(applicationContext, R.layout.marker_layout)
        chart!!.marker = marker

        val l = chart!!.legend
        l.isEnabled = false
    }


    private fun setData(count: Int, range: Float) {
        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(PieEntry((Math.random() * range + range / 5).toFloat()))
        }

        val dataSet = PieDataSet(entries, "Election Results")

        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors

        val colors = ArrayList<Int>()

        colors.add(Color.WHITE)

        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(chart))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.DKGRAY)
        dataSet.setHighLightColor(Color.parseColor("#CFAF05"))

        dataSet.isHighlightEnabled = true

        chart!!.data = data

        // undo all highlights
        chart!!.highlightValues(null)
        val h = arrayOfNulls<Highlight>(1)
        h[0] = Highlight(0f, 0, 0)

        chart!!.highlightValue(h[0])

        chart!!.invalidate()
    }


    private fun generateCenterSpannableText(): SpannableString {

        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(ForegroundColorSpan(Color.WHITE), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }

    override fun onValueSelected(e: Entry?, h: Highlight) {

        if (e == null)
            return
        Log.i(
            "VAL SELECTED",
            "Value: " + e.y + ", index: " + h.x
                    + ", DataSet index: " + h.dataSetIndex
        )
    }

    override fun onNothingSelected() {
        Log.i("PieChart", "nothing selected")
    }

}

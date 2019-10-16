# Android PieChart
Custom [MPAndroidChrt](https://github.com/PhilJay/MPAndroidChart) Piechart example

Change the color of current selected item with custom highlight color.

<img src="https://raw.githubusercontent.com/Jaosrikate/AndroidPieChart/master/screen/Screen%20Shot%202562-10-16%20at%2014.30.22.png" width="250">

Features
1. Defalut highlight first value.
2. Custom Hightlight color
3. Custom Marker view

## Highlight first value
```
 chart!!.highlightValues(null)
 val h = arrayOfNulls<Highlight>(1)
 h[0] = Highlight(0f, 0, 0)

 chart!!.highlightValue(h[0])
 chart!!.invalidate()
```

## Custom highlight color
Need to custom [MPAndroidChrt](https://github.com/PhilJay/MPAndroidChart) lib

Because `com.github.PhilJay:MPAndroidChart:v3.1.0'` is not(yet) supported this feature :sob::sob::sob:

Reference : https://github.com/PhilJay/MPAndroidChart/pull/3777/files

Custom list:
- MPChartLib/src/main/java/com/github/mikephil/charting/charts/PieChart.java
- MPChartLib/src/main/java/com/github/mikephil/charting/data/PieDataSet.java
- MPChartLib/src/main/java/com/github/mikephil/charting/interfaces/datasets/IPieDataSet.java
- MPChartLib/src/main/java/com/github/mikephil/charting/renderer/PieChartRenderer.java

### Usage
```
 chart!!.setUseCustomHighLightColor(true)
 dataSet.setHighLightColor(Color.parseColor("#CFAF05"))
```

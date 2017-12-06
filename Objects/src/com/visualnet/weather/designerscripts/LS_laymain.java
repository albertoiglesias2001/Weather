package com.visualnet.weather.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_laymain{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 4;BA.debugLine="txtCity.SetLeftAndRight(10dip,imgAdd.Left)"[laymain/General script]
views.get("txtcity").vw.setLeft((int)((10d * scale)));
views.get("txtcity").vw.setWidth((int)((views.get("imgadd").vw.getLeft()) - ((10d * scale))));
//BA.debugLineNum = 7;BA.debugLine="lblInfo1.SetLeftAndRight(0,100%x)"[laymain/General script]
views.get("lblinfo1").vw.setLeft((int)(0d));
views.get("lblinfo1").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 8;BA.debugLine="imgLogo.HorizontalCenter = 50%x"[laymain/General script]
views.get("imglogo").vw.setLeft((int)((50d / 100 * width) - (views.get("imglogo").vw.getWidth() / 2)));
//BA.debugLineNum = 10;BA.debugLine="lstCities.SetLeftAndRight(0,100%x)"[laymain/General script]
views.get("lstcities").vw.setLeft((int)(0d));
views.get("lstcities").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 11;BA.debugLine="lstCities.SetTopAndBottom(txtCity.Bottom,lblInfo1.top)"[laymain/General script]
views.get("lstcities").vw.setTop((int)((views.get("txtcity").vw.getTop() + views.get("txtcity").vw.getHeight())));
views.get("lstcities").vw.setHeight((int)((views.get("lblinfo1").vw.getTop()) - ((views.get("txtcity").vw.getTop() + views.get("txtcity").vw.getHeight()))));
//BA.debugLineNum = 14;BA.debugLine="imgBack.SetLeftAndRight(0,100%x)"[laymain/General script]
views.get("imgback").vw.setLeft((int)(0d));
views.get("imgback").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 15;BA.debugLine="imgBack.SetTopAndBottom(0,100%y)"[laymain/General script]
views.get("imgback").vw.setTop((int)(0d));
views.get("imgback").vw.setHeight((int)((100d / 100 * height) - (0d)));

}
}
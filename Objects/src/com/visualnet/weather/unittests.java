package com.visualnet.weather;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class unittests {
private static unittests mostCurrent = new unittests();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public com.visualnet.weather.main _main = null;
public com.visualnet.weather.utility _utility = null;
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 9;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static boolean  _ut_checkconnectivity(anywheresoftware.b4a.BA _ba) throws Exception{
boolean _bret = false;
 //BA.debugLineNum = 35;BA.debugLine="Sub UT_CheckConnectivity As Boolean";
 //BA.debugLineNum = 37;BA.debugLine="Dim bRet As Boolean = True";
_bret = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 39;BA.debugLine="If Utility.GetAirplaneMode = True Then";
if (mostCurrent._utility._getairplanemode(_ba)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 40;BA.debugLine="bRet = False";
_bret = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 43;BA.debugLine="Return bRet";
if (true) return _bret;
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return false;
}
public static boolean  _ut_checkresponseweather(anywheresoftware.b4a.BA _ba,String _pvalue) throws Exception{
boolean _bret = false;
 //BA.debugLineNum = 87;BA.debugLine="Sub UT_CheckResponseWeather(pValue As String) As B";
 //BA.debugLineNum = 89;BA.debugLine="Dim bRet As Boolean = True";
_bret = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 91;BA.debugLine="If pValue.StartsWith(\"{\") = False Then";
if (_pvalue.startsWith("{")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 92;BA.debugLine="bRet = False";
_bret = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 95;BA.debugLine="Return bRet";
if (true) return _bret;
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return false;
}
public static boolean  _ut_citiesdb(anywheresoftware.b4a.BA _ba,int _pqty) throws Exception{
boolean _bret = false;
 //BA.debugLineNum = 47;BA.debugLine="Sub UT_CitiesDB(pQty As Int) As Boolean";
 //BA.debugLineNum = 49;BA.debugLine="Dim bRet As Boolean = True";
_bret = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 51;BA.debugLine="If pQty = 0 Then bRet = False";
if (_pqty==0) { 
_bret = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 53;BA.debugLine="Return bRet";
if (true) return _bret;
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return false;
}
public static boolean  _ut_cityname(anywheresoftware.b4a.BA _ba,String _pvalue) throws Exception{
boolean _bret = false;
 //BA.debugLineNum = 13;BA.debugLine="Sub UT_CityName(pValue As String) As Boolean";
 //BA.debugLineNum = 15;BA.debugLine="Dim bRet As Boolean = True";
_bret = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 17;BA.debugLine="If pValue.Length = 0 Then bRet = False";
if (_pvalue.length()==0) { 
_bret = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 18;BA.debugLine="If pValue.Length > 100 Then bRet = False";
if (_pvalue.length()>100) { 
_bret = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 20;BA.debugLine="Return bRet";
if (true) return _bret;
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return false;
}
public static boolean  _ut_qtycities(anywheresoftware.b4a.BA _ba,int _pqtde) throws Exception{
boolean _bret = false;
 //BA.debugLineNum = 24;BA.debugLine="Sub UT_QtyCities(pQtde As Int) As Boolean";
 //BA.debugLineNum = 26;BA.debugLine="Dim bRet As Boolean = True";
_bret = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 28;BA.debugLine="If pQtde = 0 Then bRet = False";
if (_pqtde==0) { 
_bret = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 29;BA.debugLine="If pQtde > 10 Then bRet = False";
if (_pqtde>10) { 
_bret = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 31;BA.debugLine="Return bRet";
if (true) return _bret;
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return false;
}
public static boolean  _ut_weatherprec(anywheresoftware.b4a.BA _ba,String _pvalue) throws Exception{
boolean _bret = false;
 //BA.debugLineNum = 77;BA.debugLine="Sub UT_WeatherPrec(pValue As String) As Boolean";
 //BA.debugLineNum = 79;BA.debugLine="Dim bRet As Boolean = True";
_bret = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 81;BA.debugLine="If pValue.trim = \"\" Then bRet = False";
if ((_pvalue.trim()).equals("")) { 
_bret = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 83;BA.debugLine="Return bRet";
if (true) return _bret;
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return false;
}
public static boolean  _ut_weathertemperature(anywheresoftware.b4a.BA _ba,String _pvalue) throws Exception{
boolean _bret = false;
 //BA.debugLineNum = 57;BA.debugLine="Sub UT_WeatherTemperature(pValue As String) As Boo";
 //BA.debugLineNum = 59;BA.debugLine="Dim bRet As Boolean = True";
_bret = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 61;BA.debugLine="If pValue.trim = \"\" Then bRet = False";
if ((_pvalue.trim()).equals("")) { 
_bret = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 63;BA.debugLine="Return bRet";
if (true) return _bret;
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return false;
}
public static boolean  _ut_weatherwind(anywheresoftware.b4a.BA _ba,String _pvalue) throws Exception{
boolean _bret = false;
 //BA.debugLineNum = 67;BA.debugLine="Sub UT_WeatherWind(pValue As String) As Boolean";
 //BA.debugLineNum = 69;BA.debugLine="Dim bRet As Boolean = True";
_bret = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 71;BA.debugLine="If pValue.trim = \"\" Then bRet = False";
if ((_pvalue.trim()).equals("")) { 
_bret = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 73;BA.debugLine="Return bRet";
if (true) return _bret;
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return false;
}
}

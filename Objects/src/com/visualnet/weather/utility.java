package com.visualnet.weather;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class utility {
private static utility mostCurrent = new utility();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public com.visualnet.weather.main _main = null;
public com.visualnet.weather.unittests _unittests = null;
public static String  _fu_config(anywheresoftware.b4a.BA _ba,String _chave,String _valor) throws Exception{
String _retman = "";
anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager _manager = null;
 //BA.debugLineNum = 15;BA.debugLine="Sub FU_Config(Chave As String,Valor As String) As";
 //BA.debugLineNum = 17;BA.debugLine="Dim retMan As String = \"\"";
_retman = "";
 //BA.debugLineNum = 19;BA.debugLine="Dim manager As PreferenceManager";
_manager = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager();
 //BA.debugLineNum = 20;BA.debugLine="If manager.GetAll.Size = 0 Then";
if (_manager.GetAll().getSize()==0) { 
 //BA.debugLineNum = 21;BA.debugLine="manager.SetString(\"Autor\", \"Alberto Iglesias (";
_manager.SetString("Autor","Alberto Iglesias (alberto@visualnet.inf.br)");
 };
 //BA.debugLineNum = 24;BA.debugLine="If Valor = \"null\" Then";
if ((_valor).equals("null")) { 
 //BA.debugLineNum = 25;BA.debugLine="retMan = \"\" & manager.GetString(Chave).Trim";
_retman = ""+_manager.GetString(_chave).trim();
 //BA.debugLineNum = 26;BA.debugLine="If retMan = \"null\" Then retMan = \"\"";
if ((_retman).equals("null")) { 
_retman = "";};
 }else {
 //BA.debugLineNum = 28;BA.debugLine="manager.SetString(Chave,Valor)";
_manager.SetString(_chave,_valor);
 //BA.debugLineNum = 29;BA.debugLine="retMan = Valor";
_retman = _valor;
 };
 //BA.debugLineNum = 32;BA.debugLine="Return retMan";
if (true) return _retman;
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _fu_parametros(anywheresoftware.b4a.BA _ba,String _ptexto,int _ipos,String _strcarac) throws Exception{
String[] _campos = null;
 //BA.debugLineNum = 36;BA.debugLine="Sub FU_Parametros(pTexto As String,iPos As Int,str";
 //BA.debugLineNum = 38;BA.debugLine="Dim Campos() As String";
_campos = new String[(int) (0)];
java.util.Arrays.fill(_campos,"");
 //BA.debugLineNum = 40;BA.debugLine="If strCarac = \"|\" Then strCarac = \"\\|\"";
if ((_strcarac).equals("|")) { 
_strcarac = "\\|";};
 //BA.debugLineNum = 42;BA.debugLine="Campos = Regex.Split(strCarac,  pTexto)";
_campos = anywheresoftware.b4a.keywords.Common.Regex.Split(_strcarac,_ptexto);
 //BA.debugLineNum = 46;BA.debugLine="If Campos.Length >= iPos Then";
if (_campos.length>=_ipos) { 
 //BA.debugLineNum = 47;BA.debugLine="Return Campos(iPos-1)";
if (true) return _campos[(int) (_ipos-1)];
 }else {
 //BA.debugLineNum = 49;BA.debugLine="Return \"\"";
if (true) return "";
 };
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static boolean  _getairplanemode(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
 //BA.debugLineNum = 91;BA.debugLine="Sub GetAirplaneMode As Boolean";
 //BA.debugLineNum = 93;BA.debugLine="Try";
try { //BA.debugLineNum = 94;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 95;BA.debugLine="Return p.GetSettings(\"airplane_mode_on\") = 1";
if (true) return (_p.GetSettings("airplane_mode_on")).equals(BA.NumberToString(1));
 } 
       catch (Exception e5) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e5); //BA.debugLineNum = 97;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return false;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public static String  _setairplanemode(anywheresoftware.b4a.BA _ba,boolean _on) throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _contentresolver = null;
int _state = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 54;BA.debugLine="Sub SetAirplaneMode(On As Boolean)  'ignore";
 //BA.debugLineNum = 56;BA.debugLine="Try";
try { //BA.debugLineNum = 58;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 59;BA.debugLine="If On = GetAirplaneMode Then Return 'already in";
if (_on==_getairplanemode(_ba)) { 
if (true) return "";};
 //BA.debugLineNum = 60;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 61;BA.debugLine="Dim ContentResolver As Object";
_contentresolver = new Object();
 //BA.debugLineNum = 62;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext((_ba.processBA == null ? _ba : _ba.processBA)));
 //BA.debugLineNum = 63;BA.debugLine="ContentResolver = r.RunMethod(\"getContentResolve";
_contentresolver = _r.RunMethod("getContentResolver");
 //BA.debugLineNum = 64;BA.debugLine="Dim state As Int";
_state = 0;
 //BA.debugLineNum = 65;BA.debugLine="If On Then state = 1 Else state = 0";
if (_on) { 
_state = (int) (1);}
else {
_state = (int) (0);};
 //BA.debugLineNum = 67;BA.debugLine="r.RunStaticMethod(\"android.provider.Settings$Sys";
_r.RunStaticMethod("android.provider.Settings$System","putInt",new Object[]{_contentresolver,(Object)("airplane_mode_on"),(Object)(_state)},new String[]{"android.content.ContentResolver","java.lang.String","java.lang.int"});
 //BA.debugLineNum = 70;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 71;BA.debugLine="i.Initialize(\"android.intent.action.AIRPLANE_MOD";
_i.Initialize("android.intent.action.AIRPLANE_MODE","");
 //BA.debugLineNum = 72;BA.debugLine="i.PutExtra(\"state\", \"\" & On)";
_i.PutExtra("state",(Object)(""+BA.ObjectToString(_on)));
 //BA.debugLineNum = 73;BA.debugLine="p.SendBroadcastIntent(i)";
_p.SendBroadcastIntent((android.content.Intent)(_i.getObject()));
 } 
       catch (Exception e16) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e16); //BA.debugLineNum = 77;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage());
 };
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _su_dismisskeyboard(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.IME _keyboard = null;
 //BA.debugLineNum = 7;BA.debugLine="Sub SU_DismissKeyboard";
 //BA.debugLineNum = 9;BA.debugLine="Dim Keyboard As IME";
_keyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 10;BA.debugLine="Keyboard.Initialize(\"Keyboard\")";
_keyboard.Initialize("Keyboard");
 //BA.debugLineNum = 11;BA.debugLine="Keyboard.HideKeyboard";
_keyboard.HideKeyboard(_ba);
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _su_verificaairplane(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Sub SU_VerificaAirplane";
 //BA.debugLineNum = 85;BA.debugLine="If GetAirplaneMode = True Then";
if (_getairplanemode(_ba)==anywheresoftware.b4a.keywords.Common.True) { 
 };
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
}

package com.visualnet.weather;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.visualnet.weather", "com.visualnet.weather.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, true))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.visualnet.weather", "com.visualnet.weather.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.visualnet.weather.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static String _strapikey = "";
public anywheresoftware.b4a.objects.EditTextWrapper _txtcity = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lstcities = null;
public anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager _objmanager = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnldetail = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblwind = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblprecipitation = null;
public static String _strcitydata = "";
public anywheresoftware.b4a.objects.LabelWrapper _lblinfo1 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public com.visualnet.weather.utility _utility = null;
public com.visualnet.weather.unittests _unittests = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 32;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"layMain\")";
mostCurrent._activity.LoadLayout("layMain",mostCurrent.activityBA);
 //BA.debugLineNum = 35;BA.debugLine="pnlDetail.Left = 110%x";
mostCurrent._pnldetail.setLeft(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (110),mostCurrent.activityBA));
 //BA.debugLineNum = 36;BA.debugLine="pnlDetail.Top = 10dip";
mostCurrent._pnldetail.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 37;BA.debugLine="pnlDetail.Visible = False";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 39;BA.debugLine="ToastMessageShow(\"Created by Alberto Iglesias - 0";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Created by Alberto Iglesias - 06/12/2017"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 44;BA.debugLine="If Utility.GetAirplaneMode = True Then";
if (mostCurrent._utility._getairplanemode(mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 45;BA.debugLine="Msgbox(\"Check your Internet before use this app!";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Check your Internet before use this app!"),BA.ObjectToCharSequence("Attention"),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 47;BA.debugLine="SU_LoadCities";
_su_loadcities();
 };
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Dim txtCity As EditText";
mostCurrent._txtcity = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Dim lstCities As ListView";
mostCurrent._lstcities = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim objManager As PreferenceManager";
mostCurrent._objmanager = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager();
 //BA.debugLineNum = 25;BA.debugLine="Private pnlDetail As Panel";
mostCurrent._pnldetail = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private lblWind As Label";
mostCurrent._lblwind = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lblPrecipitation As Label";
mostCurrent._lblprecipitation = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim strCityData As String";
mostCurrent._strcitydata = "";
 //BA.debugLineNum = 29;BA.debugLine="Private lblInfo1 As Label";
mostCurrent._lblinfo1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public static String  _imgadd_click() throws Exception{
 //BA.debugLineNum = 130;BA.debugLine="Sub imgAdd_Click";
 //BA.debugLineNum = 132;BA.debugLine="If txtCity.Text.Trim = \"\" Then";
if ((mostCurrent._txtcity.getText().trim()).equals("")) { 
 //BA.debugLineNum = 133;BA.debugLine="Msgbox(\"Type the city, please!\",\"Attention\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Type the city, please!"),BA.ObjectToCharSequence("Attention"),mostCurrent.activityBA);
 //BA.debugLineNum = 134;BA.debugLine="txtCity.RequestFocus";
mostCurrent._txtcity.RequestFocus();
 //BA.debugLineNum = 135;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 138;BA.debugLine="SU_DownloadWeather(txtCity.Text.Trim)";
_su_downloadweather(mostCurrent._txtcity.getText().trim());
 //BA.debugLineNum = 140;BA.debugLine="lstCities.RequestFocus";
mostCurrent._lstcities.RequestFocus();
 //BA.debugLineNum = 141;BA.debugLine="Utility.SU_DismissKeyboard";
mostCurrent._utility._su_dismisskeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
public static String  _imgclose_click() throws Exception{
 //BA.debugLineNum = 168;BA.debugLine="Sub imgClose_Click";
 //BA.debugLineNum = 169;BA.debugLine="pnlDetail.Visible = False";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 170;BA.debugLine="pnlDetail.Top = 10dip";
mostCurrent._pnldetail.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 171;BA.debugLine="pnlDetail.Left = 110%x";
mostCurrent._pnldetail.setLeft(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (110),mostCurrent.activityBA));
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _imgdelete_click() throws Exception{
String _strid = "";
 //BA.debugLineNum = 174;BA.debugLine="Sub imgDelete_Click";
 //BA.debugLineNum = 176;BA.debugLine="Dim strID As String = Utility.FU_Parametros(strCi";
_strid = mostCurrent._utility._fu_parametros(mostCurrent.activityBA,mostCurrent._strcitydata,(int) (1),"|");
 //BA.debugLineNum = 177;BA.debugLine="If Msgbox2(\"Delete this city?\",\"Confirm\" ,\"OK\",\"\"";
if (anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Delete this city?"),BA.ObjectToCharSequence("Confirm"),"OK","","Cancel",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 178;BA.debugLine="Utility.FU_Config(\"City_\" & strID,\"\")";
mostCurrent._utility._fu_config(mostCurrent.activityBA,"City_"+_strid,"");
 //BA.debugLineNum = 179;BA.debugLine="ToastMessageShow(\"City Deleted!\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("City Deleted!"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 180;BA.debugLine="imgClose_Click";
_imgclose_click();
 //BA.debugLineNum = 181;BA.debugLine="SU_LoadCities";
_su_loadcities();
 };
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
 //BA.debugLineNum = 87;BA.debugLine="Sub JobDone (Job As HttpJob)";
 //BA.debugLineNum = 89;BA.debugLine="If Job.Success = True Then";
if (_job._success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 90;BA.debugLine="LogColor(Job.GetString,Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogColor(_job._getstring(),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 91;BA.debugLine="ParserWeather(Job.GetString)";
_parserweather(_job._getstring());
 //BA.debugLineNum = 92;BA.debugLine="txtCity.Text = \"\"";
mostCurrent._txtcity.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 93;BA.debugLine="Msgbox(\"The city was added with success!\",\"Atten";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("The city was added with success!"),BA.ObjectToCharSequence("Attention"),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 95;BA.debugLine="Msgbox(\"Check the name of City and try again, pl";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Check the name of City and try again, please!"),BA.ObjectToCharSequence("Attention"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _lstcities_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 156;BA.debugLine="Sub lstCities_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 158;BA.debugLine="strCityData = Value";
mostCurrent._strcitydata = BA.ObjectToString(_value);
 //BA.debugLineNum = 159;BA.debugLine="lblWind.Text = Utility.FU_Parametros(strCityData,";
mostCurrent._lblwind.setText(BA.ObjectToCharSequence(mostCurrent._utility._fu_parametros(mostCurrent.activityBA,mostCurrent._strcitydata,(int) (4),"|")+" meter/sec"));
 //BA.debugLineNum = 160;BA.debugLine="lblPrecipitation.Text = Utility.FU_Parametros(str";
mostCurrent._lblprecipitation.setText(BA.ObjectToCharSequence(mostCurrent._utility._fu_parametros(mostCurrent.activityBA,mostCurrent._strcitydata,(int) (5),"|")+" %"));
 //BA.debugLineNum = 161;BA.debugLine="pnlDetail.Left = 110%x";
mostCurrent._pnldetail.setLeft(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (110),mostCurrent.activityBA));
 //BA.debugLineNum = 162;BA.debugLine="pnlDetail.Visible = True";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 163;BA.debugLine="pnlDetail.SetLayoutAnimated(1000,(100%x/2)-(pnlDe";
mostCurrent._pnldetail.SetLayoutAnimated((int) (1000),(int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)/(double)2)-(mostCurrent._pnldetail.getWidth()/(double)2)),(int) ((anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)/(double)2)-(mostCurrent._pnldetail.getHeight()/(double)2)),mostCurrent._pnldetail.getWidth(),mostCurrent._pnldetail.getHeight());
 //BA.debugLineNum = 164;BA.debugLine="strCityData = Value";
mostCurrent._strcitydata = BA.ObjectToString(_value);
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public static String  _parserweather(String _jsontext) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
String _strcity = "";
String _strid = "";
anywheresoftware.b4a.objects.collections.List _weather = null;
anywheresoftware.b4a.objects.collections.Map _colweather = null;
anywheresoftware.b4a.objects.collections.Map _pwind = null;
double _pwindspeed = 0;
anywheresoftware.b4a.objects.collections.Map _maintemp = null;
String _strtemp = "";
String _strhumidity = "";
 //BA.debugLineNum = 100;BA.debugLine="Sub ParserWeather(jsontext As String)  'ignore";
 //BA.debugLineNum = 102;BA.debugLine="Dim Parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 103;BA.debugLine="Parser.Initialize(jsontext)";
_parser.Initialize(_jsontext);
 //BA.debugLineNum = 104;BA.debugLine="Dim root As Map = Parser.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _parser.NextObject();
 //BA.debugLineNum = 106;BA.debugLine="Dim strCity As String = root.Get(\"name\")";
_strcity = BA.ObjectToString(_root.Get((Object)("name")));
 //BA.debugLineNum = 107;BA.debugLine="Dim strID As String = root.Get(\"id\")";
_strid = BA.ObjectToString(_root.Get((Object)("id")));
 //BA.debugLineNum = 109;BA.debugLine="Dim weather As List = root.Get(\"weather\")";
_weather = new anywheresoftware.b4a.objects.collections.List();
_weather.setObject((java.util.List)(_root.Get((Object)("weather"))));
 //BA.debugLineNum = 110;BA.debugLine="For Each colweather As Map In weather";
_colweather = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group7 = _weather;
final int groupLen7 = group7.getSize()
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_colweather.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group7.Get(index7)));
 //BA.debugLineNum = 112;BA.debugLine="Dim pWind As Map = root.Get(\"wind\")";
_pwind = new anywheresoftware.b4a.objects.collections.Map();
_pwind.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_root.Get((Object)("wind"))));
 //BA.debugLineNum = 113;BA.debugLine="Dim pWindSpeed As Double = pWind.Get(\"speed\")";
_pwindspeed = (double)(BA.ObjectToNumber(_pwind.Get((Object)("speed"))));
 //BA.debugLineNum = 115;BA.debugLine="Dim mainTemp As Map = root.Get(\"main\")";
_maintemp = new anywheresoftware.b4a.objects.collections.Map();
_maintemp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_root.Get((Object)("main"))));
 //BA.debugLineNum = 116;BA.debugLine="Dim strTemp As String = mainTemp.Get(\"temp\")";
_strtemp = BA.ObjectToString(_maintemp.Get((Object)("temp")));
 //BA.debugLineNum = 117;BA.debugLine="Dim strHumidity As String = mainTemp.Get(\"humidi";
_strhumidity = BA.ObjectToString(_maintemp.Get((Object)("humidity")));
 //BA.debugLineNum = 118;BA.debugLine="SU_AddCity(strID,strCity, strTemp,pWindSpeed,str";
_su_addcity(_strid,_strcity,_strtemp,BA.NumberToString(_pwindspeed),_strhumidity);
 }
};
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
main._process_globals();
utility._process_globals();
unittests._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim strAPIKey As String = \"9921411dc2aec627366289";
_strapikey = "9921411dc2aec6273662892819bfd890";
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _su_addcity(String _pid,String _pcity,String _ptemp,String _pwind,String _phumidity) throws Exception{
 //BA.debugLineNum = 123;BA.debugLine="Sub SU_AddCity(pID As String , pCity As String, pT";
 //BA.debugLineNum = 125;BA.debugLine="Utility.FU_Config(\"City_\" & pID,pCity & \"|\" & pTe";
mostCurrent._utility._fu_config(mostCurrent.activityBA,"City_"+_pid,_pcity+"|"+_ptemp+"|"+_pwind+"|"+_phumidity);
 //BA.debugLineNum = 126;BA.debugLine="SU_LoadCities";
_su_loadcities();
 //BA.debugLineNum = 128;BA.debugLine="End Sub";
return "";
}
public static String  _su_downloadweather(String _pcity) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
String _strsearch = "";
anywheresoftware.b4a.samples.httputils2.httpjob _job = null;
 //BA.debugLineNum = 145;BA.debugLine="Sub SU_DownloadWeather(pCity As String)";
 //BA.debugLineNum = 147;BA.debugLine="Dim SU As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 148;BA.debugLine="Dim strSearch As String = SU.EncodeUrl(pCity, \"UT";
_strsearch = _su.EncodeUrl(_pcity,"UTF8");
 //BA.debugLineNum = 150;BA.debugLine="Dim job As HttpJob";
_job = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 151;BA.debugLine="job.Initialize(\"WE\",Me)";
_job._initialize(processBA,"WE",main.getObject());
 //BA.debugLineNum = 152;BA.debugLine="job.Download(\"http://api.openweathermap.org/data/";
_job._download("http://api.openweathermap.org/data/2.5/weather?q="+_strsearch+"&units=metric&appid="+_strapikey);
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return "";
}
public static String  _su_loadcities() throws Exception{
anywheresoftware.b4a.objects.collections.Map _mapcities = null;
String _strcity = "";
String _strcityvalue = "";
String _strvalue = "";
String _strname = "";
String _strtemperature = "";
String _strid = "";
String _strdata = "";
String _strwind = "";
String _strhum = "";
int _i = 0;
 //BA.debugLineNum = 55;BA.debugLine="Sub SU_LoadCities";
 //BA.debugLineNum = 57;BA.debugLine="Dim mapCities As Map = objManager.GetAll";
_mapcities = new anywheresoftware.b4a.objects.collections.Map();
_mapcities = mostCurrent._objmanager.GetAll();
 //BA.debugLineNum = 58;BA.debugLine="Dim strCity, strCityValue, strValue, strName, str";
_strcity = "";
_strcityvalue = "";
_strvalue = "";
_strname = "";
_strtemperature = "";
 //BA.debugLineNum = 59;BA.debugLine="Dim strID, strData, strWind, strHum As String";
_strid = "";
_strdata = "";
_strwind = "";
_strhum = "";
 //BA.debugLineNum = 61;BA.debugLine="lstCities.Clear";
mostCurrent._lstcities.Clear();
 //BA.debugLineNum = 62;BA.debugLine="lstCities.TwoLinesAndBitmap.SecondLabel.TextColor";
mostCurrent._lstcities.getTwoLinesAndBitmap().SecondLabel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 63;BA.debugLine="lstCities.TwoLinesAndBitmap.SecondLabel.TextSize";
mostCurrent._lstcities.getTwoLinesAndBitmap().SecondLabel.setTextSize((float) (16));
 //BA.debugLineNum = 65;BA.debugLine="For i=0 To mapCities.Size-1";
{
final int step7 = 1;
final int limit7 = (int) (_mapcities.getSize()-1);
_i = (int) (0) ;
for (;(step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7) ;_i = ((int)(0 + _i + step7))  ) {
 //BA.debugLineNum = 66;BA.debugLine="strValue = mapCities.GetKeyAt(i)";
_strvalue = BA.ObjectToString(_mapcities.GetKeyAt(_i));
 //BA.debugLineNum = 67;BA.debugLine="strCity = Utility.FU_Parametros(strValue,1,\"_\")";
_strcity = mostCurrent._utility._fu_parametros(mostCurrent.activityBA,_strvalue,(int) (1),"_");
 //BA.debugLineNum = 69;BA.debugLine="If strCity = \"City\" Then";
if ((_strcity).equals("City")) { 
 //BA.debugLineNum = 70;BA.debugLine="strID = Utility.FU_Parametros(strValue,2,\"_\")";
_strid = mostCurrent._utility._fu_parametros(mostCurrent.activityBA,_strvalue,(int) (2),"_");
 //BA.debugLineNum = 71;BA.debugLine="strCityValue = mapCities.GetValueAt(i)";
_strcityvalue = BA.ObjectToString(_mapcities.GetValueAt(_i));
 //BA.debugLineNum = 72;BA.debugLine="strName = Utility.FU_Parametros(strCityValue,";
_strname = mostCurrent._utility._fu_parametros(mostCurrent.activityBA,_strcityvalue,(int) (1),"|");
 //BA.debugLineNum = 73;BA.debugLine="strTemperature = Utility.FU_Parametros(strCit";
_strtemperature = mostCurrent._utility._fu_parametros(mostCurrent.activityBA,_strcityvalue,(int) (2),"|")+" °C";
 //BA.debugLineNum = 74;BA.debugLine="strWind= Utility.FU_Parametros(strCityValue,3";
_strwind = mostCurrent._utility._fu_parametros(mostCurrent.activityBA,_strcityvalue,(int) (3),"|");
 //BA.debugLineNum = 75;BA.debugLine="strHum = Utility.FU_Parametros(strCityValue,4";
_strhum = mostCurrent._utility._fu_parametros(mostCurrent.activityBA,_strcityvalue,(int) (4),"|");
 //BA.debugLineNum = 76;BA.debugLine="strData = strID & \"|\" & strName & \"|\" & strTe";
_strdata = _strid+"|"+_strname+"|"+_strtemperature+"|"+_strwind+"|"+_strhum;
 //BA.debugLineNum = 78;BA.debugLine="If strName<> \"\" Then";
if ((_strname).equals("") == false) { 
 //BA.debugLineNum = 79;BA.debugLine="lstCities.AddTwoLinesAndBitmap2(strName,strTem";
mostCurrent._lstcities.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_strname),BA.ObjectToCharSequence(_strtemperature),(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icons8-partly-cloudy-day-40.png").getObject()),(Object)(_strdata));
 };
 };
 }
};
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return "";
}
}

Type=StaticCode
Version=7.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@

'##########################################################################
'# UNIT TESTS 
'# Created by Alberto Iglesias
'# App - Weather for Android
'# 06/12/2017
'##########################################################################

Sub Process_Globals

End Sub

Sub UT_CityName(pValue As String) As Boolean
	
	Dim bRet As Boolean = True  
	
	If pValue.Length = 0 Then bRet = False	
	If pValue.Length > 100 Then bRet = False

	Return bRet
	
End Sub

Sub UT_QtyCities(pQtde As Int) As Boolean
	
	Dim bRet As Boolean = True  
	
	If pQtde = 0 Then bRet = False
	If pQtde > 10 Then bRet = False

	Return bRet
	
End Sub

Sub UT_CheckConnectivity As Boolean
	
	Dim bRet As Boolean = True  
	
	If Utility.GetAirplaneMode = True Then
		bRet = False
	End If

	Return bRet
	
End Sub

Sub UT_CitiesDB(pQty As Int) As Boolean
	
	Dim bRet As Boolean = True  
	
	If pQty = 0 Then bRet = False 

	Return bRet
	
End Sub

Sub UT_WeatherTemperature(pValue As String) As Boolean
	
	Dim bRet As Boolean = True  
	
	If pValue.trim = "" Then bRet = False

	Return bRet
	
End Sub

Sub UT_WeatherWind(pValue As String) As Boolean
	
	Dim bRet As Boolean = True  
	
	If pValue.trim = "" Then bRet = False

	Return bRet
	
End Sub

Sub UT_WeatherPrec(pValue As String) As Boolean
	
	Dim bRet As Boolean = True  
	
	If pValue.trim = "" Then bRet = False

	Return bRet
	
End Sub

Sub UT_CheckResponseWeather(pValue As String) As Boolean
	
	Dim bRet As Boolean = True  
	
	If pValue.StartsWith("{") = False Then
		bRet = False 
	End If

	Return bRet
	
End Sub
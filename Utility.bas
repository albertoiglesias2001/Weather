Type=StaticCode
Version=7.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@

Sub Process_Globals


End Sub

Sub SU_DismissKeyboard
	
	Dim Keyboard As IME
	Keyboard.Initialize("Keyboard")
	Keyboard.HideKeyboard
 
End Sub

Sub FU_Config(Chave As String,Valor As String) As String

	Dim retMan As String = ""
	
	Dim manager As PreferenceManager
	If manager.GetAll.Size = 0 Then
	   manager.SetString("Autor", "Alberto Iglesias (alberto@visualnet.inf.br)")
	End If
	
	If Valor = "null" Then
		retMan = "" & manager.GetString(Chave).Trim
		If retMan = "null" Then retMan = ""
	Else
		manager.SetString(Chave,Valor)
		retMan = Valor
	End If
	
	Return retMan
	
End Sub

Sub FU_Parametros(pTexto As String,iPos As Int,strCarac As String) As String

	Dim Campos() As String
	
	If strCarac = "|" Then strCarac = "\|"
	
	Campos = Regex.Split(strCarac,  pTexto)
	   
	'Log("Campos.Length=" & Campos.Length)
	
	If Campos.Length >= iPos Then
		Return Campos(iPos-1)
	Else
		Return ""
	End If

End Sub

Sub SetAirplaneMode(On As Boolean)  'ignore

	Try
	
		Dim p As Phone
		If On = GetAirplaneMode Then Return 'already in the correct state
		Dim r As Reflector
		Dim ContentResolver As Object
		r.Target = r.GetContext
		ContentResolver = r.RunMethod("getContentResolver")
		Dim state As Int
		If On Then state = 1 Else state = 0
		
		r.RunStaticMethod("android.provider.Settings$System", "putInt", _
		Array As Object(ContentResolver, "airplane_mode_on", state), _        
		Array As String("android.content.ContentResolver", "java.lang.String", "java.lang.int"))    
		Dim i As Intent
		i.Initialize("android.intent.action.AIRPLANE_MODE", "")
		i.PutExtra("state", "" & On)
		p.SendBroadcastIntent(i)
	
	Catch
	
		 Log(LastException.Message)
		
	End Try
	
End Sub

Sub SU_VerificaAirplane
	
	If GetAirplaneMode = True Then
		'Msgbox("Em modo avião, o aplicativo não funcionará corretamente!", "Atenção")
		'ExitApplication
	End If
	
End Sub
Sub GetAirplaneMode As Boolean

	Try
		Dim p As Phone
		Return p.GetSettings("airplane_mode_on") = 1
	Catch
		Return False
	End Try

End Sub
package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems._
import com.simplesys.SmartClient.Foundation.AbstractCanvasCompanion
import com.simplesys.SmartClient.Layout.Window
import com.simplesys.System.JSObject
import com.simplesys.System.Types.AutoChild

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait LoginDialog extends Window {

    import com.simplesys.SmartClient.System.IscArray

    val allowBlankPassword: Boolean
    val dismissable: Boolean
    val errorMessage: String
    val errorStyle: String
    val formFields: IscArray[FormItem]
    val loginButton: IButton with AutoChild
    val loginButtonTitle: String
    val loginFailureItem: BlurbItem with AutoChild
    val loginForm: DynamicForm with AutoChild
    var loginFunc: js.Function2[JSObject, js.Function1[Boolean, _], _]
    var lostPassword: js.Function2[JSObject, DynamicForm, _]
    val lostPasswordItem: LinkItem with AutoChild
    val lostPasswordItemTitle: String
    val passwordItem: PasswordItem with AutoChild
    val passwordItemTitle: String
    var register: js.Function2[JSObject, DynamicForm, _]
    val registrationItem: LinkItem with AutoChild
    val registrationItemTitle: String
    val showLostPasswordLink: Boolean
    val showRegistrationLink: Boolean
    val usernameItem: TextItem with AutoChild
    val usernameItemTitle: String
}

@js.native
abstract trait AbstractLoginDialogCompanion extends AbstractCanvasCompanion {
    var LOGIN_BUTTON_TITLE: String = js.native
    var LOGIN_ERROR_MESSAGE: String = js.native
    var LOGIN_TITLE: String = js.native
    var PASSWORD_TITLE: String = js.native
    var USERNAME_TITLE: String = js.native
}

@js.native
@JSName("LoginDialog")
object LoginDialogStatic extends AbstractLoginDialogCompanion


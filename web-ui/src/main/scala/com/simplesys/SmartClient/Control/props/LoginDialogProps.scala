package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems._
import com.simplesys.SmartClient.Layout.props.WindowProps
import com.simplesys.System.JSObject
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class LoginDialogProps extends WindowProps {
    var allowBlankPassword: ScOption[Boolean] = ScNone
    var dismissable: ScOption[Boolean] = ScNone
    var errorMessage: ScOption[String] = ScNone
    var errorStyle: ScOption[String] = ScNone
    var formFields: ScOption[Seq[FormItem]] = ScNone
    var loginButton: ScOption[IButton] = ScNone
    var loginButtonTitle: ScOption[String] = ScNone
    var loginFailureItem: ScOption[BlurbItem] = ScNone
    var loginForm: ScOption[DynamicForm] = ScNone
    var loginFunc: ScOption[js.Function2[JSObject, js.Function1[Boolean, _], _]] = ScNone
    var lostPassword: ScOption[js.Function2[JSObject, DynamicForm, _]] = ScNone
    var lostPasswordItem: ScOption[LinkItem] = ScNone
    var lostPasswordItemTitle: ScOption[String] = ScNone
    var passwordItem: ScOption[PasswordItem] = ScNone
    var passwordItemTitle: ScOption[String] = ScNone
    var register: ScOption[js.Function2[JSObject, DynamicForm, _]] = ScNone
    var registrationItem: ScOption[LinkItem] = ScNone
    var registrationItemTitle: ScOption[String] = ScNone
    var showLostPasswordLink: ScOption[Boolean] = ScNone
    var showRegistrationLink: ScOption[Boolean] = ScNone
    var usernameItem: ScOption[TextItem] = ScNone
    var usernameItemTitle: ScOption[String] = ScNone
}

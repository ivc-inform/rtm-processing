package com.simplesys.SmartClient.Forms

import com.simplesys.System.Types.Criteria

import scala.scalajs.js

@js.native
trait SearchForm extends DynamicForm {
    var search:js.Function2[Criteria, SearchForm, _]
    var showFilterFieldsOnly:Boolean
}



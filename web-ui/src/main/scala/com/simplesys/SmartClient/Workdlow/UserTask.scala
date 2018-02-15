package com.simplesys.SmartClient.Workdlow

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait UserTask extends Task {
}

@js.native
abstract trait AbstractUserTaskCompanion extends AbstractTaskCompanion {
}

@js.native
   object UserTask extends AbstractUserTaskCompanion


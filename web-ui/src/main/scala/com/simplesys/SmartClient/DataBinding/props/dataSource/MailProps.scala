package com.simplesys.SmartClient.DataBinding.props.dataSource

import com.simplesys.System.Types.{URL, VelocityExpression}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class MailProps extends AbstractClassProps {
    var bcc: ScOption[String] = ScNone
    var cc: ScOption[String] = ScNone
    var contentType: ScOption[String] = ScNone
    var encoding: ScOption[String] = ScNone
    var from: ScOption[String] = ScNone
    var messageData: ScOption[VelocityExpression] = ScNone
    var messageTemplate: ScOption[String] = ScNone
    var multiple: ScOption[String] = ScNone
    var replyTo: ScOption[String] = ScNone
    var subject: ScOption[String] = ScNone
    var templateFile: ScOption[URL] = ScNone
    var to: ScOption[String] = ScNone
}

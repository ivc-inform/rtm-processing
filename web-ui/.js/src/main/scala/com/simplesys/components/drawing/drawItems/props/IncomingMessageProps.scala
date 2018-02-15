package com.simplesys.components.drawing.drawItems.props

import com.simplesys.SmartClient.DataBinding.props.DataSourceProps
import com.simplesys.SmartClient.DataBinding.props.dataSource.DataSourceFieldProps
import com.simplesys.SmartClient.Drawing.drawItem.props.DrawLinePathSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{FieldType, OperatorId}
import com.simplesys.components.drawing.drawItems.IncomingMessage
import com.simplesys.components.validators.Validators
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js

class IncomingMessageProps extends DrawLinePathSSProps {
    type classHandler <: IncomingMessage

    override val `type`: ScOption[String] = IncomingMessage.typeName.opt
    val opersAdvisa = Seq(OperatorId.contains, OperatorId.iContains, OperatorId.iEquals, OperatorId.equals, OperatorId.iNotEqual, OperatorId.notEqual, OperatorId.iLikeWithOutPunct, OperatorId.likeWithOutPunct, OperatorId.likeSQL, OperatorId.iLikeSQL, OperatorId.notLikeSQL, OperatorId.iNotLikeSQL, OperatorId.regexp)

    fieldDataSource = DataSource.create(
        new DataSourceProps {
            clientOnly = true.opt
            unserialize = true.opt
            cacheData1 = Seq(
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "ADVISA_categoryCode".opt
                        `type` = FieldType.enum
                        title = "Код категории".opt
                        validOperators = opersAdvisa.opt
                        valueMap = js.Dictionary[String](
                            "car"->"Автомобиль",
                            "baby"->"Дети",
                            "home"->"Дом",
                            "health"->"Здоровье",
                            "restaurant"->"Кафе-Ресторан",
                            "club"->"Клуб",
                            "beauty"->"Красота",
                            "music"->"Музыка",
                            "books"->"Образование",
                            "cloth"->"Одежда",
                            "pets"->"Питомцы",
                            "gift"->"Подарки",
                            "grocery"->"Продукты",
                            "travel"->"Путешествия",
                            "entertainment"->"Развлечения",
                            "phone"->"Связь",
                            "soft"->"Софт",
                            "sport"->"Спорт",
                            "insurance"->"Страховка",
                            "devices"->"Техника",
                            "transport"->"Транспорт",
                            "penalty"->"Штраф"
                        ).opt
                    }),
                /*DataSourceField(
                    new DataSourceFieldProps {
                        name = "ADVISA_categoryName".opt
                        `type` = FieldType.text
                        title = "Наименование категории".opt
                        validOperators = opersAdvisa.opt
                    }),*/
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "ADVISA_merchantName".opt
                        `type` = FieldType.text
                        title = "Наименование мерчанта".opt
                        validOperators = opersAdvisa.opt
                    }),
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "ADVISA_terminal".opt
                        `type` = FieldType.text
                        title = "Код терминала".opt
                        validOperators = opersAdvisa.opt
                    }),
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "Pos".opt
                        `type` = FieldType.text
                        title = "Терминал: Pos".opt
                        validOperators = Seq(OperatorId.iLikeWithOutPunct, OperatorId.likeWithOutPunct, OperatorId.likeSQL, OperatorId.iLikeSQL, OperatorId.notLikeSQL, OperatorId.iNotLikeSQL, OperatorId.regexp).opt
                    }),
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "RootType".opt
                        `type` = FieldType.enum
                        title = "Тип операции".opt
                        validOperators = Seq(OperatorId.equals, OperatorId.notEqual).opt
                        valueMap = js.Dictionary[String](
                            "Purchase" → "Покупка",
                            "Withdrawal" → "Снятие",
                            "CashIn" → "Пополнение"
                        ).opt
                    }),
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "summa".opt
                        `type` = FieldType.float
                        title = "Сумма операции".opt
                        validOperators = Seq(OperatorId.iEquals, OperatorId.iNotEqual, OperatorId.between, OperatorId.greaterThan, OperatorId.lessThan, OperatorId.greaterOrEqual, OperatorId.lessOrEqual).opt
                        validators = Seq(
                            Validators.floatValidator
                        ).opt
                    }),
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "cur".opt
                        `type` = FieldType.enum
                        title = "Валюта операции".opt
                        validOperators = Seq(OperatorId.iNotEqual, OperatorId.iEquals).opt
                        valueMap = js.Dictionary[String](
                            "RUB" → "Рубль РФ",
                            "USD" → "Доллар США"
                        ).opt
                    }),
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "balance".opt
                        `type` = FieldType.float
                        title = "Остаток".opt
                        validOperators = Seq(OperatorId.iEquals, OperatorId.iNotEqual, OperatorId.between, OperatorId.greaterThan, OperatorId.lessThan, OperatorId.greaterOrEqual, OperatorId.lessOrEqual).opt
                        validators = Seq(
                            Validators.floatValidator
                        ).opt
                    }),
                DataSourceField(
                    new DataSourceFieldProps {
                        name = "balanceCur".opt
                        `type` = FieldType.enum
                        title = "Валюта остатка".opt
                        validOperators = Seq(OperatorId.iEquals).opt
                        valueMap = js.Dictionary(
                            "RUB" → "Рубль РФ",
                            "USD" → "Доллар США"
                        ).opt
                    })
            ).opt
        }
    ).opt
}

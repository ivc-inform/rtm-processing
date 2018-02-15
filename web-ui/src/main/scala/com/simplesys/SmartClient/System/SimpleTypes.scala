package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.DataBinding.props.SimpleTypeProps
import com.simplesys.System.Types.OperatorId
import com.simplesys.option.ScOption._

object SimpleTypes {
    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "boolean".opt
        name = "bBoolean_SimpleType".opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "textArea".opt
        name = "clob_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "date".opt
        name = "dDate_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "datetime".opt
        name = "dDateOptTime_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "datetime".opt
        name = "dDateTime_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "datetime".opt
        name = "dTimestamp_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "datetime".opt
        name = "dTimestampWithTZ_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "float".opt
        name = "di_SimpleType".opt
        validOperators = Seq(
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.equalsField,
            OperatorId.inSet,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "float".opt
        name = "fDouble_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "float".opt
        name = "fPrice_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "float".opt
        name = "fSum_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "integer".opt
        name = "id_SimpleType".opt
        validOperators = Seq(
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.equalsField,
            OperatorId.inSet,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "integer".opt
        name = "nInt_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.notStartsWith,
            OperatorId.lessOrEqual,
            OperatorId.notEndsWith,
            OperatorId.containsPattern,
            OperatorId.equalsField,
            OperatorId.greaterOrEqual,
            OperatorId.startsWith,
            OperatorId.greaterOrEqualField,
            OperatorId.lessOrEqualField,
            OperatorId.notContains,
            OperatorId.inSet,
            OperatorId.lessThan,
            OperatorId.greaterThanField,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.lessThanField,
            OperatorId.notNull,
            OperatorId.matchesPattern,
            OperatorId.endsWith,
            OperatorId.regexp,
            OperatorId.between,
            OperatorId.betweenInclusive,
            OperatorId.greaterThan
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "sAddress_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "sBarCode_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "sCaption_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "sCode_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "textArea".opt
        name = "sDescription_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "sEMail_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "password".opt
        name = "sPasswordHashSHA_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "password".opt
        name = "sPasswordPlain_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "sPhone_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "sPostalIndex_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "link".opt
        name = "sURL_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "sURLDomain_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "image".opt
        name = "sURLImage_SimpleType".opt
        validOperators = Seq(
            OperatorId.contains,
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.iContains,
            OperatorId.startsWith,
            OperatorId.inSet,
            OperatorId.iNotEqual,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull,
            OperatorId.endsWith,
            OperatorId.endsWith,
            OperatorId.iStartsWith,
            OperatorId.iEquals
        ).opt
    })

    SimpleType.create(new SimpleTypeProps {
        inheritsFrom = "text".opt
        name = "ss_SimpleType".opt
        validOperators = Seq(
            OperatorId.isNull,
            OperatorId.notInSet,
            OperatorId.equalsField,
            OperatorId.inSet,
            OperatorId.notEqualField,
            OperatorId.notEqual,
            OperatorId.equals,
            OperatorId.notNull
        ).opt
    })
}

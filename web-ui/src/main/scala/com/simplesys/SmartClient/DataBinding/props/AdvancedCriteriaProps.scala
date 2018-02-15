package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class AdvancedCriteriaProps extends CriterionProps {
    var strictSQLFiltering: ScOption[Boolean] = ScNone
    _constructor = "AdvancedCriteria".opt
}

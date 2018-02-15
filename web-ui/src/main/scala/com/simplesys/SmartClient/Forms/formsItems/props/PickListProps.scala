package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.DataBinding.props.SortSpecifierProps
import com.simplesys.SmartClient.DataBinding.{DSRequest, DSResponse, ResultSet, SortSpecifier}
import com.simplesys.SmartClient.Forms.formsItems.PickList
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.Types.PickListItemIconPlacement.PickListItemIconPlacement
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.ThisFunction4

trait PickListProps extends ClassProps {
    type classHandler <: PickList

    var animatePickList: ScOption[Boolean] = ScNone
    var animationTime: ScOption[Int] = ScNone
    var cachePickListResults: ScOption[Boolean] = ScNone
    var dataArrived: ScOption[js.Function3[Int, Int, ResultSet, _]] = ScNone
    var emptyPickListHeight: ScOption[Int] = ScNone
    var emptyPickListMessage: ScOption[String] = ScNone
    var fetchDelay: ScOption[Int] = ScNone
    var fetchDisplayedFieldsOnly: ScOption[Boolean] = ScNone
    var hideEmptyPickList: ScOption[Boolean] = ScNone
    var iconPlacement: ScOption[PickListItemIconPlacement] = ScNone
    var initialSort: ScOption[Seq[SortSpecifierProps]] = ScNone
    var pickListApplyRowNumberStyle: ScOption[Boolean] = ScNone
    var pickListBaseStyle: ScOption[CSSStyleName] = ScNone
    var pickListCellHeight: ScOption[Int] = ScNone
    var pickListConstructor: ScOption[SCClassName] = ScNone

    var filterComplete: ScOption[ThisFunction4[classHandler, JSUndefined[DSResponse], IscArray[JSObject], JSUndefined[DSRequest], JSUndefined[Boolean], _]] = {
        (thiz: classHandler, response: JSUndefined[DSResponse], data: IscArray[JSObject], request: JSUndefined[DSRequest], fromSharedPickList: JSUndefined[Boolean]) =>

            var returnExec = false
            if (!fromSharedPickList.getOrElse(false) && request.isDefined && request.get.clientContext.isDefined) {
                // if we get back out of sequence responses, don't allow the earlier one to clobber the
                // more recent one.

                val lastID = thiz._lastFetchID
                val newID = request.get.clientContext.get.fetchID
                if (lastID.isEmpty || lastID.get < newID)
                    thiz._lastFetchID = newID
                else {
                    thiz.logWarn("Server returned out of order responses for databound fetch requests. Ignoring superceded request results")
                    returnExec = true
                }
            }

            if (!returnExec) {
                thiz._fetchingPickListData = false

                // If the present call to filterComplete() was for a different item to which the
                // shared pickList menu was applied, then don't update the shared pickList menu.
                //if (thiz.pickList)

                thiz.pickList.foreach {
                    pickList =>
                        if (pickList._fetchingForItem.isDefined && thiz.getID() != pickList._fetchingForItem.get) {
                            // Otherwise, update the pickList
                        } else {
                            if (thiz.getID() == pickList._fetchingForItem.getOrElse(false))
                                pickList._fetchingForItem = jSUndefined

                            thiz._processingFilterComplete = true

                            thiz._updatePickListForFilterComplete(response, data, request)

                            thiz._updateValueForFilterComplete(response, data, request)

                            thiz._processingFilterComplete = false

                            if (isc.Canvas.ariaEnabled())
                                if (pickList._fetchingForItem.isEmpty || thiz.getID() == pickList._fetchingForItem.get || fromSharedPickList.isDefined)
                                    pickList clearAriaState "busy"

                        }
                }

                // If a callback was passed in when the filter was intialized, fire it now, passing in
                // the resultSet as a single parameter.

                response foreach {
                    response =>
                        request foreach (
                          request => request.internalClientContext._callback.foreach {
                              callback =>
                                  thiz.fireCallback(callback, "item,dsResponse,data,dsRequest", IscArray(thiz, response, data, request))
                                  request.internalClientContext._callback = jSUndefined
                          })
                }
            }
    }.toThisFunc.opt

    var pickListCriteria: ScOption[Criteria] = ScNone
    var pickListFields: ScOption[IscArray[ListGridField]] = ScNone
    var pickListHeaderHeight: ScOption[Int] = ScNone
    var pickListHeight: ScOption[Int] = ScNone
    var pickListMaxWidth: ScOption[Int] = ScNone
    var pickListProperties: ScOption[Grid[ListGridField, ListGridRecord]] = ScNone
    var pickListTallBaseStyle: ScOption[CSSStyleName] = ScNone
    var pickListWidth: ScOption[Int] = ScNone
    var separatorRows: ScOption[IscArray[ListGridRecord]] = ScNone
    var showAllOptions: ScOption[Boolean] = ScNone
    var showOptionsFromDataSource: ScOption[Boolean] = ScNone
    var sortField: ScOption[IscArray[String]] = ScNone
    var specialValues: ScOption[ValueMap] = ScNone
    var textMatchStyle: ScOption[TextMatchStyle] = ScNone
    var useClientFiltering: ScOption[Boolean] = ScNone
    var valueIconField: ScOption[String] = ScNone
}

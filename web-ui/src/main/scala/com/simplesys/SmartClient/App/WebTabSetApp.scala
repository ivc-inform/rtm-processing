package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.{EditorUserGroupsProps, EditorUsersProps}
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.DataBinding.props.dataSource.DataSourceFieldProps
import com.simplesys.SmartClient.DataBinding.props.{DataSourceProps, DataViewProps}
import com.simplesys.SmartClient.DataBinding.{DataSource, DataSourceSSstatic, RestDataSourceSS}
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.LabelProps
import com.simplesys.SmartClient.Grids.props.ListGridEditorProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.RibbonGroupSS
import com.simplesys.SmartClient.Layout.props._
import com.simplesys.SmartClient.RPC.RPCManagerSS
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.Tools.WindowsStack
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExport

trait WebTabSetApp extends TabSetStack {

    self =>

    protected val windowsStack = new WindowsStack

    protected val loadSchemas: Boolean
    protected val identifier: ID
    protected val appImageDir: String

    protected val dataSourcesJS_admin_UserGroup_DS: RestDataSourceSS
    protected val dataSourcesJS_admin_User_DS: RestDataSourceSS

    protected val listGridFiledsJS_admin_UserGroup_FLDS: Seq[ListGridFieldProps]
    protected val listGridFiledsJS_admin_User_FLDS: Seq[ListGridFieldProps]

    protected val formItemsJS_admin_UserGroup_FRMITM: Seq[FormItem]
    protected val formItemsJS_admin_User_FRMITM: Seq[FormItem]

    protected val admin_User_codeGroup_NameStrong: NameStrong
    protected def getSettingsEditor(): SettingsEditor

    protected val functionButton = IconMenuButtonSS.create(
        new IconMenuButtonSSProps {
            title = "Операции".ellipsis.opt
            icon = Common.iconConstructor.opt
            identifier = "33EE1839-8D4D-FFA0-E491-22B54F212772A".opt
        }
    )

    protected val functionGroup = RibbonGroupSS.create(
        new RibbonGroupSSProps {
            title = "Управление".ellipsis.opt
            visibility = Visibility.hidden.opt
            controls = Seq(
                functionButton
            ).opt
        })

    protected def managedUsersGroups: Seq[RibbonGroupSS]
    protected def progectManagedDevsGroups: Seq[RibbonGroupSS]

    @JSExport
    def getUIContent() {
        Page.setEvent(
            PageEvent.load, {
                (target: JSObject) =>

                    isc.params.locale = "ru_RU"
                    //isc.params.locale = "en"

                    val skin: String = simpleSyS.skin.toOption match {
                        case Some(skin) => skin
                        case None => isc.OfflineSS.get(s"Skin$identifier", Skin.Enterprise.toString)
                    }

                    simpleSyS.skin = skin

                    Page setAppImgDir appImageDir

                    FileLoader.loadSkin(
                        skin, {
                            () =>
                                var localeFile = "isomorphic/locales/frameworkMessages.properties"
                                if (isc.params.locale != "en")
                                    localeFile = "isomorphic/locales/frameworkMessages_" + isc.params.locale + ".properties"

                                FileLoader.loadJSFiles(localeFile, {
                                    () =>
                                        if (loadSchemas)
                                            DataSourceSSstatic.loadComponentSchemas(
                                                () =>
                                                    DataView.create(
                                                        new DataViewProps {
                                                            height = "100%"
                                                            width = "100%"
                                                            members = Seq(
                                                                mainCanvas
                                                            ).opt
                                                        }
                                                    )
                                            )
                                        else
                                            DataView.create(
                                                new DataViewProps {
                                                    height = "100%"
                                                    width = "100%"
                                                    members = Seq(
                                                        mainCanvas
                                                    ).opt
                                                }
                                            )
                                })
                        }
                    )
            }
        )
    }

    private lazy val aboutDS: DataSource =
        DataSource.create(
            new DataSourceProps {
                clientOnly = true.opt
                dataFormat = DSDataFormat.json.opt
                cacheData = simpleSyS.aboutData.seq.opt
                fields = Seq(
                    new DataSourceFieldProps {
                        required = true.opt
                        `type` = FieldType.sCode_SimpleType.opt
                        title = "Наименование".opt
                        primaryKey = true.opt
                        name = "libName".opt
                    },
                    new DataSourceFieldProps {
                        required = true.opt
                        `type` = FieldType.sCode_SimpleType.opt
                        title = "Версия".opt
                        primaryKey = true.opt
                        name = "libVersion".opt
                    }
                ).opt
            }
        )

    protected def getAbout(): Unit = {
        //isc debugTrap simpleSyS.aboutData
        aboutDS setCacheData simpleSyS.aboutData

        isc.infos(
            ListGridEditor(
                new ListGridEditorProps {
                    identifier = "544C01DA-5F30-0126-8546-00F31AC35541".opt
                    autoFetchData = true.opt
                    dataSource = aboutDS.opt
                    fields = Seq(
                        new ListGridFieldProps {
                            nameStrong = "libName".nameStrongOpt
                        },
                        new ListGridFieldProps {
                            nameStrong = "libVersion".nameStrongOpt
                            align = Alignment.center.opt
                        }
                    ).opt
                }
            ),
            "544C01DA-5F30-0126-8546-00F31AC36341"
        )
    }

    protected val managedAdminsGroups: Seq[RibbonGroupSS] = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Администраторы".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Администрирование".ellipsis.opt
                            icon = Common.ref.opt
                            identifier = "33EE1839-8D4D-FFA0-E491-22B54F2C772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "groups".opt
                                            icon = Common.admin_UserGroup.opt
                                            title = "Группы".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(
                                                        EditorUserGroups.create(
                                                            new EditorUserGroupsProps {
                                                                dataSource = dataSourcesJS_admin_UserGroup_DS.opt
                                                                fields = listGridFiledsJS_admin_UserGroup_FLDS.opt
                                                                editingFields = formItemsJS_admin_UserGroup_FRMITM.opt
                                                            }), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "users".opt
                                            icon = Common.admin_User.opt
                                            title = "Пользователи".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(EditorUsers.create(
                                                        new EditorUsersProps {
                                                            dataSourceList = dataSourcesJS_admin_User_DS.opt
                                                            dataSourceTree = dataSourcesJS_admin_UserGroup_DS.opt

                                                            fieldsTree = listGridFiledsJS_admin_UserGroup_FLDS.opt
                                                            editingTreeFields = formItemsJS_admin_UserGroup_FRMITM.opt

                                                            fieldsList = listGridFiledsJS_admin_User_FLDS.opt
                                                            editingListFields = formItemsJS_admin_User_FRMITM.opt

                                                            val userGroupEditor = EditorUserGroups.create(
                                                                new EditorUserGroupsProps {
                                                                    dataSource = dataSourcesJS_admin_UserGroup_DS.opt
                                                                    fields = listGridFiledsJS_admin_UserGroup_FLDS.opt
                                                                    editingFields = formItemsJS_admin_UserGroup_FRMITM.opt
                                                                })

                                                            replacingFieldsList = Seq(
                                                                new ListGridFieldProps {
                                                                    nameStrong = admin_User_codeGroup_NameStrong.opt
                                                                    editorType = FormItemComponentType.LookupTreeGridEditorItem
                                                                    editorProperties = LookupTreeGridEditorItem(
                                                                        new LookupTreeGridEditorItemProps {
                                                                            treeGridEditor = userGroupEditor.opt
                                                                        }).opt
                                                                }
                                                            ).opt
                                                        }
                                                    ), item)
                                            }.toFunc.opt
                                        }
                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        )
    ).map {
        item =>
            item.hide()
            item
    }

    private val infoGroup = Seq(RibbonGroupSS.create(
        new RibbonGroupSSProps {
            title = "Системные".ellipsis.opt
            controls = Seq(
                IconButtonSS.create(
                    new IconButtonSSProps {
                        title = "Настройки".ellipsis.opt
                        icon = Common.settings.opt
                        click = {
                            (thiz: classHandler) =>
                                getSettingsEditor()
                                false
                        }.toThisFunc.opt
                    }
                ),
                IconButtonSS.create(
                    new IconButtonSSProps {
                        title = "Информация".ellipsis.opt
                        icon = Common.info.opt
                        click = {
                            (thiz: classHandler) =>
                                getAbout()
                                false
                        }.toThisFunc.opt
                    }
                )
            ).opt
        }
    )).map {
        item =>
            item.hide()
            item
    }

    protected val managedDevsGroups: Seq[RibbonGroupSS] = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Разработчики".ellipsis.opt
                controls = Seq(
                    IconButtonSS.create(
                        new IconButtonSSProps {
                            title = "GUID".opt
                            icon = Common.guid.opt
                            orientation = "gorizontal".opt
                            click = {
                                (thiz: classHandler) =>
                                    isc info(simpleSyS.guid, "163B7F9E-576B-7EFA-8F3C-E536055508B4")
                                    false
                            }.toThisFunc.opt
                        }
                    )
                ).opt
            }
        )
    ).map {
        item =>
            item.hide()
            item
    }

    private val captionUserLabel = Label.create(
        new LabelProps {
            showEdges = true.opt
            contents = "Иванов Иван Иванович".opt
            icon = Common.approved.opt
            wrap = true.opt
            visibility = Visibility.hidden.opt
        }
    )

    protected def mainCanvas: Canvas =
        VLayoutSS.create(
            new VLayoutSSProps {
                members = Seq(
                    RibbonBar.create(
                        new RibbonBarProps {
                            width = "100%"
                            showResizeBar = true.opt
                            members = (
                              managedUsersGroups ++
                                progectManagedDevsGroups ++
                                managedDevsGroups ++
                                managedAdminsGroups ++
                                Seq(functionGroup) ++
                                infoGroup ++
                                Seq(
                                    LayoutSpacer.create(
                                        new LayoutSpacerProps {
                                            width = "*"
                                        }
                                    ),
                                    RibbonGroupSS.create(
                                        new RibbonGroupSSProps {
                                            title = "Аутентификация".ellipsis.opt
                                            defaultLayoutAlign = Alignment.center
                                            width = 40
                                            controls = Seq(
                                                IconButtonSS.create(
                                                    new IconButtonSSProps {
                                                        click = {
                                                            (thiz: classHandler) =>
                                                                if (!LoggedGroup.logged) {
                                                                    RPCManagerSS.loginRequired({
                                                                        (res: Boolean, captionUser: JSUndefined[String], codeGroup: JSUndefined[String]) =>
                                                                            if (res) {

                                                                                captionUserLabel setContents s"Работает: '${captionUser.toOption.getOrElse("Не определен")}'"
                                                                                captionUserLabel.show()
                                                                                managedUsersGroups.foreach(_.show())
                                                                                infoGroup.foreach(_.show())
                                                                                LoggedGroup.codeGroup = codeGroup.toOption

                                                                                if (LoggedGroup.isAdminsGroup() || LoggedGroup.isDevsGroup())
                                                                                    managedAdminsGroups.foreach(_.show())

                                                                                if (LoggedGroup.isRoot()) {
                                                                                    managedAdminsGroups.foreach(_.show())
                                                                                }

                                                                                if (LoggedGroup.isDevsGroup()) {
                                                                                    managedDevsGroups.foreach(_.show())
                                                                                    progectManagedDevsGroups.foreach(_.show())
                                                                                }

                                                                                LoggedGroup.logged = true

                                                                                thiz setTitle "Выход"
                                                                                thiz setIcon Common.closeProgram
                                                                            } else {
                                                                                managedUsersGroups.foreach(_.hide())
                                                                                managedAdminsGroups.foreach(_.hide())
                                                                                managedDevsGroups.foreach(_.hide())
                                                                                progectManagedDevsGroups.foreach(_.hide())
                                                                                infoGroup.foreach(_.hide())
                                                                                captionUserLabel.hide()
                                                                                tabGroupSet.removeAllTabs()

                                                                                LoggedGroup.logged = false
                                                                                thiz setTitle "Вход".ellipsis
                                                                                thiz setIcon Common.login
                                                                            }
                                                                    }.toFunc)

                                                                } else {
                                                                    RPCManagerSS.logoutRequired()
                                                                    thiz setTitle "Вход".ellipsis
                                                                    thiz setIcon Common.login
                                                                    LoggedGroup.logged = false
                                                                    managedUsersGroups.foreach(_.hide())
                                                                    managedAdminsGroups.foreach(_.hide())
                                                                    managedDevsGroups.foreach(_.hide())
                                                                    progectManagedDevsGroups.foreach(_.hide())
                                                                    captionUserLabel.hide()
                                                                    functionGroup.hide()
                                                                    infoGroup.foreach(_.hide())
                                                                    windowsStack.destroyAll()
                                                                    tabGroupSet.removeAllTabs()
                                                                }
                                                                false
                                                        }.toThisFunc.opt
                                                        title = "Войти".ellipsis.opt
                                                        iconOrientation = IconOrientation.center.opt
                                                        icon = Common.login.opt
                                                    }
                                                ),
                                                captionUserLabel
                                            ).opt
                                            numRows = 3.opt
                                            titleHeight = 18.opt
                                        }
                                    )
                                )).opt
                        }
                    ),
                    tabGroupSet
                ).opt
            }
        )
}

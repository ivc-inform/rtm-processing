<?xml version="1.1"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:isc="http://simpleSys.ru/xml/library/ISC" xmlns:app="http://simpleSys.ru/xml/library/app"
                xmlns:common="http://simpleSys.ru/xml/library/common" exclude-result-prefixes="xs isc app common">

	<xsl:import href="common.xsl"/>

	<xsl:template name="app:DefaultListGridEditorMenu">
		<xsl:param as="xs:string" name="MenuName" tunnel="yes"/>
		<xsl:param as="xs:string" name="jsDir" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="addEnable" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="editEnable" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="deleteEnable" tunnel="yes"/>

		<isc:MenuDyn>
			<isc:Identifier>
				<xsl:value-of select="$MenuName"/>
			</isc:Identifier>
			<isc:useSelfName>true</isc:useSelfName>
			<xsl:call-template name="app:DefaultListGridEditorMenuItems"/>
		</isc:MenuDyn>
	</xsl:template>

	<xsl:template name="app:DefaultListGridEditorMenuItems">
		<xsl:param as="xs:string" name="jsDir" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="addEnable" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="editEnable" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="deleteEnable" tunnel="yes"/>

		<isc:Items>
			<xsl:if test="$addEnable">
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>newListGridEditorRow</isc:FunctionName>
					</isc:Click>
					<isc:Icon>Actions-insert-link-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+N</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>N</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title Ellipsis="true">Новый</isc:Title>
				</isc:MenuItemDyn>
			</xsl:if>
			<xsl:if test="$editEnable">
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>editRow</isc:FunctionName>
					</isc:Click>
					<isc:EnableIf>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>enableEdit</isc:FunctionName>
					</isc:EnableIf>
					<isc:Icon>Actions-document-edit-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+E</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>E</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title Ellipsis="true">Изменить</isc:Title>
				</isc:MenuItemDyn>
			</xsl:if>
			<xsl:if test="$deleteEnable">
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>removeRow</isc:FunctionName>
					</isc:Click>
					<isc:EnableIf>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>enableDelete</isc:FunctionName>
					</isc:EnableIf>
					<isc:Icon>Delete-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+D</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>D</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title>Удалить</isc:Title>
				</isc:MenuItemDyn>
			</xsl:if>
			<isc:MenuItemDyn>
				<isc:Click>
					<isc:FunctionsFileURL>
						<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
					</isc:FunctionsFileURL>
					<isc:FunctionName>refresh</isc:FunctionName>
				</isc:Click>
				<isc:Icon>Refresh.png</isc:Icon>
				<!--<isc:KeyTitle>Ctrl+R</isc:KeyTitle>
				<isc:Keys>
					<isc:KeyIdentifier>
						<isc:KeyName>R</isc:KeyName>
						<isc:CtrlKey>true</isc:CtrlKey>
					</isc:KeyIdentifier>
				</isc:Keys>-->
				<isc:Title>Обновить</isc:Title>
			</isc:MenuItemDyn>
			<isc:MenuItemDyn>
				<isc:IsSeparator>true</isc:IsSeparator>
			</isc:MenuItemDyn>
			<xsl:if test="$addEnable or $editEnable or $deleteEnable">
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>saveRecords</isc:FunctionName>
					</isc:Click>
					<isc:EnableIf>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>enableSave</isc:FunctionName>
					</isc:EnableIf>
					<isc:Icon>Save-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+S</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>S</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title>Сохранить</isc:Title>
				</isc:MenuItemDyn>
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>canselEdited</isc:FunctionName>
					</isc:Click>
					<isc:EnableIf>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>enableSave</isc:FunctionName>
					</isc:EnableIf>
					<isc:Icon>cancel-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+Shift+R</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>R</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
							<isc:ShiftKey>true</isc:ShiftKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title>Отменить изменения</isc:Title>
				</isc:MenuItemDyn>
				<isc:MenuItemDyn>
					<isc:IsSeparator>true</isc:IsSeparator>
				</isc:MenuItemDyn>
			</xsl:if>
			<isc:MenuItemDyn>
				<isc:Click>
					<isc:FunctionsFileURL>
						<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
					</isc:FunctionsFileURL>
					<isc:FunctionName>deleteTab</isc:FunctionName>
				</isc:Click>
				<isc:EnableIf>
					<isc:FunctionsFileURL>
						<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
					</isc:FunctionsFileURL>
					<isc:FunctionName>enableDeleteTable</isc:FunctionName>
				</isc:EnableIf>
				<isc:Icon>Windows-Close-Program-icon.png</isc:Icon>
				<!--<isc:KeyTitle>Ctrl+T</isc:KeyTitle>
				<isc:Keys>
					<isc:KeyIdentifier>
						<isc:KeyName>T</isc:KeyName>
						<isc:CtrlKey>true</isc:CtrlKey>
					</isc:KeyIdentifier>
				</isc:Keys>-->
				<isc:Title>Удалить вкладку</isc:Title>
			</isc:MenuItemDyn>
		</isc:Items>
	</xsl:template>

	<xsl:template name="app:DefaultTreeGridEditorMenu">
		<xsl:param as="xs:string" name="MenuName" tunnel="yes"/>
		<xsl:param as="xs:string" name="jsDir" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="addEnable" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="editEnable" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="deleteEnable" tunnel="yes"/>

		<isc:MenuDyn>
			<isc:Identifier>
				<xsl:value-of select="$MenuName"/>
			</isc:Identifier>
			<isc:useSelfName>true</isc:useSelfName>
			<xsl:call-template name="app:DefaultTreeGridEditorMenuItems"/>
		</isc:MenuDyn>
	</xsl:template>

	<xsl:template name="app:DefaultTreeGridEditorMenuItems">
		<xsl:param as="xs:string" name="jsDir" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="addEnable" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="editEnable" tunnel="yes"/>
		<xsl:param as="xs:boolean" name="deleteEnable" tunnel="yes"/>

		<isc:Items>
			<xsl:if test="$addEnable">
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>newTreeGridEditorRow</isc:FunctionName>
					</isc:Click>
					<isc:Icon>Actions-insert-link-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+N</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>N</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title Ellipsis="true">Новый</isc:Title>
				</isc:MenuItemDyn>
			</xsl:if>
			<xsl:if test="$editEnable">
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>editRow</isc:FunctionName>
					</isc:Click>
					<isc:EnableIf>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>enableEdit</isc:FunctionName>
					</isc:EnableIf>
					<isc:Icon>Actions-document-edit-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+E</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>E</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title Ellipsis="true">Изменить</isc:Title>
				</isc:MenuItemDyn>
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>canReparentNodes</isc:FunctionName>
					</isc:Click>
					<isc:Title Ellipsis="true">Разрешить перемещение группы</isc:Title>
				</isc:MenuItemDyn>
			</xsl:if>
			<xsl:if test="$deleteEnable">
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>removeRow</isc:FunctionName>
					</isc:Click>
					<isc:EnableIf>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>enableDeleteFromTree</isc:FunctionName>
					</isc:EnableIf>
					<isc:Icon>Delete-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+D</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>D</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title>Удалить</isc:Title>
				</isc:MenuItemDyn>
			</xsl:if>
			<isc:MenuItemDyn>
				<isc:Click>
					<isc:FunctionsFileURL>
						<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
					</isc:FunctionsFileURL>
					<isc:FunctionName>refresh</isc:FunctionName>
				</isc:Click>
				<isc:Icon>Refresh.png</isc:Icon>
				<!--<isc:KeyTitle>Ctrl+R</isc:KeyTitle>
				<isc:Keys>
					<isc:KeyIdentifier>
						<isc:KeyName>R</isc:KeyName>
						<isc:CtrlKey>true</isc:CtrlKey>
					</isc:KeyIdentifier>
				</isc:Keys>-->
				<isc:Title>Обновить</isc:Title>
			</isc:MenuItemDyn>
			<isc:MenuItemDyn>
				<isc:Click>
					<isc:FunctionsFileURL>
						<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
					</isc:FunctionsFileURL>
					<isc:FunctionName>openFolders</isc:FunctionName>
				</isc:Click>
				<isc:EnableIf>
					<isc:FunctionsFileURL>
						<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
					</isc:FunctionsFileURL>
					<isc:FunctionName>enableOpenFolders</isc:FunctionName>
				</isc:EnableIf>
				<isc:Icon>openFolder.png</isc:Icon>
				<!--<isc:KeyTitle>Ctrl+O</isc:KeyTitle>
				<isc:Keys>
					<isc:KeyIdentifier>
						<isc:KeyName>O</isc:KeyName>
						<isc:CtrlKey>true</isc:CtrlKey>
					</isc:KeyIdentifier>
				</isc:Keys>-->
				<isc:Title>Развернуть узел</isc:Title>
			</isc:MenuItemDyn>
			<isc:MenuItemDyn>
				<isc:IsSeparator>true</isc:IsSeparator>
			</isc:MenuItemDyn>
			<xsl:if test="$addEnable or $editEnable or $deleteEnable">
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>saveRecords</isc:FunctionName>
					</isc:Click>
					<isc:EnableIf>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>enableSave</isc:FunctionName>
					</isc:EnableIf>
					<isc:Icon>Save-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+S</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>S</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title>Сохранить</isc:Title>
				</isc:MenuItemDyn>
				<isc:MenuItemDyn>
					<isc:Click>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>canselEdited</isc:FunctionName>
					</isc:Click>
					<isc:EnableIf>
						<isc:FunctionsFileURL>
							<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
						</isc:FunctionsFileURL>
						<isc:FunctionName>enableSave</isc:FunctionName>
					</isc:EnableIf>
					<isc:Icon>cancel-icon.png</isc:Icon>
					<!--<isc:KeyTitle>Ctrl+Shift+R</isc:KeyTitle>
					<isc:Keys>
						<isc:KeyIdentifier>
							<isc:KeyName>R</isc:KeyName>
							<isc:CtrlKey>true</isc:CtrlKey>
							<isc:ShiftKey>true</isc:ShiftKey>
						</isc:KeyIdentifier>
					</isc:Keys>-->
					<isc:Title>Отменить изменения</isc:Title>
				</isc:MenuItemDyn>
				<isc:MenuItemDyn>
					<isc:IsSeparator>true</isc:IsSeparator>
				</isc:MenuItemDyn>
			</xsl:if>
			<isc:MenuItemDyn>
				<isc:Click>
					<isc:FunctionsFileURL>
						<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
					</isc:FunctionsFileURL>
					<isc:FunctionName>deleteTab</isc:FunctionName>
				</isc:Click>
				<isc:EnableIf>
					<isc:FunctionsFileURL>
						<xsl:value-of select="concat(common:getRelativeOfPath($jsDir),'MenuItemsFunctions.js/')"/>
					</isc:FunctionsFileURL>
					<isc:FunctionName>enableDeleteTable</isc:FunctionName>
				</isc:EnableIf>
				<isc:Icon>Windows-Close-Program-icon.png</isc:Icon>
				<!--<isc:KeyTitle>Ctrl+T</isc:KeyTitle>
				<isc:Keys>
					<isc:KeyIdentifier>
						<isc:KeyName>T</isc:KeyName>
						<isc:CtrlKey>true</isc:CtrlKey>
					</isc:KeyIdentifier>
				</isc:Keys>-->
				<isc:Title>Удалить вкладку</isc:Title>
			</isc:MenuItemDyn>
		</isc:Items>
	</xsl:template>

	<xsl:template name="app:DefaultTreeListGridEditorMenu">
		<xsl:param as="xs:string" name="MenuName" tunnel="yes"/>
		<xsl:param as="xs:string" name="jsDir" tunnel="yes"/>
		<xsl:param as="xs:string" name="treeGridTitle" select="'Неопределен'"/>
		<xsl:param as="xs:string" name="listGridTitle"/>

		<xsl:param as="xs:boolean" name="addEnableList"/>
		<xsl:param as="xs:boolean" name="editEnableList"/>
		<xsl:param as="xs:boolean" name="deleteEnableList"/>

		<xsl:param as="xs:boolean" name="addEnableTree"/>
		<xsl:param as="xs:boolean" name="editEnableTree" />
		<xsl:param as="xs:boolean" name="deleteEnableTree" />

		<isc:MenuDyn>
			<isc:Identifier>
				<xsl:value-of select="$MenuName"/>
			</isc:Identifier>
			<isc:useSelfName>true</isc:useSelfName>
			<isc:Items>
				<isc:MenuItemDyn>
					<isc:Icon>ellipsis.png</isc:Icon>
					<isc:Submenu>
						<isc:ID>
							<xsl:value-of select="concat($MenuName,'_Tree')"/>
						</isc:ID>
						<isc:Identifier>
							<xsl:value-of select="concat($MenuName,'_Tree')"/>
						</isc:Identifier>
						<isc:useSelfName>true</isc:useSelfName>
						<isc:AutoDraw>false</isc:AutoDraw>
						<isc:ShadowDepth>10</isc:ShadowDepth>
						<isc:ShowShadow>true</isc:ShowShadow>
						<xsl:call-template name="app:DefaultTreeGridEditorMenuItems">
							<xsl:with-param name="addEnable" as="xs:boolean" select="$addEnableTree" tunnel="yes"/>
							<xsl:with-param name="deleteEnable" as="xs:boolean" select="$deleteEnableTree" tunnel="yes"/>
							<xsl:with-param name="editEnable" as="xs:boolean" select="$editEnableTree" tunnel="yes"/>
						</xsl:call-template>
					</isc:Submenu>
					<isc:Title Ellipsis="true">
						<xsl:value-of select="$treeGridTitle"/>
					</isc:Title>
				</isc:MenuItemDyn>
				<isc:MenuItemDyn>
					<isc:Icon>ellipsis.png</isc:Icon>
					<isc:Submenu>
						<isc:ID>
							<xsl:value-of select="concat($MenuName,'_List')"/>
						</isc:ID>
						<isc:Identifier>
							<xsl:value-of select="concat($MenuName,'_List')"/>
						</isc:Identifier>
						<isc:useSelfName>true</isc:useSelfName>
						<isc:AutoDraw>false</isc:AutoDraw>
						<isc:ShadowDepth>10</isc:ShadowDepth>
						<isc:ShowShadow>true</isc:ShowShadow>
						<xsl:call-template name="app:DefaultListGridEditorMenuItems">
							<xsl:with-param name="addEnable" as="xs:boolean" select="$addEnableList" tunnel="yes"/>
							<xsl:with-param name="deleteEnable" as="xs:boolean" select="$deleteEnableList" tunnel="yes"/>
							<xsl:with-param name="editEnable" as="xs:boolean" select="$editEnableList" tunnel="yes"/>
						</xsl:call-template>
					</isc:Submenu>
					<isc:Title Ellipsis="true">
						<xsl:value-of select="$listGridTitle"/>
					</isc:Title>
				</isc:MenuItemDyn>
			</isc:Items>
		</isc:MenuDyn>
	</xsl:template>
</xsl:stylesheet>
<?xml version="1.0"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:app="http://simpleSys.ru/xml/library/app" xmlns:isc="http://simpleSys.ru/xml/library/ISC" xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:bo="http://simpleSys.ru/xml/library/bo" xmlns:common="http://simpleSys.ru/xml/library/common" xmlns:merge="http://simpleSys.ru/xml/library/merge" exclude-result-prefixes="isc xs common bo merge">

	<xsl:import href="common.xsl"/>
	<xsl:import href="commonApp.xsl"/>
	<xsl:import href="MergedFiles.xsl"/>
	<xsl:import href="DefaultTemplates.xsl"/>

	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaApp.xsd"/>
	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schema.xsd"/>

	<xsl:output indent="yes" method="xml" encoding="UTF-8" name="format"/>

	<!--<xsl:param name="FilesName" as="xs:string*" select="'file:///f:/src/main/resources/defs/app/macroBo/admin_UserGroup.xml'"/>-->
	<!--<xsl:param name="FilesName" as="xs:string*" select="'file:///f:/src/main/resources/defs/app/macroBo/common_ContractorGroup.xml'"/>-->
	<!--<xsl:param name="FilesName" as="xs:string*" select="'file:///f:/src/main/resources/defs/app/macroBo/common_Contractor.xml'"/>-->
	<xsl:param name="FilesName" as="xs:string*" select="'file:///f:/src/main/resources/defs/app/macroBo/admin_User.xml'"/>
	<!--<xsl:param name="FilesName" as="xs:string*" select="'file:///f:/src/main/resources/defs/app/macroBo/test_TestPaging.xml'"/>-->
	<!--<xsl:param name="FilesName" as="xs:string*" select="'file:///f:/src/main/resources/defs/app/macroBo/userOld.xml'"/>-->
	<!--<xsl:param name="FilesName" as="xs:string*" select="('file:///f:/src/main/resources/defs/app/macroBo/contractors.xml','file:///f:/src/main/resources/defs/app/macroBo/user.xml')"/>-->
	<!--<xsl:param name="FilesName" as="xs:string*"/>-->
	<xsl:param name="ContextPath" as="xs:string" select="'mfms'"/>

	<xsl:param name="macroDir" as="xs:string" select="'file:///f:/src/main/resources/defs/app/macroBo'"/>
	<xsl:variable name="_macroDir" as="xs:string" select="common:check-last-slash($macroDir)"/>

	<xsl:param name="tmpDir" as="xs:string" select="'file:///f:/target/scala-2.11/src_managed/main/defs/app/tmp'"/>
	<xsl:variable name="_tmpDir" as="xs:string" select="common:check-last-slash($tmpDir)"/>

	<xsl:param name="jsDir" as="xs:string" select="'file:///f:/src/main/resources/defs/app/js'"/>
	<xsl:variable name="_jsDir" as="xs:string" select="common:check-last-slash($jsDir)"/>

	<xsl:param name="generatedDir" as="xs:string" select="'file:///f:/target/scala-2.11/src_managed/main/defs/app/generated/xml'"/>
	<xsl:variable name="_generatedDir" as="xs:string" select="common:check-last-slash($generatedDir)"/>

	<xsl:variable select="'http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd'" name="schemaAppPath" as="xs:string"/>

	<xsl:variable name="docBO" select="doc(concat($_tmpDir,'allBo.xml'))"/>
	<xsl:variable name="dataSourceBO" select="doc(concat($_tmpDir,'dataSources.xml'))"/>
	<!--<xsl:variable name="docDefaults" select="doc(concat($_macroDir,'developed', '/', 'defaults.xml'))"/>-->

	<xsl:template name="ProcessingAll">
		<xsl:call-template name="app:Transformation"/>
	</xsl:template>

	<!--.................................................................................-->

	<xsl:template name="app:Transformation">
		<!--<xsl:message select="'Node: LRE means Literal result element'"/>-->
		<xsl:for-each select="$FilesName">
			<xsl:message select="concat('Transformation begin for: ',common:dblQuoted(.)),'Context Path: ',common:dblQuoted($ContextPath)"/>
			<!--Разбор макроописания-->
			<xsl:variable as="node()*" name="source" select="doc(.)"/>
			<xsl:variable as="xs:string" name="sourceHandMadeFile" select="concat(common:substring-before-last(. , '/'), '/handMade/', common:substring-after-last(., '/'))"/>
			<xsl:message select="concat('HandMade File is: ', $sourceHandMadeFile)"/>
			<xsl:choose>
				<xsl:when test="$source/app:RootPane/app:Bo/@Enabled=true()">
					<xsl:result-document href="{concat($_generatedDir, $source/app:RootPane/app:Bo/@GroupName, '_', $source/app:RootPane/app:Bo/@BoName,'.xml')}" format="format" validation="strict">
						<xsl:apply-templates select="$source/app:RootPane">
							<xsl:with-param as="xs:string" select="$source/app:RootPane/app:Bo/@BoName" name="BoName" tunnel="yes"/>
							<xsl:with-param as="xs:string" select="$source/app:RootPane/app:Bo/@GroupName" name="GroupName" tunnel="yes"/>
							<xsl:with-param as="xs:string" select="$sourceHandMadeFile" name="HandMadeFile" tunnel="yes"/>
							<xsl:with-param as="xs:string" select="concat(app:getDataSourceIdentifier($source/app:RootPane/app:Bo/@GroupName, $source/app:RootPane/app:Bo/@BoName),'_', 'ComponentMenu')" name="MenuName" tunnel="yes"/>
						</xsl:apply-templates>
					</xsl:result-document>
				</xsl:when>
				<xsl:otherwise>
					<xsl:message select="'Enabled=false, because skiped.'"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
		<!--.................................................................................-->

		<xsl:message select="'Transformation done.'"/>
	</xsl:template>

	<!-- Блок разбора макроописания-->
	<xsl:function name="app:getEnabled" as="xs:boolean">

		<xsl:param as="xs:string" name="GroupName"/>
		<xsl:param as="xs:string" name="BoName"/>
		<xsl:param as="xs:string" name="operationType"/>
		<xsl:param as="xs:string" name="dsID"/>

		<xsl:variable name="Identifier" as="xs:string">
			<xsl:choose>
				<xsl:when test="$dsID">
					<xsl:value-of select="$dsID"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat(app:getDataSourceIdentifier($GroupName, $BoName), '_DS')"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<xsl:variable name="res" select="$dataSourceBO/isc:DataSources/isc:RestDataSourceSSDyn/isc:ID[.=$Identifier]/following-sibling::*/isc:OperationBindingDyn/isc:OperationType=$operationType"/>
		<xsl:choose>
			<xsl:when test="$res">
				<xsl:value-of select="$res"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="false()"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:function>

	<xsl:template match="app:RootPane">
		<xsl:param as="xs:string" name="HandMadeFile" tunnel="yes"/>
		<xsl:param as="xs:string" name="MenuName" tunnel="yes"/>

		<isc:RootPane xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="{concat('http://simpleSys.ru/xml/library/ISC',' ', $schemaAppPath)}" xmlns:isc="http://simpleSys.ru/xml/library/ISC">
			<isc:Bo BoName="{app:Bo/@BoName}" GroupName="{app:Bo/@GroupName}" Enabled="{app:Bo/@Enabled}"/>
			<xsl:choose>
				<xsl:when test="count(//app:DataSource) &gt; 0">
					<isc:DataSources>
						<xsl:apply-templates select="//app:DataSource"/>
					</isc:DataSources>
				</xsl:when>
				<xsl:otherwise>
					<isc:DataSources>
						<xsl:variable name="Identifier" as="xs:string" select="concat(app:getDataSourceIdentifier(app:Bo/@GroupName, app:Bo/@BoName), '_DS')"/>
						<xsl:copy-of select="$dataSourceBO/isc:DataSources/isc:RestDataSourceSSDyn[isc:Identifier=$Identifier]"/>
					</isc:DataSources>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:choose>
				<xsl:when test="count(//app:Menu) &gt; 0">
					<isc:Menus>
						<xsl:apply-templates select="//app:Menu"/>
					</isc:Menus>
				</xsl:when>
				<xsl:otherwise>
					<xsl:variable name="menus" as="node()*">
						<xsl:choose>
							<xsl:when test="app:RootCanvas/app:ListGridEditor">
								<!--<xsl:message select="'Executed app:DefaultListGridEditorMenu'" terminate="no"/>-->
								<xsl:call-template name="app:DefaultListGridEditorMenu">
									<xsl:with-param name="jsDir" select="$_jsDir" as="xs:string" tunnel="yes"/>
									<xsl:with-param name="addEnable" select="app:getEnabled(app:Bo/@GroupName, app:Bo/@BoName, 'dsOptTypeAdd', '')" as="xs:boolean" tunnel="yes"/>
									<xsl:with-param name="editEnable" select="app:getEnabled(app:Bo/@GroupName, app:Bo/@BoName, 'dsOptTypeUpdate', '')" as="xs:boolean" tunnel="yes"/>
									<xsl:with-param name="deleteEnable" select="app:getEnabled(app:Bo/@GroupName, app:Bo/@BoName, 'dsOptTypeRemove', '')" as="xs:boolean" tunnel="yes"/>
								</xsl:call-template>
							</xsl:when>
							<xsl:when test="app:RootCanvas/app:TreeGridEditor">
								<xsl:call-template name="app:DefaultTreeGridEditorMenu">
									<xsl:with-param name="jsDir" select="$_jsDir" as="xs:string" tunnel="yes"/>
									<xsl:with-param name="addEnable" select="app:getEnabled(app:Bo/@GroupName, app:Bo/@BoName, 'dsOptTypeAdd', '')" as="xs:boolean" tunnel="yes"/>
									<xsl:with-param name="editEnable" select="app:getEnabled(app:Bo/@GroupName, app:Bo/@BoName, 'dsOptTypeUpdate', '')" as="xs:boolean" tunnel="yes"/>
									<xsl:with-param name="deleteEnable" select="app:getEnabled(app:Bo/@GroupName, app:Bo/@BoName, 'dsOptTypeRemove', '')" as="xs:boolean" tunnel="yes"/>
								</xsl:call-template>
							</xsl:when>
							<xsl:when test="app:RootCanvas/app:TreeListGridEditor">
								<xsl:variable name="listDS" as="xs:string" select="app:RootCanvas/app:TreeListGridEditor/app:ListGridDataSourceElement/app:DataSourceIDREF"/>
								<xsl:variable name="treeDS" as="xs:string" select="app:RootCanvas/app:TreeListGridEditor/app:TreeGridDataSourceElement/app:DataSourceIDREF"/>

								<xsl:call-template name="app:DefaultTreeListGridEditorMenu">
									<xsl:with-param name="jsDir" select="$_jsDir" as="xs:string" tunnel="yes"/>
									<xsl:with-param name="treeGridTitle" select="app:BoTreeCaption/text()"/>
									<xsl:with-param name="listGridTitle" select="app:Bo/@BoCaption"/>
									<xsl:with-param name="addEnableList" select="app:getEnabled('', '', 'dsOptTypeAdd', $listDS)" as="xs:boolean"/>
									<xsl:with-param name="addEnableTree" select="app:getEnabled('', '', 'dsOptTypeUpdate', $treeDS)" as="xs:boolean"/>
									<xsl:with-param name="editEnableList" select="app:getEnabled('', '', 'dsOptTypeRemove', $listDS)" as="xs:boolean"/>
									<xsl:with-param name="editEnableTree" select="app:getEnabled('', '', 'dsOptTypeAdd', $treeDS)" as="xs:boolean"/>
									<xsl:with-param name="deleteEnableList" select="app:getEnabled('', '', 'dsOptTypeUpdate', $listDS)" as="xs:boolean"/>
									<xsl:with-param name="deleteEnableTree" select="app:getEnabled('', '', 'dsOptTypeRemove', $treeDS)" as="xs:boolean"/>
								</xsl:call-template>
							</xsl:when>
							<xsl:otherwise>
								<xsl:message select="'Bad branch.'" terminate="yes"/>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					<xsl:if test="count($menus) &gt; 0">
						<xsl:choose>
							<xsl:when test="doc-available($HandMadeFile)">
								<isc:Menus>
									<xsl:for-each select="$menus" xpath-default-namespace="isc">
										<!--<xsl:message select="./isc:Identifier/text()" terminate="no"/>-->
										<xsl:variable as="node()*" name="sourceHandMade" select="doc($HandMadeFile)/app:RootPane/app:Menus/app:Menu/app:Native/isc:MenuDyn[isc:Identifier=./isc:Identifier/text()]"/>
										<xsl:call-template name="merge:Merge">
											<xsl:with-param name="nodes1" select="."/>
											<xsl:with-param name="nodes2" select="$sourceHandMade"/>
										</xsl:call-template>
										<xsl:variable as="node()*" name="sourceHandMade1" select="doc($HandMadeFile)/app:RootPane/app:Menus/app:Menu/app:Native/isc:MenuSSDyn[isc:Identifier=./isc:Identifier/text()]"/>
										<xsl:call-template name="merge:Merge">
											<xsl:with-param name="nodes1" select="."/>
											<xsl:with-param name="nodes2" select="$sourceHandMade1"/>
										</xsl:call-template>
									</xsl:for-each>
								</isc:Menus>
							</xsl:when>
							<xsl:otherwise>
								<isc:Menus>
									<xsl:copy-of select="$menus"/>
								</isc:Menus>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:if>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:for-each select="app:MenuPath">
				<!--<xsl:copy-of select="."/>-->
				<isc:MenuPath>
					<xsl:copy-of select="./node()"/>
				</isc:MenuPath>
			</xsl:for-each>
			<xsl:if test="app:RootCanvas">
				<xsl:apply-templates select="app:RootCanvas"/>
			</xsl:if>
		</isc:RootPane>
	</xsl:template>


	<xsl:template match="app:DataSource">
		<xsl:variable name="dataSource">
			<isc:RestDataSourceSSDyn>
				<isc:ID>
					<xsl:value-of select="app:Identifier"/>
				</isc:ID>
				<isc:Identifier>
					<xsl:if test="not (app:Identifier)">
						<xsl:message select="'Must be an Identifier !'" terminate="yes"/>
					</xsl:if>
					<xsl:value-of select="app:Identifier"/>
				</isc:Identifier>
				<isc:DataURL>
					<!--<xsl:value-of select="concat('/',$ContextPath,'/logic/',app:Identifier,'/*')"/>-->
					<xsl:value-of select="concat('/logic/', common:substring-before-last(app:Identifier,'_'),'/*')"/>
				</isc:DataURL>
				<isc:JsonPrefix></isc:JsonPrefix>
				<isc:JsonSuffix></isc:JsonSuffix>
				<xsl:variable name="GroupName" as="xs:string" select="substring-before(app:Identifier,'_')"/>
				<xsl:if test="not($GroupName)">
					<xsl:message terminate="yes">
						<xsl:value-of select="concat('Failed to allocate GroupName from Identifier: ', common:dblQuoted(app:Identifier))"/>
					</xsl:message>
				</xsl:if>

				<xsl:variable name="BoName" as="xs:string" select="substring-after(app:Identifier,'_')"/>
				<xsl:if test="not($BoName)">
					<xsl:message terminate="yes">
						<xsl:value-of select="concat('Failed to allocate BoName from Identifier: ', common:dblQuoted(app:Identifier))"/>
					</xsl:message>
				</xsl:if>

				<xsl:apply-templates select="app:Fields" mode="dataSource">
					<xsl:with-param name="GroupName" select="$GroupName" tunnel="yes"/>
					<xsl:with-param name="BoName" select="$BoName" tunnel="yes"/>
				</xsl:apply-templates>

				<xsl:if test="app:Operations/app:Operation">
					<isc:OperationBindings>
						<xsl:call-template name="app:getOperation">
							<xsl:with-param name="operation" select="app:Operations/app:Operation[app:OperationType='dsOptTypeAdd']"/>
							<xsl:with-param name="mode" select="'Add'"/>
						</xsl:call-template>
						<xsl:call-template name="app:getOperation">
							<xsl:with-param name="operation" select="app:Operations/app:Operation[app:OperationType='dsOptTypeFetch']"/>
							<xsl:with-param name="mode" select="'Fetch'"/>
						</xsl:call-template>
						<xsl:call-template name="app:getOperation">
							<xsl:with-param name="operation" select="app:Operations/app:Operation[app:OperationType='dsOptTypeRemove']"/>
							<xsl:with-param name="mode" select="'Remove'"/>
						</xsl:call-template>
						<xsl:call-template name="app:getOperation">
							<xsl:with-param name="operation" select="app:Operations/app:Operation[app:OperationType='dsOptTypeUpdate']"/>
							<xsl:with-param name="mode" select="'Update'"/>
						</xsl:call-template>
					</isc:OperationBindings>
				</xsl:if>
				<xsl:call-template name="app:getDataUrl">
					<xsl:with-param name="operation" select="app:Operations/app:Operation[app:OperationType='dsOptTypeAdd']"/>
					<xsl:with-param name="dataSourceID" select="app:Identifier"/>
					<xsl:with-param name="mode" select="'Add'"/>
				</xsl:call-template>
				<xsl:call-template name="app:getDataUrl">
					<xsl:with-param name="operation" select="app:Operations/app:Operation[app:OperationType='dsOptTypeFetch']"/>
					<xsl:with-param name="dataSourceID" select="app:Identifier"/>
					<xsl:with-param name="mode" select="'Fetch'"/>
				</xsl:call-template>
				<xsl:call-template name="app:getDataUrl">
					<xsl:with-param name="operation" select="app:Operations/app:Operation[app:OperationType='dsOptTypeRemove']"/>
					<xsl:with-param name="dataSourceID" select="app:Identifier"/>
					<xsl:with-param name="mode" select="'Remove'"/>
				</xsl:call-template>
				<xsl:call-template name="app:getDataUrl">
					<xsl:with-param name="operation" select="app:Operations/app:Operation[app:OperationType='dsOptTypeUpdate']"/>
					<xsl:with-param name="dataSourceID" select="app:Identifier"/>
					<xsl:with-param name="mode" select="'Update'"/>
				</xsl:call-template>
			</isc:RestDataSourceSSDyn>
		</xsl:variable>

		<xsl:variable name="Identifier" as="xs:string" select="app:Identifier"/>
		<!--<xsl:variable name="dataSource1" as="node()*">-->
		<xsl:call-template name="merge:Merge">
			<xsl:with-param name="nodes1" select="$dataSourceBO/isc:DataSources/isc:RestDataSourceSSDyn[isc:Identifier=$Identifier]"/>
			<xsl:with-param name="nodes2" select="$dataSource/node()"/>
		</xsl:call-template>
		<!--</xsl:variable>-->

		<!--<xsl:call-template name="merge:Merge">
                            <xsl:with-param name="nodes1" select="$dataSource1"/>
                            <xsl:with-param name="nodes2" select="app:Native/node()"/>
                        </xsl:call-template>-->
	</xsl:template>

	<xsl:template match="app:Fields" mode="dataSource">
		<isc:Fields>
			<xsl:apply-templates select="app:Field" mode="dataSource"/>
		</isc:Fields>
	</xsl:template>

	<xsl:template match="app:Field" mode="dataSource">
		<xsl:param name="GroupName" as="xs:string" tunnel="yes"/>
		<xsl:param name="BoName" as="xs:string" tunnel="yes"/>
		<!--<xsl:message select="'123456'"/>-->
		<xsl:variable name="GroupPrefix" as="xs:string" select="$docBO/bo:allClasses/bo:class[@group=$GroupName][@name=$BoName]/@groupPrefix"/>

		<xsl:variable name="boAttr" as="node()*" select="app:getBOAttr(app:Name, $GroupName, $BoName)"/>
		<!--<xsl:variable name="field">-->
		<isc:DataSourceFieldDyn>
			<!--<xsl:variable as="xs:string" name="fieldName" select="concat(app:getTableName($GroupPrefix,$BoName),'.',app:getFieldName($boAttr/@name, $boAttr/@type))"/>-->
			<!--<xsl:variable as="xs:string" name="fieldName" select="app:getFieldName($boAttr/@name, $boAttr/@type)"/>-->
			<xsl:variable as="xs:string" name="fieldName" select="$boAttr/@name"/>
			<xsl:if test="app:Hidden">
				<isc:Hidden>
					<xsl:value-of select="app:Hidden"/>
				</isc:Hidden>
			</xsl:if>
			<isc:Name>
				<xsl:value-of select="$fieldName"/>
			</isc:Name>
			<!--<xsl:if test="(app:PrimaryKey = true()) or (app:IsUnique = true())">-->
			<xsl:if test="app:PrimaryKey = true()">
				<isc:PrimaryKey>
					<xsl:value-of select="app:PrimaryKey"/>
				</isc:PrimaryKey>
			</xsl:if>
			<xsl:if test="app:Mantadory">
				<isc:Required>
					<xsl:value-of select="app:Mantadory"/>
				</isc:Required>
			</xsl:if>
			<xsl:if test="app:Caption">
				<isc:Title>
					<xsl:if test="app:Caption[@Ellipsis]">
						<xsl:attribute name="Ellipsis" select="app:Caption/@Ellipsis"/>
					</xsl:if>
					<xsl:attribute name="key4MergeValue" select="$fieldName"/>
					<xsl:value-of select="app:Caption/text()"/>
				</isc:Title>
			</xsl:if>
			<xsl:call-template name="app:getType1">
				<xsl:with-param name="type" select="$boAttr/@type"/>
				<xsl:with-param name="iscType" select="app:FieldType"/>
			</xsl:call-template>
		</isc:DataSourceFieldDyn>
		<!--</xsl:variable>-->
		<!--<xsl:call-template name="merge:Merge">
                            <xsl:with-param name="nodes1" select="$field/node()"/>
                            <xsl:with-param name="nodes2" select="app:Field/app:Native/node()"/>
                        </xsl:call-template>-->
	</xsl:template>

	<xsl:template match="app:Menu">
		<!--<xsl:variable name="menu">-->
		<isc:MenuDyn>
			<xsl:if test="not (app:Identifier)">
				<xsl:message select="'Must be an Identifier !'" terminate="yes"/>
			</xsl:if>
			<isc:Identifier>
				<xsl:value-of select="app:Identifier"/>
			</isc:Identifier>
			<isc:useSelfName>true</isc:useSelfName>
			<isc:AutoDraw>false</isc:AutoDraw>
			<isc:ShadowDepth>10</isc:ShadowDepth>
			<isc:ShowShadow>true</isc:ShowShadow>
			<xsl:apply-templates select="app:MenuItems">
				<xsl:with-param as="xs:string" name="functionsFile" select="common:check-last-slash(concat($_jsDir, app:JSFile))" tunnel="yes"/>
			</xsl:apply-templates>
		</isc:MenuDyn>
		<!--</xsl:variable>-->
		<!--<xsl:call-template name="merge:Merge">
                            <xsl:with-param name="nodes1" select="$menu/node()"/>
                            <xsl:with-param name="nodes2" select="app:Menu/app:Native/node()"/>
                        </xsl:call-template>-->
	</xsl:template>

	<xsl:template match="app:MenuItems">
		<isc:Items>
			<xsl:apply-templates select="app:MenuItem"/>
		</isc:Items>
	</xsl:template>

	<xsl:template match="app:MenuItem">
		<!--<xsl:variable name="menuItem">-->
		<isc:MenuItemDyn>
			<xsl:choose>
				<xsl:when test="app:Name='NewRow'">
					<xsl:call-template name="NewRow"/>
				</xsl:when>
				<xsl:when test="app:Name='EditRow'">
					<xsl:call-template name="EditRow"/>
				</xsl:when>
				<xsl:when test="app:Name='CanReparentNodes'">
					<xsl:call-template name="CanReparentNodes"/>
				</xsl:when>
				<xsl:when test="app:Name='DeleteRows'">
					<xsl:call-template name="DeleteRows"/>
				</xsl:when>
				<xsl:when test="app:Name='RefreshAllRows'">
					<xsl:call-template name="RefreshAllRows"/>
				</xsl:when>
				<xsl:when test="app:Name='Separator'">
					<xsl:call-template name="MenuItemSeparator"/>
				</xsl:when>
				<xsl:when test="app:Name='SaveEdited'">
					<xsl:call-template name="SaveEdited"/>
				</xsl:when>
				<xsl:when test="app:Name='CancelEdited'">
					<xsl:call-template name="CancelEdited"/>
				</xsl:when>
				<xsl:when test="app:Name='SaveViewState'">
					<xsl:call-template name="SaveViewState"/>
				</xsl:when>
				<xsl:when test="app:Name='DeleteTab'">
					<xsl:call-template name="DeleteTab"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:message select="concat('Unknown MenuItem: ', app:Name)" terminate="yes"/>
				</xsl:otherwise>
			</xsl:choose>
		</isc:MenuItemDyn>
		<!--</xsl:variable>-->
		<!--<xsl:call-template name="merge:Merge">
                            <xsl:with-param name="nodes1" select="$menuItem/node()"/>
                            <xsl:with-param name="nodes2" select="app:MenuItem/app:Native/node()"/>
                        </xsl:call-template>-->
	</xsl:template>

	<xsl:template name="NewRow">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'newRow'"/>
			<xsl:with-param name="iconPath" select="'Actions-insert-link-icon.png'"/>
			<xsl:with-param name="keyTitle" select="'Ctrl+N'"/>
			<xsl:with-param name="keyName" select="'N'"/>
			<xsl:with-param name="ctrlKey" select="true()"/>
			<xsl:with-param name="title" select="'Новый'"/>
			<xsl:with-param name="ellipsis" select="true()"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="EditRow">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'editRow'"/>
			<xsl:with-param name="iconPath" select="'Actions-document-edit-icon.png'"/>
			<xsl:with-param name="keyTitle" select="'Ctrl+E'"/>
			<xsl:with-param name="keyName" select="'E'"/>
			<xsl:with-param name="ctrlKey" select="true()"/>
			<xsl:with-param name="title" select="'Изменить'"/>
			<xsl:with-param name="ellipsis" select="true()"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="CanReparentNodes">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'canReparentNodes'"/>
			<xsl:with-param name="title" select="'Разрешить перемещение группы'"/>
			<xsl:with-param name="ellipsis" select="true()"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="DeleteRows">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'removeRow'"/>
			<xsl:with-param name="iconPath" select="'Delete-icon.png'"/>
			<xsl:with-param name="keyTitle" select="'Ctrl+D'"/>
			<xsl:with-param name="keyName" select="'D'"/>
			<xsl:with-param name="ctrlKey" select="true()"/>
			<xsl:with-param name="title" select="'Удалить'"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="RefreshAllRows">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'refresh'"/>
			<xsl:with-param name="iconPath" select="'Refresh.png'"/>
			<xsl:with-param name="keyTitle" select="'Ctrl+R'"/>
			<xsl:with-param name="keyName" select="'R'"/>
			<xsl:with-param name="ctrlKey" select="true()"/>
			<xsl:with-param name="title" select="'Обновить'"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="SaveEdited">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'saveRecords'"/>
			<xsl:with-param name="iconPath" select="'Save-icon.png'"/>
			<xsl:with-param name="keyTitle" select="'Ctrl+S'"/>
			<xsl:with-param name="keyName" select="'S'"/>
			<xsl:with-param name="ctrlKey" select="true()"/>
			<xsl:with-param name="title" select="'Сохранить'"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="SaveViewState">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'saveViewState'"/>
			<xsl:with-param name="iconPath" select="'settings-icon.png'"/>
			<xsl:with-param name="keyTitle" select="'Ctrl+Alt+S'"/>
			<xsl:with-param name="keyName" select="'S'"/>
			<xsl:with-param name="ctrlKey" select="true()"/>
			<xsl:with-param name="altKey" select="true()"/>
			<xsl:with-param name="title" select="'Сохранить конфигурацию'"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="CancelEdited">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'canselEdited'"/>
			<xsl:with-param name="iconPath" select="'cancel-icon.png'"/>
			<xsl:with-param name="keyTitle" select="'Ctrl+Shift+R'"/>
			<xsl:with-param name="keyName" select="'R'"/>
			<xsl:with-param name="ctrlKey" select="true()"/>
			<xsl:with-param name="shiftKey" select="true()"/>
			<xsl:with-param name="title" select="'Отменить изменения'"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="DeleteTab">
		<xsl:call-template name="MenuItemNewRow">
			<xsl:with-param name="clickFunc" select="'deleteTab'"/>
			<xsl:with-param name="iconPath" select="'Windows-Close-Program-icon.png'"/>
			<xsl:with-param name="keyTitle" select="'Ctrl+T'"/>
			<xsl:with-param name="keyName" select="'T'"/>
			<xsl:with-param name="ctrlKey" select="true()"/>
			<xsl:with-param name="title" select="'Удалить вкладку'"/>
			<xsl:with-param name="enableIfPath" select="'enableDeleteTable'"/>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="MenuItemSeparator">
		<isc:IsSeparator>true</isc:IsSeparator>
	</xsl:template>

	<xsl:template name="MenuItemNewRow">
		<xsl:param as="xs:string" name="clickFunc"/>
		<xsl:param as="xs:string" name="functionsFile" tunnel="yes"/>
		<xsl:param as="xs:string" name="iconPath" select="''"/>
		<xsl:param as="xs:string" name="enableIfPath" select="''"/>
		<xsl:param as="xs:string" name="keyTitle" select="''"/>
		<xsl:param as="xs:string" name="keyName" select="''"/>
		<xsl:param as="xs:string" name="title"/>
		<xsl:param as="xs:string" name="arrayItemId" select="''"/>
		<xsl:param as="xs:boolean" name="ellipsis" select="false()"/>
		<xsl:param as="xs:boolean" name="altKey" select="false()"/>
		<xsl:param as="xs:boolean" name="ctrlKey" select="false()"/>
		<xsl:param as="xs:boolean" name="shiftKey" select="false()"/>

		<isc:Click>
			<isc:FunctionsFileURL>
				<xsl:value-of select="common:getRelativeOfPath($functionsFile)"/>
			</isc:FunctionsFileURL>
			<isc:FunctionName>
				<xsl:value-of select="$clickFunc"/>
			</isc:FunctionName>
		</isc:Click>
		<xsl:if test="not($enableIfPath='')">
			<isc:EnableIf>
				<isc:FunctionsFileURL>
					<xsl:value-of select="common:getRelativeOfPath($functionsFile)"/>
				</isc:FunctionsFileURL>
				<isc:FunctionName>
					<xsl:value-of select="$enableIfPath"/>
				</isc:FunctionName>
			</isc:EnableIf>
		</xsl:if>
		<xsl:if test="$iconPath">
			<isc:Icon>
				<xsl:value-of select="$iconPath"/>
			</isc:Icon>
		</xsl:if>
		<xsl:if test="not($arrayItemId='')">
			<isc:IdArrayItem>
				<xsl:value-of select="$arrayItemId"/>
			</isc:IdArrayItem>
		</xsl:if>
		<xsl:if test="$keyTitle">
			<isc:KeyTitle>
				<xsl:value-of select="$keyTitle"/>
			</isc:KeyTitle>
		</xsl:if>
		<xsl:call-template name="MenuKeys">
			<xsl:with-param name="keyName" select="$keyName"/>
			<xsl:with-param name="ctrlKey" select="$ctrlKey"/>
			<xsl:with-param name="altKey" select="$altKey"/>
			<xsl:with-param name="shiftKey" select="$shiftKey"/>
		</xsl:call-template>
		<isc:Title>
			<xsl:if test="$ellipsis=true()">
				<xsl:attribute name="Ellipsis" select="true()"/>
			</xsl:if>
			<xsl:value-of select="$title"/>
		</isc:Title>
	</xsl:template>

	<xsl:template name="MenuKeys">
		<xsl:param as="xs:string" name="keyName"/>
		<xsl:param as="xs:boolean" name="altKey" select="false()"/>
		<xsl:param as="xs:boolean" name="ctrlKey" select="false()"/>
		<xsl:param as="xs:boolean" name="shiftKey" select="false()"/>
		<xsl:if test="$keyName or $altKey=true() or $ctrlKey=true() or $shiftKey=true()">
			<isc:Keys>
				<isc:KeyIdentifier>
					<xsl:if test="$keyName">
						<isc:KeyName>
							<xsl:value-of select="$keyName"/>
						</isc:KeyName>
					</xsl:if>
					<xsl:if test="$altKey=true()">
						<isc:AltKey>true</isc:AltKey>
					</xsl:if>
					<xsl:if test="$ctrlKey=true()">
						<isc:CtrlKey>true</isc:CtrlKey>
					</xsl:if>
					<xsl:if test="$shiftKey=true()">
						<isc:ShiftKey>true</isc:ShiftKey>
					</xsl:if>
				</isc:KeyIdentifier>
			</isc:Keys>
		</xsl:if>
	</xsl:template>

	<xsl:template match="app:RootCanvas">
		<isc:RootCanvas>
			<xsl:apply-templates/>
		</isc:RootCanvas>
	</xsl:template>

	<xsl:template match="app:Identifier">
		<isc:Identifier>
			<xsl:value-of select="."/>
		</isc:Identifier>
	</xsl:template>

	<xsl:template name="dataSource">
		<xsl:param as="xs:string" name="BoName" tunnel="yes"/>
		<xsl:param as="xs:string" name="GroupName" tunnel="yes"/>
		<isc:DataSource>
			<xsl:choose>
				<xsl:when test="count(app:DataSourceElement/app:DataSourceIDREF) &gt; 0">
					<xsl:value-of select="concat(app:DataSourceElement/app:DataSourceIDREF, '_DS')"/>
				</xsl:when>
				<xsl:when test="count(app:DataSourceElement/app:DataSource/app:Identifier) &gt; 0">
					<xsl:value-of select="concat(app:DataSourceElement/app:DataSource/app:Identifier, '_DS')"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat($GroupName,'_',$BoName, '_DS')"/>
				</xsl:otherwise>
			</xsl:choose>
		</isc:DataSource>
	</xsl:template>

	<xsl:template name="FuncMenu">
		<xsl:param as="xs:string" name="BoName" tunnel="yes"/>
		<xsl:param as="xs:string" name="GroupName" tunnel="yes"/>
		<xsl:param as="xs:string" name="MenuName" tunnel="yes"/>
		<isc:FuncMenu>
			<xsl:choose>
				<xsl:when test="count(app:FuncMenuElement/app:MenuIDREF) &gt; 0">
					<xsl:value-of select="app:FuncMenuElement/app:MenuIDREF"/>
				</xsl:when>
				<xsl:when test="count(app:FuncMenuElement/app:Menu/app:Identifier) &gt; 0">
					<xsl:value-of select="app:FuncMenuElement/app:Menu/app:Identifier"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$MenuName"/>
				</xsl:otherwise>
			</xsl:choose>
		</isc:FuncMenu>
	</xsl:template>

	<xsl:template name="GetSelectionType">
		<xsl:param as="xs:string" name="SelectionType"/>
		<xsl:choose>
			<xsl:when test="$SelectionType = 'Multiple'">
				<xsl:value-of select="'slStlMultiple'"/>
			</xsl:when>
			<xsl:when test="$SelectionType = 'Single'">
				<xsl:value-of select="'slStlSingle'"/>
			</xsl:when>
			<xsl:when test="$SelectionType = 'Simple'">
				<xsl:value-of select="'slStlSimple'"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:message select="concat('Unknown app:SelectionType: ', $SelectionType)" terminate="yes"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="app:ListGridEditor">
		<xsl:param as="xs:string" name="BoName" tunnel="yes"/>
		<xsl:param as="xs:string" name="GroupName" tunnel="yes"/>
		<xsl:param as="xs:string" name="MenuName" tunnel="yes"/>

		<!--<xsl:variable name="listGridEditor">-->
		<isc:ListGridEditorDyn>
			<xsl:if test="app:Identifier">
				<isc:Identifier>
					<xsl:value-of select="app:Identifier"/>
				</isc:Identifier>
			</xsl:if>
			<isc:useSelfName>
				<xsl:choose>
					<xsl:when test="app:useSelfName=true()">
						<xsl:value-of select="true()"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="false()"/>
					</xsl:otherwise>
				</xsl:choose>
			</isc:useSelfName>
			<isc:ContextMenu>
				<xsl:value-of select="$MenuName"/>
			</isc:ContextMenu>
			<isc:AutoFetchData>
				<xsl:choose>
					<xsl:when test="app:AutoFetchData">
						<xsl:value-of select="app:AutoFetchData"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:AutoFetchData>
			<isc:AutoFetchTextMatchStyle>
				<xsl:choose>
					<xsl:when test="app:TextMatchStyle">
						<xsl:value-of select="app:TextMatchStyle"/>
					</xsl:when>
					<xsl:otherwise>txtMchStyleSubstring</xsl:otherwise>
				</xsl:choose>
			</isc:AutoFetchTextMatchStyle>
			<isc:AutoFitWidthApproach>
				<xsl:choose>
					<xsl:when test="app:AutoFitWidthApproach">
						<xsl:choose>
							<xsl:when test="app:AutoFitWidthApproach='both'">
								<xsl:value-of select="'aftWdAprchBoth'"/>
							</xsl:when>
							<xsl:when test="app:AutoFitWidthApproach='title'">
								<xsl:value-of select="'aftWdAprchTitle'"/>
							</xsl:when>
							<xsl:when test="app:AutoFitWidthApproach='value'">
								<xsl:value-of select="'aftWdAprchValue'"/>
							</xsl:when>
						</xsl:choose>
					</xsl:when>
					<xsl:otherwise>aftWdAprchBoth</xsl:otherwise>
				</xsl:choose>
			</isc:AutoFitWidthApproach>
			<isc:AutoFitFieldWidths>
				<xsl:choose>
					<xsl:when test="app:AutoFitFieldWidths">
						<xsl:value-of select="app:AutoFitFieldWidths"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:AutoFitFieldWidths>
			<isc:AutoSaveEdits>
				<xsl:choose>
					<xsl:when test="app:AutoSaveEdits">
						<xsl:value-of select="app:AutoSaveEdits"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:AutoSaveEdits>
			<isc:CanEdit>
				<xsl:choose>
					<xsl:when test="app:CanEdit">
						<xsl:value-of select="app:CanEdit"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:CanEdit>
			<isc:CanSelectCells>
				<xsl:value-of select="app:CanSelectCells"/>
			</isc:CanSelectCells>
			<xsl:variable as="node()*" select="." name="bo"/>
			<xsl:call-template name="app:PagingData">
				<xsl:with-param name="bo" as="node()*" select="$bo"/>
			</xsl:call-template>

			<xsl:if test="app:InitialSort">
				<isc:InitialSort>
					<xsl:call-template name="app:setInitialSort">
						<xsl:with-param name="bo" as="node()*" select="$bo"/>
					</xsl:call-template>
				</isc:InitialSort>
			</xsl:if>
			<isc:EditByCell>
				<xsl:choose>
					<xsl:when test="app:EditByCell">
						<xsl:value-of select="app:EditByCell"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:EditByCell>
			<isc:FetchDelay>
				<xsl:choose>
					<xsl:when test="app:FetchDelay">
						<xsl:value-of select="app:FetchDelay"/>
					</xsl:when>
					<xsl:otherwise>300</xsl:otherwise>
				</xsl:choose>
			</isc:FetchDelay>
			<isc:FilterOnKeypress>
				<xsl:choose>
					<xsl:when test="app:FilterOnKeypress">
						<xsl:value-of select="app:FetchDelay"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:FilterOnKeypress>
			<xsl:if test="count(./app:Fields) &gt; 0">
				<isc:Fields>
					<xsl:for-each select="./app:Fields/app:Field">
						<isc:ListGridFieldDyn>
							<xsl:if test="app:Hidden">
								<isc:Hidden>
									<xsl:value-of select="app:Hidden"/>
								</isc:Hidden>
							</xsl:if>
							<isc:Name>
								<xsl:value-of select="app:Name"/>
							</isc:Name>
						</isc:ListGridFieldDyn>
					</xsl:for-each>
				</isc:Fields>
			</xsl:if>
			<xsl:if test="count(./app:DefaultFields) &gt; 0">
				<isc:DefaultFields>
					<xsl:for-each select="./app:DefaultFields/app:Field">
						<isc:ListGridFieldDyn>
							<xsl:if test="app:Hidden">
								<isc:Hidden>
									<xsl:value-of select="app:Hidden"/>
								</isc:Hidden>
							</xsl:if>
							<isc:Name>
								<xsl:value-of select="app:Name"/>
							</isc:Name>
						</isc:ListGridFieldDyn>
					</xsl:for-each>
				</isc:DefaultFields>
			</xsl:if>
			<xsl:if test="app:RecordComponentPoolingMode">
				<isc:RecordComponentPoolingMode>
					<xsl:value-of select="app:GetRecordComponentPoolingMode(app:RecordComponentPoolingMode)"/>
				</isc:RecordComponentPoolingMode>
			</xsl:if>
			<isc:SelectionType>
				<xsl:choose>
					<xsl:when test="app:SelectionType">
						<xsl:call-template name="GetSelectionType">
							<xsl:with-param name="SelectionType" as="xs:string" select="app:SelectionType"/>
						</xsl:call-template>
					</xsl:when>
					<xsl:otherwise>slStlMultiple</xsl:otherwise>
				</xsl:choose>
			</isc:SelectionType>
			<isc:ShowFilterEditor>
				<xsl:choose>
					<xsl:when test="app:ShowFilterEditor">
						<xsl:value-of select="app:ShowFilterEditor"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:ShowFilterEditor>
			<xsl:if test="app:ShowRecordComponents">
				<isc:ShowRecordComponents>
					<xsl:value-of select="app:ShowRecordComponents"/>
				</isc:ShowRecordComponents>
			</xsl:if>
			<xsl:if test="app:ShowRecordComponentsByCell">
				<isc:ShowRecordComponentsByCell>
					<xsl:value-of select="app:ShowRecordComponentsByCell"/>
				</isc:ShowRecordComponentsByCell>
			</xsl:if>
			<xsl:call-template name="dataSource"/>
			<xsl:call-template name="FuncMenu"/>
			<isc:ShowAdvancedFilter>
				<xsl:choose>
					<xsl:when test="app:ShowAdvancedFilter">
						<xsl:value-of select="app:ShowAdvancedFilter"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:ShowAdvancedFilter>
			<isc:WrapCells>
				<xsl:choose>
					<xsl:when test="app:WrapCells">
						<xsl:value-of select="app:WrapCells"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:WrapCells>
			<isc:InitWidget>
				<isc:FunctionsFileURL>
					<xsl:value-of select="concat(common:getRelativeOfPath($_jsDir),'MenuItemsFunctions.js/')"/>
				</isc:FunctionsFileURL>
				<isc:FunctionName>initListGridEditor</isc:FunctionName>
			</isc:InitWidget>
		</isc:ListGridEditorDyn>
		<!--</xsl:variable>-->
		<!--<xsl:call-template name="merge:Merge">
                            <xsl:with-param name="nodes1" select="$listGridEditor/node()"/>
                            <xsl:with-param name="nodes2" select="app:ListGridEditor/app:Native/node()"/>
                        </xsl:call-template>-->
	</xsl:template>

	<xsl:template match="app:TreeGridEditor">
		<xsl:param as="xs:string" name="MenuName" tunnel="yes"/>

		<!--<xsl:variable name="treeGridEditor">-->
		<isc:TreeGridEditorDyn>
			<xsl:if test="app:Identifier">
				<isc:Identifier>
					<xsl:value-of select="app:Identifier"/>
				</isc:Identifier>
			</xsl:if>
			<isc:useSelfName>
				<xsl:choose>
					<xsl:when test="app:useSelfName=true()">
						<xsl:value-of select="true()"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="false()"/>
					</xsl:otherwise>
				</xsl:choose>
			</isc:useSelfName>
			<isc:ContextMenu>
				<xsl:value-of select="$MenuName"/>
			</isc:ContextMenu>
			<isc:AutoFetchData>
				<xsl:choose>
					<xsl:when test="app:AutoFetchData">
						<xsl:value-of select="app:AutoFetchData"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:AutoFetchData>
			<xsl:if test="app:CanAcceptDroppedRecords=true()">
				<isc:CanAcceptDroppedRecords>true</isc:CanAcceptDroppedRecords>
			</xsl:if>
			<isc:AutoSaveEdits>
				<xsl:choose>
					<xsl:when test="app:AutoSaveEdits">
						<xsl:value-of select="app:AutoSaveEdits"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:AutoSaveEdits>
			<isc:CanEdit>
				<xsl:choose>
					<xsl:when test="app:CanEdit">
						<xsl:value-of select="app:CanEdit"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:CanEdit>
			<xsl:if test="app:CanReorderRecords=true()">
				<isc:CanReorderRecords>true</isc:CanReorderRecords>
			</xsl:if>
			<isc:CanSelectCells>
				<xsl:value-of select="app:CanSelectCells"/>
			</isc:CanSelectCells>
			<xsl:variable as="node()*" select="." name="bo"/>
			<xsl:call-template name="app:PagingData">
				<xsl:with-param name="bo" as="node()*" select="$bo"/>
			</xsl:call-template>

			<xsl:if test="app:InitialSort">
				<isc:InitialSort>
					<xsl:call-template name="app:setInitialSort">
						<xsl:with-param name="bo" as="node()*" select="$bo"/>
					</xsl:call-template>
				</isc:InitialSort>
			</xsl:if>
			<isc:EditByCell>
				<xsl:choose>
					<xsl:when test="app:EditByCell">
						<xsl:value-of select="app:EditByCell"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:EditByCell>
			<isc:SelectionType>
				<xsl:choose>
					<xsl:when test="app:SelectionType">
						<xsl:call-template name="GetSelectionType">
							<xsl:with-param name="SelectionType" as="xs:string" select="app:SelectionType"/>
						</xsl:call-template>
					</xsl:when>
					<xsl:otherwise>slStlMultiple</xsl:otherwise>
				</xsl:choose>
			</isc:SelectionType>
			<!--<isc:ClosedIconSuffix></isc:ClosedIconSuffix>-->
			<xsl:call-template name="dataSource"/>
			<!--<isc:DropIconSuffix></isc:DropIconSuffix>-->
			<xsl:if test="count(./app:Fields) &gt; 0">
				<isc:Fields>
					<xsl:for-each select="./app:Fields/app:Field">
						<isc:TreeGridFieldDyn>
							<xsl:if test="app:Hidden">
								<isc:Hidden>
									<xsl:value-of select="app:Hidden"/>
								</isc:Hidden>
							</xsl:if>
							<isc:Name>
								<xsl:value-of select="app:Name"/>
							</isc:Name>
						</isc:TreeGridFieldDyn>
					</xsl:for-each>
				</isc:Fields>
			</xsl:if>
			<xsl:if test="count(./app:DefaultFields) &gt; 0">
				<isc:DefaultFields>
					<xsl:for-each select="./app:DefaultFields/app:Field">
						<isc:TreeGridFieldDyn>
							<xsl:if test="app:Hidden">
								<isc:Hidden>
									<xsl:value-of select="app:Hidden"/>
								</isc:Hidden>
							</xsl:if>
							<isc:Name>
								<xsl:value-of select="app:Name"/>
							</isc:Name>
						</isc:TreeGridFieldDyn>
					</xsl:for-each>
				</isc:DefaultFields>
			</xsl:if>
			<xsl:if test="count(app:FolderIcon)&gt;0">
				<isc:FolderIcon>
					<xsl:value-of select="app:FolderIcon"/>
				</isc:FolderIcon>
			</xsl:if>
			<xsl:if test="count(app:NodeIcon)&gt;0">
				<isc:NodeIcon>
					<xsl:value-of select="app:NodeIcon"/>
				</isc:NodeIcon>
			</xsl:if>
			<!--<isc:OpenIconSuffix></isc:OpenIconSuffix>-->
			<isc:CanReparentNodes>
				<xsl:choose>
					<xsl:when test="app:CanReparentNodes=true()">true</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:CanReparentNodes>
			<xsl:call-template name="FuncMenu"/>
			<isc:InitWidget>
				<isc:FunctionsFileURL>
					<xsl:value-of select="concat(common:getRelativeOfPath($_jsDir),'MenuItemsFunctions.js/')"/>
				</isc:FunctionsFileURL>
				<isc:FunctionName>initTreeGridEditor</isc:FunctionName>
			</isc:InitWidget>
		</isc:TreeGridEditorDyn>
		<!--</xsl:variable>-->
		<!--<xsl:call-template name="merge:Merge">
                            <xsl:with-param name="nodes1" select="$treeGridEditor/node()"/>
                            <xsl:with-param name="nodes2" select="app:TreeGridEditor/app:Native/node()"/>
                        </xsl:call-template>-->
	</xsl:template>

	<xsl:template match="app:TreeListGridEditor">
		<xsl:param as="xs:string" name="BoName" tunnel="yes"/>
		<xsl:param as="xs:string" name="GroupName" tunnel="yes"/>
		<xsl:param as="xs:string" name="MenuName" tunnel="yes"/>
		<!--<xsl:variable name="treeListGridEditor">-->
		<isc:TreeListGridEditorDyn>
			<xsl:if test="app:Identifier">
				<isc:Identifier>
					<xsl:value-of select="app:Identifier"/>
				</isc:Identifier>
			</xsl:if>
			<isc:useSelfName>
				<xsl:choose>
					<xsl:when test="app:useSelfName=true()">
						<xsl:value-of select="true()"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="false()"/>
					</xsl:otherwise>
				</xsl:choose>
			</isc:useSelfName>
			<isc:AutoFetchData>
				<xsl:choose>
					<xsl:when test="app:AutoFetchData">
						<xsl:value-of select="app:AutoFetchData"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:AutoFetchData>
			<isc:AutoFetchTextMatchStyleList>
				<xsl:choose>
					<xsl:when test="app:TextMatchStyleList">
						<xsl:value-of select="app:TextMatchStyleList"/>
					</xsl:when>
					<xsl:otherwise>txtMchStyleSubstring</xsl:otherwise>
				</xsl:choose>
			</isc:AutoFetchTextMatchStyleList>
			<isc:AutoFetchTextMatchStyleTree>
				<xsl:choose>
					<xsl:when test="app:TextMatchStyleTree">
						<xsl:value-of select="app:TextMatchStyleTree"/>
					</xsl:when>
					<xsl:otherwise>txtMchStyleSubstring</xsl:otherwise>
				</xsl:choose>
			</isc:AutoFetchTextMatchStyleTree>
			<isc:AutoSaveListEdits>
				<xsl:choose>
					<xsl:when test="app:AutoSaveListEdits">
						<xsl:value-of select="app:AutoSaveListEdits"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:AutoSaveListEdits>
			<isc:AutoSaveTreeEdits>
				<xsl:choose>
					<xsl:when test="app:AutoSaveTreeEdits">
						<xsl:value-of select="app:AutoSaveTreeEdits"/>
					</xsl:when>
					<xsl:otherwise>false</xsl:otherwise>
				</xsl:choose>
			</isc:AutoSaveTreeEdits>
			<xsl:if test="app:CanEditList">
				<isc:CanEditList>
					<xsl:value-of select="app:CanEditList"/>
				</isc:CanEditList>
			</xsl:if>
			<xsl:if test="app:CanEditTree">
				<isc:CanEditTree>
					<xsl:value-of select="app:CanEditTree"/>
				</isc:CanEditTree>
			</xsl:if>
			<xsl:if test="app:CanSelectCellsList">
				<isc:CanSelectCellsList>
					<xsl:value-of select="app:CanSelectCellsList"/>
				</isc:CanSelectCellsList>
			</xsl:if>
			<xsl:if test="app:CanSelectCellsTree">
				<isc:CanSelectCellsTree>
					<xsl:value-of select="app:CanSelectCellsTree"/>
				</isc:CanSelectCellsTree>
			</xsl:if>
			<isc:ContextMenuListGridEditor>
				<xsl:value-of select="concat($MenuName,'_List')"/>
			</isc:ContextMenuListGridEditor>
			<isc:ContextMenuTreeGridEditor>
				<xsl:value-of select="concat($MenuName,'_Tree')"/>
			</isc:ContextMenuTreeGridEditor>
			<xsl:variable as="node()*" select="." name="bo"/>
			<xsl:call-template name="app:PagingDataList">
				<xsl:with-param name="bo" as="node()*" select="$bo"/>
			</xsl:call-template>
			<xsl:call-template name="app:PagingDataTree">
				<xsl:with-param name="bo" as="node()*" select="$bo"/>
			</xsl:call-template>
			<xsl:if test="app:InitialSortList">
				<isc:InitialSortList>
					<xsl:call-template name="app:setInitialSortList">
						<xsl:with-param name="bo" as="node()*" select="$bo"/>
					</xsl:call-template>
				</isc:InitialSortList>
			</xsl:if>
			<xsl:if test="app:InitialSortTree">
				<isc:InitialSortTree>
					<xsl:call-template name="app:setInitialSortTree">
						<xsl:with-param name="bo" as="node()*" select="$bo"/>
					</xsl:call-template>
				</isc:InitialSortTree>
			</xsl:if>
			<isc:DataSourceList>
				<xsl:choose>
					<xsl:when test="count(app:ListGridDataSourceElement/app:DataSourceIDREF) &gt; 0">
						<xsl:value-of select="app:ListGridDataSourceElement/app:DataSourceIDREF"/>
					</xsl:when>
					<xsl:when test="count(app:ListGridDataSourceElement/app:DataSource/app:Identifier) &gt; 0">
						<xsl:value-of select="app:ListGridDataSourceElement/app:DataSource/app:Identifier"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="concat($GroupName,'_',$BoName)"/>
					</xsl:otherwise>
				</xsl:choose>
			</isc:DataSourceList>
			<isc:DataSourceTree>
				<xsl:choose>
					<xsl:when test="count(app:TreeGridDataSourceElement/app:DataSourceIDREF) &gt; 0">
						<xsl:value-of select="app:TreeGridDataSourceElement/app:DataSourceIDREF"/>
					</xsl:when>
					<xsl:when test="count(app:TreeGridDataSourceElement/app:DataSource/app:Identifier) &gt; 0">
						<xsl:value-of select="app:TreeGridDataSourceElement/app:DataSource/app:Identifier"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="concat($GroupName,'_',$BoName)"/>
					</xsl:otherwise>
				</xsl:choose>
			</isc:DataSourceTree>
			<xsl:if test="count(app:ListGridFields/app:Field) &gt; 0">
				<isc:FieldsList>
					<xsl:for-each select="app:ListGridFields/app:Field">
						<isc:ListGridFieldDyn>
							<isc:Name>
								<xsl:variable name="boAttr" as="node()*" select="app:getBOAttr(app:Name, $GroupName, $BoName)"/>
								<!--<xsl:variable as="xs:string" name="fieldName" select="app:getFieldName($boAttr/@name, $boAttr/@type)"/>-->
								<xsl:variable as="xs:string" name="fieldName" select="$boAttr/@name"/>
								<xsl:value-of select="$fieldName"/>
							</isc:Name>
						</isc:ListGridFieldDyn>
					</xsl:for-each>
				</isc:FieldsList>
			</xsl:if>
			<xsl:if test="count(app:ListGridDefaultFields/app:Field) &gt; 0">
				<isc:DefaultFieldsList>
					<xsl:for-each select="app:ListGridDefaultFields/app:Field">
						<isc:ListGridFieldDyn>
							<isc:Name>
								<xsl:variable name="boAttr" as="node()*" select="app:getBOAttr(app:Name, $GroupName, $BoName)"/>
								<!--<xsl:variable as="xs:string" name="fieldName" select="app:getFieldName($boAttr/@name, $boAttr/@type)"/>-->
								<xsl:variable as="xs:string" name="fieldName" select="$boAttr/@name"/>
								<xsl:value-of select="$fieldName"/>
							</isc:Name>
						</isc:ListGridFieldDyn>
					</xsl:for-each>
				</isc:DefaultFieldsList>
			</xsl:if>
			<xsl:if test="count(app:TreeGridFields/app:Field) &gt; 0">
				<isc:FieldsTree>
					<xsl:for-each select="app:TreeGridFields/app:Field">
						<isc:TreeGridFieldDyn>
							<isc:Name>
								<xsl:variable name="boAttr" as="node()*" select="app:getBOAttr(app:Name, $GroupName, $BoName)"/>
								<!--<xsl:variable as="xs:string" name="fieldName" select="app:getFieldName($boAttr/@name, $boAttr/@type)"/>-->
								<xsl:variable as="xs:string" name="fieldName" select="$boAttr/@name"/>
								<xsl:value-of select="$fieldName"/>
							</isc:Name>
						</isc:TreeGridFieldDyn>
					</xsl:for-each>
				</isc:FieldsTree>
			</xsl:if>
			<xsl:if test="count(app:TreeGridDefaultFields/app:Field) &gt; 0">
				<isc:DefaultFieldsTree>
					<xsl:for-each select="app:TreeGridDefaultFields/app:Field">
						<isc:TreeGridFieldDyn>
							<isc:Name>
								<xsl:variable name="boAttr" as="node()*" select="app:getBOAttr(app:Name, $GroupName, $BoName)"/>
								<!--<xsl:variable as="xs:string" name="fieldName" select="app:getFieldName($boAttr/@name, $boAttr/@type)"/>-->
								<xsl:variable as="xs:string" name="fieldName" select="$boAttr/@name"/>
								<xsl:value-of select="$fieldName"/>
							</isc:Name>
						</isc:TreeGridFieldDyn>
					</xsl:for-each>
				</isc:DefaultFieldsTree>
			</xsl:if>
			<isc:FetchListDelay>
				<xsl:choose>
					<xsl:when test="app:FetchListDelay">
						<xsl:value-of select="app:FetchListDelay"/>
					</xsl:when>
					<xsl:otherwise>500</xsl:otherwise>
				</xsl:choose>
			</isc:FetchListDelay>
			<isc:FetchTreeDelay>
				<xsl:choose>
					<xsl:when test="app:FetchTreeDelay">
						<xsl:value-of select="app:FetchTreeDelay"/>
					</xsl:when>
					<xsl:otherwise>500</xsl:otherwise>
				</xsl:choose>
			</isc:FetchTreeDelay>
			<isc:FilterListOnKeypress>
				<xsl:choose>
					<xsl:when test="app:FilterListOnKeypress">
						<xsl:value-of select="app:FilterListOnKeypress"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:FilterListOnKeypress>
			<isc:FilterTreeOnKeypress>
				<xsl:choose>
					<xsl:when test="app:FilterTreeOnKeypress">
						<xsl:value-of select="app:FilterTreeOnKeypress"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:FilterTreeOnKeypress>
			<isc:FolderDropImageTree>
				<xsl:value-of select="app:FolderDropImageTree"/>
			</isc:FolderDropImageTree>
			<isc:FolderIconTree>
				<xsl:value-of select="app:FolderIconTree"/>
			</isc:FolderIconTree>
			<isc:FuncMenu>
				<xsl:value-of select="$MenuName"/>
			</isc:FuncMenu>
			<!--<isc:InitWidget>
                <isc:FunctionsFileURL>
                  <xsl:value-of select="concat(common:getRelativeOfPath($_jsDir),'MenuItemsFunctions.js/')"/>
                </isc:FunctionsFileURL>
                <isc:FunctionName>initTreeListGridEditor</isc:FunctionName>
              </isc:InitWidget>-->
			<isc:NodeIconTree>
				<xsl:value-of select="app:NodeIconTree"/>
			</isc:NodeIconTree>
			<xsl:if test="app:ShowListAdvancedFilter=true()">
				<isc:ShowListAdvancedFilter>true</isc:ShowListAdvancedFilter>
			</xsl:if>
			<xsl:if test="app:ShowTreeAdvancedFilter=true()">
				<isc:ShowTreeAdvancedFilter>true</isc:ShowTreeAdvancedFilter>
			</xsl:if>
			<xsl:if test="app:ShowListFilterEditor=true()">
				<isc:ShowListFilterEditor>true</isc:ShowListFilterEditor>
			</xsl:if>
			<xsl:if test="app:ShowTreeFilterEditor=true()">
				<isc:ShowTreeFilterEditor>true</isc:ShowTreeFilterEditor>
			</xsl:if>
			<xsl:if test="app:ShowListRecordComponents=true()">
				<isc:ShowListRecordComponents>true</isc:ShowListRecordComponents>
			</xsl:if>
			<xsl:if test="app:ShowTreeRecordComponents=true()">
				<isc:ShowTreeRecordComponents>true</isc:ShowTreeRecordComponents>
			</xsl:if>
			<xsl:if test="app:ShowListRecordComponentsByCell=true()">
				<isc:ShowListRecordComponentsByCell>true</isc:ShowListRecordComponentsByCell>
			</xsl:if>
			<xsl:if test="app:ShowTreeRecordComponentsByCell=true()">
				<isc:ShowTreeRecordComponentsByCell>true</isc:ShowTreeRecordComponentsByCell>
			</xsl:if>
			<xsl:if test="app:WrapListCells=true()">
				<isc:WrapListCells>true</isc:WrapListCells>
			</xsl:if>
			<xsl:if test="app:WrapTreeCells=true()">
				<isc:WrapTreeCells>true</isc:WrapTreeCells>
			</xsl:if>
		</isc:TreeListGridEditorDyn>
		<!--</xsl:variable>-->
		<!--<xsl:call-template name="merge:Merge">
                            <xsl:with-param name="nodes1" select="$treeListGridEditor/node()"/>
                            <xsl:with-param name="nodes2" select="app:TreeListGridEditor/app:Native/node()"/>
                        </xsl:call-template>-->
	</xsl:template>

	<xsl:template name="app:PagingData">
		<xsl:param as="node()*" name="bo"/>

		<xsl:if test="$bo/app:DataFetchMode">
			<isc:DataFetchMode>
				<xsl:value-of select="$bo/app:DataFetchMode"/>
			</isc:DataFetchMode>
		</xsl:if>
		<xsl:if test="$bo/app:DataPageSize">
			<isc:DataPageSize>
				<xsl:value-of select="$bo/app:DataPageSize"/>
			</isc:DataPageSize>
		</xsl:if>
		<xsl:if test="$bo/app:DrawAheadRatio">
			<isc:DrawAheadRatio>
				<xsl:value-of select="$bo/app:DrawAheadRatio"/>
			</isc:DrawAheadRatio>
		</xsl:if>
	</xsl:template>

	<xsl:template name="app:PagingDataTree">
		<xsl:param as="node()*" name="bo"/>

		<xsl:if test="$bo/app:DataFetchModeTree">
			<isc:DataFetchModeTree>
				<xsl:value-of select="$bo/app:DataFetchModeTree"/>
			</isc:DataFetchModeTree>
		</xsl:if>
		<xsl:if test="$bo/app:DataPageSizeTree">
			<isc:DataPageSizeTree>
				<xsl:value-of select="$bo/app:DataPageSizeTree"/>
			</isc:DataPageSizeTree>
		</xsl:if>
		<xsl:if test="$bo/app:DrawAheadRatioTree">
			<isc:DrawAheadRatioTree>
				<xsl:value-of select="$bo/app:DrawAheadRatioTree"/>
			</isc:DrawAheadRatioTree>
		</xsl:if>
	</xsl:template>

	<xsl:template name="app:PagingDataList">
		<xsl:param as="node()*" name="bo"/>

		<xsl:if test="$bo/app:DataFetchModeList">
			<isc:DataFetchModeList>
				<xsl:value-of select="$bo/app:DataFetchModeList"/>
			</isc:DataFetchModeList>
		</xsl:if>
		<xsl:if test="$bo/app:DataPageSizeList">
			<isc:DataPageSizeList>
				<xsl:value-of select="$bo/app:DataPageSizeList"/>
			</isc:DataPageSizeList>
		</xsl:if>
		<xsl:if test="$bo/app:DrawAheadRatioList">
			<isc:DrawAheadRatioList>
				<xsl:value-of select="$bo/app:DrawAheadRatioList"/>
			</isc:DrawAheadRatioList>
		</xsl:if>
	</xsl:template>

	<xsl:template name="app:setInitialSort">
		<xsl:param as="node()*" name="bo"/>

		<xsl:for-each select="app:InitialSort/app:SortSpecifier">
			<isc:SortSpecifierDyn>
				<isc:Property>
					<xsl:value-of select="app:Property"/>
				</isc:Property>
				<xsl:if test="app:SortDirection">
					<isc:Direction>
						<xsl:value-of select="app:SortDirection"/>
					</isc:Direction>
				</xsl:if>
			</isc:SortSpecifierDyn>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="app:setInitialSortList">
		<xsl:param as="node()*" name="bo"/>

		<xsl:for-each select="app:InitialSortList/app:SortSpecifier">
			<isc:SortSpecifierDyn>
				<isc:Property>
					<xsl:value-of select="app:Property"/>
				</isc:Property>
				<xsl:if test="app:SortDirection">
					<isc:Direction>
						<xsl:value-of select="app:SortDirection"/>
					</isc:Direction>
				</xsl:if>
			</isc:SortSpecifierDyn>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="app:setInitialSortTree">
		<xsl:param as="node()*" name="bo"/>

		<xsl:for-each select="app:InitialSortTree/app:SortSpecifier">
			<isc:SortSpecifierDyn>
				<isc:Property>
					<xsl:value-of select="app:Property"/>
				</isc:Property>
				<xsl:if test="app:SortDirection">
					<isc:Direction>
						<xsl:value-of select="app:SortDirection"/>
					</isc:Direction>
				</xsl:if>
			</isc:SortSpecifierDyn>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="app:HLayout">
		<isc:HLayoutSSDyn>
			<xsl:if test="app:Identifier">
				<isc:Identifier>
					<xsl:value-of select="app:Identifier"/>
				</isc:Identifier>
			</xsl:if>
			<isc:useSelfName>true</isc:useSelfName>
			<isc:AnimateMembers>
				<xsl:choose>
					<xsl:when test="app:AnimateMembers">
						<xsl:value-of select="app:AnimateMembers"/>
					</xsl:when>
					<xsl:otherwise>true</xsl:otherwise>
				</xsl:choose>
			</isc:AnimateMembers>
			<xsl:if test="app:AnimateMembers">
				<isc:LayoutMargin>
					<xsl:value-of select="isc:AnimateMembers"/>
				</isc:LayoutMargin>
			</xsl:if>
			<xsl:apply-templates/>
		</isc:HLayoutSSDyn>
	</xsl:template>

	<xsl:template match="app:Members">
		<isc:Members>
			<xsl:apply-templates/>
		</isc:Members>
	</xsl:template>

	<xsl:template match="app:Members">
		<isc:Members>
			<xsl:apply-templates/>
		</isc:Members>
	</xsl:template>

	<xsl:template match="app:Pane">
		<isc:Pane>
			<isc:useSelfName>true</isc:useSelfName>
			<isc:Children>
				<xsl:apply-templates/>
			</isc:Children>
		</isc:Pane>
	</xsl:template>

	<xsl:template match="app:ListGrid">
		<isc:ListGridDyn>
			<isc:useSelfName>true</isc:useSelfName>
			<xsl:apply-templates/>
		</isc:ListGridDyn>
	</xsl:template>

	<xsl:template match="app:VLayout">
		<isc:VLayoutSSDyn>
			<isc:useSelfName>true</isc:useSelfName>
			<xsl:apply-templates/>
		</isc:VLayoutSSDyn>
	</xsl:template>

	<xsl:template match="app:HLayout">
		<isc:HLayoutSSDyn>
			<isc:useSelfName>true</isc:useSelfName>
			<xsl:apply-templates/>
		</isc:HLayoutSSDyn>
	</xsl:template>

	<xsl:template match="app:Tab">
		<isc:TabDyn>
			<xsl:apply-templates/>
		</isc:TabDyn>
	</xsl:template>

	<xsl:template match="app:DynamicForm">
		<isc:DynamicFormDyn>
			<isc:useSelfName>true</isc:useSelfName>
			<xsl:apply-templates select="*" mode="DynamicForm"/>
		</isc:DynamicFormDyn>
	</xsl:template>

	<xsl:template match="app:Field" mode="DynamicForm">
		<isc:FormItemDyn>
			<isc:Name>
				<xsl:value-of select="@name"/>
			</isc:Name>
			<xsl:if test="@rowSpan">
				<isc:RowSpan>
					<xsl:value-of select="@rowSpan"/>
				</isc:RowSpan>
			</xsl:if>
			<xsl:if test="@colSpan">
				<isc:ColSpan>
					<xsl:value-of select="@colSpan"/>
				</isc:ColSpan>
			</xsl:if>
			<xsl:if test="@startRow">
				<isc:StartRow>
					<xsl:value-of select="@startRow"/>
				</isc:StartRow>
			</xsl:if>
			<xsl:if test="@endRow">
				<isc:EndRow>
					<xsl:value-of select="@endRow"/>
				</isc:EndRow>
			</xsl:if>
		</isc:FormItemDyn>
	</xsl:template>

	<xsl:template match="app:Fields" mode="DynamicForm">
		<isc:Fields>
			<xsl:apply-templates select="*" mode="DynamicForm"/>
		</isc:Fields>
	</xsl:template>

	<xsl:template match="app:TabSet">
		<isc:TabSetDyn>
			<isc:useSelfName>true</isc:useSelfName>
			<xsl:apply-templates/>
		</isc:TabSetDyn>
	</xsl:template>

	<xsl:template match="app:TabSetSS">
		<isc:TabSetSSDyn>
			<isc:useSelfName>true</isc:useSelfName>
			<xsl:apply-templates/>
		</isc:TabSetSSDyn>
	</xsl:template>

	<xsl:template match="app:Tabs">
		<isc:Tabs>
			<xsl:apply-templates/>
		</isc:Tabs>
	</xsl:template>

	<xsl:template match="app:Width" mode="#all">
		<isc:Width>
			<xsl:value-of select="."/>
		</isc:Width>
	</xsl:template>

	<xsl:template match="app:Height" mode="#all">
		<isc:Height>
			<xsl:value-of select="."/>
		</isc:Height>
	</xsl:template>

	<xsl:template match="app:DefaultWidth" mode="#all">
		<isc:DefaultWidth>
			<xsl:value-of select="."/>
		</isc:DefaultWidth>
	</xsl:template>

	<xsl:template match="app:DefaultHeight" mode="#all">
		<isc:DefaultHeight>
			<xsl:value-of select="."/>
		</isc:DefaultHeight>
	</xsl:template>

	<xsl:template match="app:NumCols" mode="#all">
		<isc:NumCols>
			<xsl:value-of select="."/>
		</isc:NumCols>
	</xsl:template>

	<xsl:template match="app:NumRows" mode="#all">
		<isc:NumRows>
			<xsl:value-of select="."/>
		</isc:NumRows>
	</xsl:template>

	<xsl:template match="app:SelectedTab">
		<isc:SelectedTab>
			<xsl:value-of select="."/>
		</isc:SelectedTab>
	</xsl:template>

	<xsl:template match="app:Title" mode="#all">
		<isc:Title>
			<xsl:value-of select="."/>
		</isc:Title>
	</xsl:template>

	<xsl:template match="app:TitleOrientation" mode="#all">
		<isc:TitleOrientation>
			<xsl:choose>
				<xsl:when test=".='top'">
					<xsl:value-of select="'ttlOrntTop'"/>
				</xsl:when>
				<xsl:when test=".='left'">
					<xsl:value-of select="'ttlOrntLeft'"/>
				</xsl:when>
				<xsl:when test=".='right'">
					<xsl:value-of select="'ttlOrntRight'"/>
				</xsl:when>
				<xsl:otherwise></xsl:otherwise>
			</xsl:choose>
		</isc:TitleOrientation>
	</xsl:template>

	<xsl:template match="app:Padding" mode="#all">
		<isc:Padding>
			<xsl:value-of select="."/>
		</isc:Padding>
	</xsl:template>

	<xsl:template match="app:ShowResizeBar" mode="#all">
		<isc:ShowResizeBar>
			<xsl:value-of select="."/>
		</isc:ShowResizeBar>
	</xsl:template>

	<xsl:template match="app:ResizeBarTarget" mode="#all">
		<isc:ResizeBarTarget>
			<xsl:value-of select="."/>
		</isc:ResizeBarTarget>
	</xsl:template>

	<!--................................Дополнительные ф-ции (ToDo Перенести куда нибудь в общий модуль).....................................-->
	<xsl:template name="app:getDataUrl">
		<xsl:param name="mode" as="xs:string"/>
		<xsl:param name="dataSourceID" as="xs:string"/>
		<xsl:param name="operation" as="node()*"/>

		<xsl:if test="count($operation) &gt; 0">
			<xsl:element name="{concat('isc:',common:capitalize($mode),'DataURL')}">
				<xsl:value-of select="concat('/',$ContextPath, '/logic/',$dataSourceID,'/',$mode)"/>
			</xsl:element>
		</xsl:if>
	</xsl:template>

	<xsl:template name="app:getOperation">

		<xsl:param name="mode" as="xs:string"/>
		<xsl:param name="operation" as="node()*"/>
		<xsl:if test="count($operation) &gt; 0">
			<isc:OperationBindingDyn>
				<isc:DataFormat>dtftJSON</isc:DataFormat>
				<isc:DataProtocol>dsprtPostXML</isc:DataProtocol>
				<isc:OperationType>
					<xsl:value-of select="concat('dsOptType', $mode)"/>
				</isc:OperationType>
			</isc:OperationBindingDyn>
		</xsl:if>
	</xsl:template>

	<xsl:function name="app:getBOAttr" as="node()*">
		<xsl:param name="AppName" as="xs:string"/>
		<xsl:param name="GroupName" as="xs:string"/>
		<xsl:param name="BoName" as="xs:string"/>

		<xsl:variable name="res" as="item()*">
			<xsl:variable name="res1" as="node()*" select="$docBO/bo:allClasses/bo:class[@group=$GroupName][@name=$BoName]/bo:attrs/bo:attr[@name=$AppName]"/>
			<xsl:choose>
				<xsl:when test="$res1">
					<xsl:copy-of select="$res1"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:variable name="res2" select="$docBO/bo:allClasses/bo:class[(@name=$BoName) and (@group=$GroupName)]/bo:constraints/bo:fk"/>
					<xsl:variable as="xs:string" name="referenceToGroup" select="$res2/@referenceToGroup"/>
					<xsl:variable as="xs:string" name="referenceTo" select="$res2/@referenceTo"/>					
					<xsl:variable name="res3" as="node()*" select="$docBO/bo:allClasses/bo:class[@group=$referenceToGroup][@name=$referenceTo]/bo:attrs/bo:attr[@name=$AppName]"/>
					<xsl:if test="not($res3)">
						<xsl:message terminate="yes" select="concat('Field: ', common:dblQuoted($AppName), ' not found.')"/>
					</xsl:if>
					<!--<xsl:message select="$res3"/>-->
					<xsl:copy-of select="$res3"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:copy-of select="$res"/>
		<!--<xsl:message select="$res"/>-->
	</xsl:function>

	<xsl:function name="app:GetRecordComponentPoolingMode" as="xs:token">
		<xsl:param name="modelRecordComponentPoolingMode" as="xs:string"/>
		<xsl:value-of select="if ($modelRecordComponentPoolingMode = 'Wiewport') then 'rcCmpPlMdViewport' else if ($modelRecordComponentPoolingMode = 'Data') then 'rcCmpPlMdData' else if ($modelRecordComponentPoolingMode = 'Recycle') then 'rcCmpPlMdRecycle' else 'rcCmpPlMdViewport'"/>
	</xsl:function>
</xsl:stylesheet>

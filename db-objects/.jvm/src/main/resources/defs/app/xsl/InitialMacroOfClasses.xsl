<?xml version="1.1"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:bo="http://simpleSys.ru/xml/library/bo" xmlns:isc="http://simpleSys.ru/xml/library/ISC"
                xmlns:common="http://simpleSys.ru/xml/library/common" xmlns="http://simpleSys.ru/xml/library/app" exclude-result-prefixes="xs bo common">

	<xsl:import href="common.xsl"/>

	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schema.xsd"/>
	<xsl:output indent="yes" method="xml" encoding="UTF-8" name="format"/>

	<xsl:param name="inputBoFile" as="xs:string" select="'file:///f:/target/scala-2.11/src_managed/main/defs/app/tmp/allBo.xml'"/>
	<xsl:variable name="FileSource" select="doc($inputBoFile)"/>

	<xsl:param name="macroDir" as="xs:string" select="'file:///f:/src/main/resources/defs/app/macroBo'"/>
	<xsl:variable name="_macroDir" as="xs:string" select="common:check-last-slash($macroDir)"/>

	<xsl:template name="ProcessingAll">
		<xsl:apply-templates select="$FileSource/bo:allClasses"/>
	</xsl:template>

	<xsl:function as="xs:string" name="bo:separator">
		<xsl:value-of select="'::'"/>
	</xsl:function>

	<xsl:template name="bo:getClassifierDataSource">
		<xsl:variable as="xs:string" name="classifier" select="string-join(common:sort(bo:defaults/bo:classifier/bo:attrName), bo:separator())"/>
		<xsl:if test="$classifier=''">
			<xsl:message terminate="yes" select="concat('Field: ', common:dblQuoted('classifier'), ' not found.')"/>
		</xsl:if>
		<xsl:for-each select="bo:constraints/bo:fk">
			<xsl:choose>
				<xsl:when test="string-join(common:sort(bo:attrRef/@nameLocal), bo:separator()) = $classifier">
					<xsl:value-of select="concat(@referenceToGroup, '_' ,@referenceTo, '_DS')"/>
				</xsl:when>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="bo:getBoTreeCaption">
		<xsl:variable as="xs:string" name="classifier" select="string-join(common:sort(bo:defaults/bo:classifier/bo:attrName), bo:separator())"/>
		<xsl:for-each select="bo:constraints/bo:fk">
			<xsl:choose>
				<xsl:when test="string-join(common:sort(bo:attrRef/@nameLocal), bo:separator()) = $classifier">
					<xsl:choose>
						<xsl:when test="@referenceTo">
							<xsl:variable name="referenceToGroup" select="@referenceToGroup"/>
							<xsl:variable name="referenceTo" select="@referenceTo"/>

							<xsl:variable name="res" select="$FileSource/bo:allClasses/bo:class[@name=$referenceTo][@group=$referenceToGroup]/@caption"/>
							<xsl:choose>
								<xsl:when test="$res">
									<xsl:value-of select="$res"/>
								</xsl:when>
								<xsl:otherwise>
									<!--<xsl:message select="'getBoTreeCaption не определен.'" terminate="yes"/>-->
								</xsl:otherwise>
							</xsl:choose>
						</xsl:when>
						<xsl:otherwise>
							<!--<xsl:message select="'getBoTreeCaption не определен.'" terminate="yes"/>-->
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="bo:getClassifierInitialSort">
		<xsl:variable as="xs:string" name="classifier" select="string-join(common:sort(bo:defaults/bo:classifier/bo:attrName), bo:separator())"/>
		<xsl:for-each select="bo:constraints/bo:fk">
			<xsl:choose>
				<xsl:when test="string-join(common:sort(bo:attrRef/@nameLocal), bo:separator()) = $classifier">
					<InitialSortTree>
						<xsl:variable as="xs:string" name="referenceToGroup" select="@referenceToGroup"/>
						<xsl:variable as="xs:string" name="referenceTo" select="@referenceTo"/>

						<xsl:message select="$referenceToGroup"/>
						<xsl:message select="$referenceTo"/>
						<xsl:for-each select="$FileSource/bo:allClasses/bo:class[@name=$referenceTo][@group=$referenceToGroup]/bo:defaults/bo:uiSettings/bo:orderBy/bo:field">
							<SortSpecifier>
								<xsl:if test="@by">
									<SortDirection>
										<xsl:value-of select="bo:getSortDirection(@by)"/>
									</SortDirection>
								</xsl:if>
								<Property>
									<xsl:value-of select="@attrName"/>
								</Property>
							</SortSpecifier>
						</xsl:for-each>
					</InitialSortTree>
				</xsl:when>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="bo:getDefaultFields">
		<xsl:variable as="xs:string" name="classifier" select="string-join(common:sort(bo:defaults/bo:classifier/bo:attrName), bo:separator())"/>
		<xsl:for-each select="bo:constraints/bo:fk">
			<xsl:choose>
				<xsl:when test="string-join(common:sort(bo:attrRef/@nameLocal), bo:separator()) = $classifier">
					<xsl:variable name="referenceToGroup" select="@referenceToGroup"/>
					<xsl:variable name="referenceTo" select="@referenceTo"/>
					<xsl:choose>
						<xsl:when test="count($FileSource/bo:allClasses/bo:class[@name=$referenceTo][@group=$referenceToGroup]/bo:defaults/bo:showAttrs/bo:attrName) &gt; 0">
							<TreeGridDefaultFields>
								<xsl:for-each select="$FileSource/bo:allClasses/bo:class[@name=$referenceTo][@group=$referenceToGroup]/bo:defaults/bo:showAttrs/bo:attrName">
									<Field>
										<Name>
											<xsl:value-of select="."/>
										</Name>
									</Field>
								</xsl:for-each>
							</TreeGridDefaultFields>
						</xsl:when>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>

	<xsl:function as="xs:boolean" name="bo:checkFk">
		<xsl:param as="node()*" name="bo"/>
		<xsl:param as="xs:string" name="className"/>
		<xsl:param as="xs:string" name="groupName"/>

		<xsl:variable as="xs:integer" name="res" select="count($bo[(@name=$className) and (@group=$groupName)]/bo:constraints/bo:fk[(@referenceTo!=$className) or (@referenceToGroup!=$groupName)])"/>
		<!--<xsl:message select="$res"/>-->

		<xsl:value-of select="$res &gt; 0"/>
	</xsl:function>

	<xsl:function as="xs:boolean" name="bo:checkRefFk">
		<xsl:param as="node()*" name="bo"/>
		<xsl:param as="xs:string" name="className"/>
		<xsl:param as="xs:string" name="groupName"/>

		<xsl:variable as="xs:string" name="classNameRef" select="$bo[(@name=$className) and (@group=$groupName)]/bo:constraints/bo:fk/@referenceTo"/>
		<xsl:variable as="xs:string" name="groupName" select="$bo[(@name=$className) and (@group=$groupName)]/bo:constraints/bo:fk/@referenceToGroup"/>

		<!--<xsl:message select="$classNameRef"/>-->

		<xsl:value-of select="bo:checkFk($bo, $classNameRef, $groupName)"/>
	</xsl:function>

	<xsl:function as="xs:boolean" name="bo:checkAutoSave">
		<xsl:param as="node()*" name="bo"/>
		<xsl:param as="xs:string" name="className"/>
		<xsl:param as="xs:string" name="groupName"/>

		<xsl:value-of select="not(bo:checkFk($bo, $className, $groupName))"/>
	</xsl:function>

	<xsl:template match="bo:allClasses">
		<xsl:for-each-group select="bo:class" group-by="@group">
			<xsl:sort select="current-grouping-key()"/>
			<xsl:variable select="current-grouping-key()" name="groupName" as="xs:string"/>
			<xsl:for-each select="$FileSource/bo:allClasses/bo:class[@group=$groupName]">
				<xsl:variable select="@name" name="boName" as="xs:string"/>
				<xsl:variable select="@caption" name="caption" as="xs:string"/>
				<xsl:variable select="@groupCaption" name="groupCaption" as="xs:string"/>
				<!--<xsl:if test="$boName='TestPaging'">-->
				<!--<xsl:if test="$boName='Contractor'">-->
				<!--<xsl:if test="$boName='RefRefsValues'">-->
					<xsl:message select="concat($groupName, '_', $boName)"/>
					<xsl:variable as="xs:string" select="concat($_macroDir, $groupName, '_', $boName, '.xml')" name="outPath"/>
					<xsl:if test="not(doc-available($outPath))">
						<xsl:result-document href="{$outPath}" format="format" validation="strict">
							<RootPane xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://simpleSys.ru/xml/library/app" xmlns:bo="http://simpleSys.ru/xml/library/bo" xmlns:isc="http://simpleSys.ru/xml/library/ISC"
							          xsi:schemaLocation="http://simpleSys.ru/xml/library/app http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaApp.xsd">
								<Bo BoName="{$boName}" GroupName="{$groupName}" BoCaption="{$caption}" GroupCaption="{$groupCaption}" Enabled="true"/>
								<xsl:variable as="node()*" name="boTreeCaption">
									<xsl:call-template name="bo:getBoTreeCaption"/>
								</xsl:variable>
								<xsl:variable as="xs:string" name="MenuName" select="concat($groupName, '_' ,$boName, '_ComponentMenu')"></xsl:variable>
								<xsl:if test="$boTreeCaption">
									<BoTreeCaption>
										<xsl:call-template name="bo:getBoTreeCaption"/>
									</BoTreeCaption>
								</xsl:if>
								<MenuPath>
									<isc:GroupTitle>
										<xsl:value-of select="$groupCaption"/>
									</isc:GroupTitle>
									<isc:Identifier>
										<xsl:value-of select="$groupName"/>
									</isc:Identifier>
									<isc:Title>
										<xsl:value-of select="$groupCaption"/>
									</isc:Title>
									<isc:MenuPath>
										<isc:Identifier>
											<xsl:value-of select="concat($groupName, '_' ,$boName)"/>
										</isc:Identifier>
										<isc:Title>
											<xsl:value-of select="$caption"/>
										</isc:Title>
									</isc:MenuPath>
								</MenuPath>
								<xsl:choose>
									<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='singleListGrid'"/>
									<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='singleTreeGrid'"/>
									<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='masterTreeDetailedListGrid'">
										<!--<Menus>
										<Menu>
											<Identifier>
												<xsl:value-of select="concat($MenuName,'_Tree')"/>
											</Identifier>
										</Menu>
										<Menu>
											<Identifier>
												<xsl:value-of select="concat($MenuName,'_List')"/>
											</Identifier>
										</Menu>
									</Menus>-->
									</xsl:when>
								</xsl:choose>
								<xsl:choose>
									<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='singleListGrid'"/>
									<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='singleTreeGrid'"/>
									<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='masterTreeDetailedListGrid'">
										<DataSources>
											<DataSource>
												<Identifier>
													<xsl:value-of select="concat($groupName, '_' ,$boName, '_DS')"/>
												</Identifier>
											</DataSource>
											<DataSource>
												<Identifier>
													<!--<xsl:value-of select="bo:getRefDataSource(.)"/>-->
													<xsl:call-template name="bo:getClassifierDataSource"/>
												</Identifier>
											</DataSource>
										</DataSources>
									</xsl:when>
								</xsl:choose>
								<RootCanvas>

									<xsl:variable as="node()*" name="bo" select="."/>
									<xsl:variable as="xs:string" name="className" select="@name"/>
									<xsl:variable as="xs:string" name="groupName" select="@group"/>

									<xsl:choose>
										<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='singleListGrid'">
											<ListGridEditor>
												<useSelfName>false</useSelfName>
												<AutoFetchData>true</AutoFetchData>
												<AutoSaveEdits>
													<xsl:value-of select="bo:checkAutoSave($bo, $className, $groupName)"/>
												</AutoSaveEdits>
												<AutoFitWidthApproach>both</AutoFitWidthApproach>
												<AutoFitFieldWidths>true</AutoFitFieldWidths>
												<Identifier>
													<xsl:value-of select="concat('editor',$groupName,'_',$boName)"/>
												</Identifier>
												<xsl:call-template name="bo:setDataPageSizing">
													<xsl:with-param name="bo" select="$bo" as="node()*"/>
												</xsl:call-template>
												<xsl:if test="$bo/bo:defaults/bo:uiSettings/bo:orderBy">
													<InitialSort>
														<xsl:call-template name="bo:setSortInitial">
															<xsl:with-param name="bo" select="$bo" as="node()*"/>
														</xsl:call-template>
													</InitialSort>
												</xsl:if>
												<CanEdit>true</CanEdit>
												<CanSelectCells>false</CanSelectCells>
												<FetchDelay>500</FetchDelay>
												<!--<xsl:if test="count(bo:attrs/bo:attr) &gt; 0">
													<DefaultFields>
														<xsl:for-each select="bo:attrs/bo:attr">
															<Field>
																<xsl:if test="@hidden=true()">
																	<Hidden>true</Hidden>
																</xsl:if>
																<Name>
																	<xsl:value-of select="@name"/>
																</Name>
															</Field>
														</xsl:for-each>
														<xsl:for-each select="bo:constraints/bo:fk">
															<Field>
																<Hidden>true</Hidden>
																<Name>
																	<xsl:value-of select="bo:attrRef/@nameLocal"/>
																</Name>
															</Field>
														</xsl:for-each>
													</DefaultFields>
												</xsl:if>-->
												<xsl:if test="bo:checkFk($bo, $className, $groupName) = true()">
													<RecordComponentPoolingMode>Recycle</RecordComponentPoolingMode>
												</xsl:if>
												<SelectionType>Multiple</SelectionType>
												<ShowAdvancedFilter>false</ShowAdvancedFilter>
												<ShowFilterEditor>true</ShowFilterEditor>
												<ShowRecordComponents>
													<xsl:value-of select="bo:checkFk($bo, $className, $groupName)"/>
												</ShowRecordComponents>
												<ShowRecordComponentsByCell>
													<xsl:value-of select="bo:checkFk($bo, $className, $groupName)"/>
												</ShowRecordComponentsByCell>
												<TextMatchStyle>txtMchStyleSubstring</TextMatchStyle>
												<WrapCells>true</WrapCells>
												<!--<ContextMenuElement>
												<MenuIDREF>
													<xsl:value-of select="$MenuName"/>
												</MenuIDREF>
											</ContextMenuElement>
											<FuncMenuElement>
												<MenuIDREF>
													<xsl:value-of select="$MenuName"/>
												</MenuIDREF>
											</FuncMenuElement>-->
											</ListGridEditor>
										</xsl:when>
										<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='singleTreeGrid'">
											<TreeGridEditor>
												<useSelfName>false</useSelfName>
												<AutoFetchData>true</AutoFetchData>
												<AutoSaveEdits>
													<xsl:value-of select="bo:checkAutoSave($bo, $className, $groupName)"/>
												</AutoSaveEdits>
												<Identifier>
													<xsl:value-of select="concat('editor',$groupName,'_',$boName)"/>
												</Identifier>
												<xsl:call-template name="bo:setDataPageSizing">
													<xsl:with-param name="bo" select="$bo" as="node()*"/>
												</xsl:call-template>
												<xsl:if test="$bo/bo:defaults/bo:uiSettings/bo:orderBy">
													<InitialSort>
														<xsl:call-template name="bo:setSortInitial">
															<xsl:with-param name="bo" select="$bo" as="node()*"/>
														</xsl:call-template>
													</InitialSort>
												</xsl:if>
												<CanEdit>true</CanEdit>
												<FetchDelay>500</FetchDelay>												
												<FolderIcon>folder.png</FolderIcon>
												<CanSelectCells>false</CanSelectCells>
												<CanReparentNodes>true</CanReparentNodes>
												<NodeIcon>tree_node.png</NodeIcon>
												<xsl:if test="bo:checkFk($bo, $className, $groupName) = true()">
													<RecordComponentPoolingMode>Recycle</RecordComponentPoolingMode>
												</xsl:if>
												<SelectionType>Multiple</SelectionType>
												<ShowRecordComponents>
													<xsl:value-of select="bo:checkFk($bo, $className, $groupName)"/>
												</ShowRecordComponents>
												<ShowRecordComponentsByCell>
													<xsl:value-of select="bo:checkFk($bo, $className, $groupName)"/>
												</ShowRecordComponentsByCell>
												<!--<ContextMenuElement>
												<MenuIDREF>
													<xsl:value-of select="$MenuName"/>
												</MenuIDREF>
											</ContextMenuElement>
											<FuncMenuElement>
												<MenuIDREF>
													<xsl:value-of select="$MenuName"/>
												</MenuIDREF>
											</FuncMenuElement>-->
											</TreeGridEditor>
										</xsl:when>
										<xsl:when test="bo:defaults/bo:uiSettings/@uiFormType='masterTreeDetailedListGrid'">
											<xsl:variable as="xs:string" name="referenceTo" select="bo:constraints/bo:fk/@referenceTo"/>
											<xsl:variable as="xs:string" name="referenceToGroup" select="bo:constraints/bo:fk/@referenceToGroup"/>
											<xsl:variable name="bo1" as="node()*" select="$FileSource/bo:allClasses/bo:class[@name=$referenceTo][@group=$referenceToGroup]"/>

											<TreeListGridEditor>
												<useSelfName>false</useSelfName>
												<Identifier>
													<xsl:value-of select="concat('editor',$groupName,'_',$boName)"/>
												</Identifier>
												<xsl:call-template name="bo:setDataPageSizingTreeList">
													<xsl:with-param name="bo" select="$bo" as="node()*"/>
													<xsl:with-param name="bo1" select="$bo1" as="node()*"/>
												</xsl:call-template>
												<xsl:if test="$bo/bo:defaults/bo:uiSettings/bo:orderBy">
													<InitialSortList>
														<xsl:call-template name="bo:setSortInitial">
															<xsl:with-param name="bo" select="$bo" as="node()*"/>
														</xsl:call-template>
													</InitialSortList>
												</xsl:if>
												<xsl:call-template name="bo:getClassifierInitialSort"/>
												<AutoFetchData>true</AutoFetchData>
												<AutoSaveListEdits>
													<xsl:value-of select="bo:checkAutoSave($bo, $className, $groupName)"/>
												</AutoSaveListEdits>
												<AutoSaveTreeEdits>
													<xsl:value-of select="bo:checkAutoSave($bo1, $referenceTo, $referenceToGroup)"/>
												</AutoSaveTreeEdits>
												<CanEditList>true</CanEditList>
												<CanEditTree>true</CanEditTree>
												<CanSelectCellsList>false</CanSelectCellsList>
												<CanSelectCellsTree>false</CanSelectCellsTree>
												<!--<ContextMenuListGridEditor>
												<xsl:value-of select="concat($MenuName,'_List')"/>
											</ContextMenuListGridEditor>
											<ContextMenuTreeGridEditor>
												<xsl:value-of select="concat($MenuName,'_Tree')"/>
											</ContextMenuTreeGridEditor>-->
												<ListGridDataSourceElement>
													<DataSourceIDREF>
														<xsl:value-of select="concat($groupName, '_' ,$boName, '_DS')"/>
													</DataSourceIDREF>
												</ListGridDataSourceElement>
												<TreeGridDataSourceElement>
													<DataSourceIDREF>
														<!--<xsl:value-of select="bo:getRefDataSource(.)"/>-->
														<xsl:call-template name="bo:getClassifierDataSource"/>
													</DataSourceIDREF>
												</TreeGridDataSourceElement>
												<FolderDropImageTree>folder.png</FolderDropImageTree>
												<FolderIconTree>folder.png</FolderIconTree>
												<NodeIconTree>tree_node.png</NodeIconTree>
												<xsl:call-template name="bo:getDefaultFields"/>
												<ShowListFilterEditor>true</ShowListFilterEditor>
												<ShowTreeFilterEditor>true</ShowTreeFilterEditor>
												<FetchListDelay>500</FetchListDelay>
												<FetchTreeDelay>500</FetchTreeDelay>
												<FilterListOnKeypress>true</FilterListOnKeypress>
												<FilterTreeOnKeypress>true</FilterTreeOnKeypress>
												<TextMatchStyleList>txtMchStyleSubstring</TextMatchStyleList>
												<TextMatchStyleTree>txtMchStyleSubstring</TextMatchStyleTree>
												<ShowListAdvancedFilter>false</ShowListAdvancedFilter>
												<ShowTreeAdvancedFilter>false</ShowTreeAdvancedFilter>
												<ShowListRecordComponents>
													<xsl:value-of select="bo:checkFk($bo, $className, $groupName)"/>
												</ShowListRecordComponents>
												<ShowListRecordComponentsByCell>
													<xsl:value-of select="bo:checkFk($bo, $className, $groupName)"/>
												</ShowListRecordComponentsByCell>
												<ShowTreeRecordComponents>
													<xsl:value-of select="bo:checkRefFk($bo, $className, $groupName)"/>
												</ShowTreeRecordComponents>
												<ShowTreeRecordComponentsByCell>
													<xsl:value-of select="bo:checkRefFk($bo, $className, $groupName)"/>
												</ShowTreeRecordComponentsByCell>
												<WrapListCells>true</WrapListCells>
												<WrapTreeCells>true</WrapTreeCells>
											</TreeListGridEditor>
										</xsl:when>
									</xsl:choose>
								</RootCanvas>
							</RootPane>
						</xsl:result-document>
					</xsl:if>
				<!--</xsl:if>-->
			</xsl:for-each>
		</xsl:for-each-group>
	</xsl:template>

	<xsl:template name="bo:setDataPageSizing">
		<xsl:param as="node()*" name="bo"/>
		<xsl:choose>
			<xsl:when test="$bo/bo:defaults/bo:uiSettings/bo:fetch/bo:size">
				<DataPageSize>
					<xsl:value-of select="$bo/bo:defaults/bo:uiSettings/bo:fetch/bo:size"/>
				</DataPageSize>
			</xsl:when>
			<xsl:when test="$bo/bo:defaults/bo:uiSettings/bo:fetch/bo:fetchAll=true()">
				<DataFetchMode>ftchMdBasic</DataFetchMode>
			</xsl:when>
			<xsl:when test="$bo/bo:defaults/bo:uiSettings/bo:fetch/bo:fetchAll=false()">
				<DataPageSize>
					<xsl:value-of select="'simpleSyS.config.dataPageSize'"/>
				</DataPageSize>
			</xsl:when>
			<xsl:otherwise>
				<DataPageSize>
					<xsl:value-of select="'simpleSyS.config.dataPageSize'"/>
				</DataPageSize>
			</xsl:otherwise>
		</xsl:choose>
		<DrawAheadRatio>
			<xsl:value-of select="'simpleSyS.config.drawAheadRatio'"/>
		</DrawAheadRatio>
	</xsl:template>

	<xsl:template name="bo:setDataPageSizingTreeList">
		<xsl:param as="node()*" name="bo"/>
		<xsl:param as="node()*" name="bo1"/>

		<xsl:choose>
			<xsl:when test="$bo/bo:defaults/bo:uiSettings/bo:fetch/bo:size">
				<DataPageSizeList>
					<xsl:value-of select="$bo/bo:defaults/bo:uiSettings/bo:fetch/bo:size"/>
				</DataPageSizeList>
			</xsl:when>
			<xsl:when test="$bo/bo:defaults/bo:uiSettings/bo:fetch/bo:fetchAll=true()">
				<DataFetchModeList>ftchMdBasic</DataFetchModeList>
			</xsl:when>
			<xsl:when test="$bo/bo:defaults/bo:uiSettings/bo:fetch/bo:fetchAll=false()">
				<DataPageSizeList>
					<xsl:value-of select="'simpleSyS.config.dataPageSize'"/>
				</DataPageSizeList>
			</xsl:when>
			<xsl:otherwise>
				<DataPageSizeList>
					<xsl:value-of select="'simpleSyS.config.dataPageSize'"/>
				</DataPageSizeList>
			</xsl:otherwise>
		</xsl:choose>
		<DrawAheadRatioList>
			<xsl:value-of select="'simpleSyS.config.drawAheadRatio'"/>
		</DrawAheadRatioList>

		<xsl:choose>
			<xsl:when test="$bo1/bo:defaults/bo:uiSettings/bo:fetch/bo:size">
				<DataPageSizeTree>
					<xsl:value-of select="$bo1/bo:defaults/bo:uiSettings/bo:fetch/bo:size"/>
				</DataPageSizeTree>
			</xsl:when>
			<xsl:when test="$bo1/bo:defaults/bo:uiSettings/bo:fetch/bo:fetchAll=true()">
				<DataFetchModeTree>ftchMdBasic</DataFetchModeTree>
			</xsl:when>
			<xsl:when test="$bo1/bo:defaults/bo:uiSettings/bo:fetch/bo:fetchAll=false()">
				<DataPageSizeTree>
					<xsl:value-of select="'simpleSyS.config.dataPageSize'"/>
				</DataPageSizeTree>
			</xsl:when>
			<xsl:otherwise>
				<DataPageSizeTree>
					<xsl:value-of select="'simpleSyS.config.dataPageSize'"/>
				</DataPageSizeTree>
			</xsl:otherwise>
		</xsl:choose>
		<DrawAheadRatioTree>
			<xsl:value-of select="'simpleSyS.config.drawAheadRatio'"/>
		</DrawAheadRatioTree>
	</xsl:template>

	<xsl:template name="bo:setSortInitial">
		<xsl:param as="node()*" name="bo"/>

		<xsl:for-each select="$bo/bo:defaults/bo:uiSettings/bo:orderBy/bo:field">
			<SortSpecifier>
				<xsl:if test="@by">
					<SortDirection>
						<xsl:variable select="@by" name="by" as="xs:string"/>
						<xsl:value-of select="bo:getSortDirection($by)"/>
					</SortDirection>
				</xsl:if>
				<Property>
					<xsl:value-of select="@attrName"/>
				</Property>
			</SortSpecifier>
		</xsl:for-each>
	</xsl:template>

	<xsl:function name="bo:getSortDirection" as="xs:string">
		<xsl:param as="xs:string" name="inParam"/>

		<xsl:choose>
			<xsl:when test="$inParam='asc'">
				<xsl:value-of select="'srtDirAscending'"/>
			</xsl:when>
			<xsl:when test="$inParam='desc'">
				<xsl:value-of select="'srtDirDescending'"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="''"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:function>
</xsl:stylesheet>

<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:isc="http://simpleSys.ru/xml/library/ISC" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:common="http://simpleSys.ru/xml/library/common"
                exclude-result-prefixes="isc xs common">

	<xsl:import href="common.xsl"/>
	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schema.xsd"/>

	<xsl:output indent="yes" method="xml" encoding="UTF-8" name="format"/>

	<xsl:param name="files" as="xs:string*" select="('file:///f:/target/scala-2.11/src_managed/main/defs/app/generated/xml/admin_User.xml')"/>

	<xsl:param name="tmpDir" as="xs:string" select="'file:///f:/target/scala-2.11/src_managed/main/defs/app/tmp'"/>
	<xsl:variable name="_tmpDir" as="xs:string" select="common:check-last-slash($tmpDir)"/>

	<xsl:param name="jsDir" as="xs:string" select="'file:///f:/src/main/resources/defs/app/js'"/>
	<xsl:variable name="_jsDir" as="xs:string" select="common:check-last-slash($jsDir)"/>

	<xsl:variable select="'http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd'" name="schemaAppPath" as="xs:string"/>

	<xsl:template name="MakeDynRibbon">
		<!--Объединение частей для динамического меню из всех файлов сгенеренный в #761 -->
		<xsl:variable as="node()*" name="res">
			<xsl:document validation="strict">
				<isc:MenuPathes>
					<xsl:for-each select="$files">
						<xsl:message select="."/>
						<xsl:for-each select="doc(.)/isc:RootPane/isc:MenuPath">
							<xsl:copy-of select="."/>
						</xsl:for-each>
					</xsl:for-each>
				</isc:MenuPathes>
			</xsl:document>
		</xsl:variable>
		<!--.......................................................................................................................................................-->

		<!--Построение динамической части главного меню-->
		<xsl:result-document href="{concat($_tmpDir, 'dynamicPartOfRibbon.xml')}" format="format" validation="strict">
			<xsl:variable as="xs:boolean" name="existsExt1" select="doc-available('../xml/ExtensionPart1.xml')"/>
			<xsl:message select="concat('existsExt1: ', $existsExt1)"/>
			<xsl:if test="count($res/isc:MenuPathes/isc:MenuPath) &gt; 0 or $existsExt1 = true()">
				<isc:Members xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://simpleSys.ru/xml/library/ISC" xsi:schemaLocation="http://simpleSys.ru/xml/library/ISC  http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd">
					<xsl:for-each-group select="$res/isc:MenuPathes/isc:MenuPath" group-by="isc:Identifier">
						<xsl:sort select="isc:MenuPath/isc:Title"/>
						<isc:RibbonGroupDyn>
							<isc:Group>IfLogged</isc:Group>
							<isc:useSelfName>true</isc:useSelfName>
							<isc:AutoDraw>false</isc:AutoDraw>
							<isc:DefaultLayoutAlign>AlCenter</isc:DefaultLayoutAlign>
							<isc:Controls>
								<xsl:choose>
									<xsl:when test="count(current-group()/isc:MenuPath) = 0">
										<isc:IconButtonSSDyn>
											<isc:Identifier>
												<xsl:value-of select="isc:Identifier"/>
											</isc:Identifier>
											<isc:useSelfName>true</isc:useSelfName>
											<isc:AutoDraw>false</isc:AutoDraw>
											<isc:Click>
												<xsl:call-template name="FunctionsFileURL"/>
												<isc:FunctionName>getTab</isc:FunctionName>
											</isc:Click>
											<isc:Title Ellipsis="true">
												<xsl:value-of select="isc:Title"/>
											</isc:Title>
											<isc:IconOrientation>IoCenter</isc:IconOrientation>
											<xsl:call-template name="getLageIcon"/>
											<isc:Orientation>icbtnOrntVertical</isc:Orientation>
										</isc:IconButtonSSDyn>
									</xsl:when>
									<xsl:otherwise>
										<isc:IconMenuButtonSSDyn>
											<isc:Identifier>
												<xsl:value-of select="isc:Identifier"/>
											</isc:Identifier>
											<isc:useSelfName>true</isc:useSelfName>
											<isc:AutoDraw>false</isc:AutoDraw>
											<isc:Click>
												<xsl:call-template name="FunctionsFileURL"/>
												<isc:FunctionName>showMenu</isc:FunctionName>
											</isc:Click>
											<isc:Title>
												<xsl:value-of select="isc:Title"/>
											</isc:Title>
											<isc:IconOrientation>IoCenter</isc:IconOrientation>
											<xsl:call-template name="getLageIcon"/>
											<isc:Orientation>icbtnOrntVertical</isc:Orientation>
											<isc:Menu>
												<isc:Items>
													<xsl:for-each select="current-group()">
														<xsl:sort select="isc:MenuPath/isc:Title"/>
														<xsl:apply-templates select="isc:MenuPath"/>
													</xsl:for-each>
												</isc:Items>
											</isc:Menu>
											<isc:MenuAnimationEffect>mnuAnimEffctFade</isc:MenuAnimationEffect>
										</isc:IconMenuButtonSSDyn>
									</xsl:otherwise>
								</xsl:choose>
							</isc:Controls>
							<isc:NumRows>1</isc:NumRows>
							<isc:TitleHeight>18</isc:TitleHeight>
							<isc:Title>
								<xsl:value-of select="isc:GroupTitle"/>
							</isc:Title>
						</isc:RibbonGroupDyn>
					</xsl:for-each-group>
					<xsl:if test="$existsExt1=true()">
						<xsl:copy-of select="doc('../xml/ExtensionPart1.xml')/isc:Members/node()"/>
					</xsl:if>
				</isc:Members>
			</xsl:if>
		</xsl:result-document>
		<xsl:message select="'Transformation done.'"/>
	</xsl:template>

	<xsl:template name="getLageIcon">
		<isc:LargeIcon>
			<xsl:choose>
				<xsl:when test="isc:LargeIcon">
					<xsl:value-of select="isc:LargeIcon"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat(isc:Identifier,'.png')"/>
				</xsl:otherwise>
			</xsl:choose>
		</isc:LargeIcon>
	</xsl:template>

	<xsl:template name="getIcon">
		<isc:Icon>
			<xsl:choose>
				<xsl:when test="isc:Icon">
					<xsl:value-of select="isc:Icon"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat(isc:Identifier,'.png')"/>
				</xsl:otherwise>
			</xsl:choose>
		</isc:Icon>
	</xsl:template>

	<xsl:template name="FunctionsFileURL">
		<isc:FunctionsFileURL>
			<xsl:value-of select="concat($_jsDir, 'MenuItemsFunctions.js/')"/>
		</isc:FunctionsFileURL>
	</xsl:template>

	<xsl:template match="isc:MenuPath">
		<isc:MenuItemDyn>
			<!--<isc:Identifier>
                    <xsl:value-of select="isc:Identifier"/>
                  </isc:Identifier>-->
			<xsl:choose>
				<xsl:when test="count(./isc:MenuPath)=0">
					<isc:Click>
						<xsl:call-template name="FunctionsFileURL"/>
						<isc:FunctionName>getTab</isc:FunctionName>
					</isc:Click>
					<xsl:if test="isc:VisibilityIf">
						<isc:VisibilityIf>
							<xsl:call-template name="FunctionsFileURL"/>
							<isc:FunctionName>
								<xsl:value-of select="isc:VisibilityIf/isc:FunctionName"/>
							</isc:FunctionName>
						</isc:VisibilityIf>
					</xsl:if>
					<xsl:call-template name="getIcon"/>
				</xsl:when>
				<xsl:otherwise>
					<isc:Icon>
						<xsl:value-of select="'ellipsis.png'"/>
					</isc:Icon>
				</xsl:otherwise>
			</xsl:choose>

			<isc:Name>
				<xsl:value-of select="isc:Identifier"/>
			</isc:Name>
			<xsl:if test="count(./isc:MenuPath)&gt;0">
				<isc:Submenu>
					<isc:Items>
						<xsl:for-each select="isc:MenuPath">
							<xsl:sort select="isc:MenuPath/isc:Title"/>
							<xsl:apply-templates select="."/>
						</xsl:for-each>
					</isc:Items>
				</isc:Submenu>
			</xsl:if>
			<isc:Title Ellipsis="{count(./isc:MenuPath)=0}">
				<xsl:value-of select="isc:Title"/>
			</isc:Title>
		</isc:MenuItemDyn>
	</xsl:template>

	<!--.......................................................................................................................................................-->
</xsl:stylesheet>

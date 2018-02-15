<?xml version="1.1"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:common="http://simpleSys.ru/xml/library/common" xmlns:domains="http://simpleSys.ru/xml/library/domains"
                xmlns:isc="http://simpleSys.ru/xml/library/ISC" xmlns:app="http://simpleSys.ru/xml/library/app" xmlns:bo="http://simpleSys.ru/xml/library/bo" exclude-result-prefixes="xs isc app bo domains">

	<xsl:import href="common.xsl"/>
	<xsl:import href="commonApp.xsl"/>

	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schema.xsd"/>
	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/domains.xsd"/>
	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd"/>

	<xsl:output indent="yes" method="xml" encoding="UTF-8" name="format"/>

	<xsl:param name="resFile" as="xs:string" select="'file:///e:/target/scala-2.11/src_managed/main/defs/app/tmp/dataSources.xml'"/>
	<xsl:param name="inputBoFile" as="xs:string" select="'file:///e:/target/scala-2.11/src_managed/main/defs/app/tmp/allBo.xml'"/>
	<xsl:param name="domainsFile" as="xs:string" select="'file:///e:/target/scala-2.11/src_managed/main/defs/app/tmp/domains.xml'"/>
	<xsl:param name="maxArity" as="xs:integer" select="254"/>
	<xsl:param name="ContextPath" as="xs:string" select="'mfms'"/>

	<xsl:variable name="FileSource" select="doc($inputBoFile)"/>
	<xsl:variable name="FileDomains" select="doc($domainsFile)"/>

	<xsl:template name="ProcessingAll">
		<xsl:variable name="bos" as="node()*">
			<xsl:document>
				<isc:DataSources xmlns:isc="http://simpleSys.ru/xml/library/ISC" xmlns:common="http://simpleSys.ru/xml/library/common" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				                 xsi:schemaLocation="http://simpleSys.ru/xml/library/ISC http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd">
					<xsl:message select="concat('Processing file: ', $inputBoFile)"/>
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class[@name='ContractorGroup']"/>-->
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class[@name='Gds']"/>-->
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class[@group='test'][@name='TestMultiFKRoot']"/>-->
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class[@group='test'][@name='TestMultiFKCh']"/>-->
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class[@name='User'][@group='admin']"/>-->
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:clas s[@name='UserGroup'][@group='admin']"/>-->
					<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class"/>
				</isc:DataSources>

				<xsl:message select="'Processing done.'"/>
			</xsl:document>
		</xsl:variable>

		<xsl:result-document href="{$resFile}" format="format" validation="strict">
			<xsl:copy-of select="$bos"/>
		</xsl:result-document>
	</xsl:template>

	<xsl:template match="bo:class">
		<xsl:variable name="dataSourecId" as="xs:string" select="app:getDataSourceIdentifier(@group,@name)"/>
		<xsl:message select="app:getDataSourceIdentifier(@group,@name)"/>
		<isc:RestDataSourceSSDyn>
			<isc:ID>
				<xsl:value-of select="concat($dataSourecId, '_DS')"/>
			</isc:ID>
			<isc:fullClassName>
				<xsl:value-of select="@fullClassName"/>
			</isc:fullClassName>
			<isc:lobName>
				<xsl:value-of select="@lobName"/>
			</isc:lobName>
			<isc:Identifier>
				<xsl:value-of select="concat($dataSourecId, '_DS')"/>
			</isc:Identifier>
			<isc:useSelfName>true</isc:useSelfName>
			<!--<isc:AutoJoinTransactions>false</isc:AutoJoinTransactions>-->
			<isc:DataURL>
				<xsl:value-of select="concat('logic/',$dataSourecId,'/*','@simpleSysContextPath')"/>
			</isc:DataURL>
			<!--<isc:DisableQueuing>false</isc:DisableQueuing>-->
			<isc:JsonPrefix></isc:JsonPrefix>
			<isc:JsonSuffix></isc:JsonSuffix>
			<isc:Fields>
				<xsl:variable name="bo" select="."/>
				<xsl:variable name="groupPrefix" select="@groupPrefix"/>
				<xsl:variable name="countSelfAttrs" as="xs:integer" select="count(bo:attrs/bo:attr)"/>
				<xsl:if test="$countSelfAttrs &gt; $maxArity">
					<xsl:message select="concat('Qty columns greate than ',$maxArity)" terminate="yes"/>
				</xsl:if>

				<xsl:variable name="currentClass" as="xs:string" select="@name"/>
				<xsl:variable name="currentGroup" as="xs:string" select="@group"/>

				<xsl:for-each select="bo:attrs/bo:attr">
					<!--<xsl:sort select="@name"/>-->
					<xsl:variable as="xs:string" name="fieldName" select="@name"/>
					<xsl:call-template name="bo:recField">
						<xsl:with-param name="bo" select="$bo" tunnel="yes"/>
						<xsl:with-param name="fieldName" select="$fieldName"/>
						<xsl:with-param name="fieldLookup" as="xs:string" select="'no'"/>
					</xsl:call-template>
				</xsl:for-each>
				<xsl:for-each select="bo:constraints/bo:fk">
					<xsl:variable name="group" as="xs:string" select="@referenceToGroup"/>
					<xsl:variable name="class" as="xs:string" select="@referenceTo"/>
					<xsl:variable name="nameLocal" as="xs:string" select="bo:attrRef/@nameLocal"/>
					<xsl:for-each select="$FileSource/bo:allClasses/bo:class[@group=$group][@name=$class]">
						<xsl:variable name="bo1" select="."/>
						<xsl:if test="$group!=$currentGroup or $class!=$currentClass">
							<xsl:variable name="countSelfAttrs1" as="xs:integer" select="count($bo1/bo:defaults/bo:showAttrs/bo:attrName)"/>
							<xsl:variable name="captionClassLookup" as="xs:string" select="$bo1/@caption"/>

							<xsl:if test="($countSelfAttrs + $countSelfAttrs1) &gt; $maxArity">
								<xsl:message select="concat('Qty columns greate than ',$maxArity)" terminate="yes"/>
							</xsl:if>

							<xsl:for-each select="$bo1/bo:defaults/bo:showAttrs/bo:attrName">
								<xsl:variable as="xs:string" name="fieldName1" select="text()"/>
								<xsl:for-each select="$bo1/bo:attrs/bo:attr[@name=$fieldName1]">
									<!--<xsl:sort select="@name"/>-->
									<xsl:call-template name="bo:recField">
										<xsl:with-param name="bo" select="$bo1" tunnel="yes"/>
										<xsl:with-param name="fieldName" select="$fieldName1"/>
										<xsl:with-param name="nameLocal" select="$nameLocal"/>
										<xsl:with-param name="captionClassLookup" as="xs:string" select="$captionClassLookup"/>
										<xsl:with-param name="fieldLookup" as="xs:string" select="'yes'"/>
									</xsl:call-template>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:if>
					</xsl:for-each>
				</xsl:for-each>
			</isc:Fields>
			<isc:OperationBindings>
				<xsl:if test="not(@isAbstract=true())">
					<isc:OperationBindingDyn>
						<isc:DataFormat>dtftJSON</isc:DataFormat>
						<isc:DataProtocol>dsprtPostXML</isc:DataProtocol>
						<isc:OperationType>dsOptTypeAdd</isc:OperationType>
					</isc:OperationBindingDyn>
				</xsl:if>
				<isc:OperationBindingDyn>
					<isc:DataFormat>dtftJSON</isc:DataFormat>
					<isc:DataProtocol>dsprtPostXML</isc:DataProtocol>
					<isc:OperationType>dsOptTypeFetch</isc:OperationType>
				</isc:OperationBindingDyn>
				<xsl:if test="not(@isAbstract=true())">
					<isc:OperationBindingDyn>
						<isc:DataFormat>dtftJSON</isc:DataFormat>
						<isc:DataProtocol>dsprtPostXML</isc:DataProtocol>
						<isc:OperationType>dsOptTypeRemove</isc:OperationType>
					</isc:OperationBindingDyn>
					<isc:OperationBindingDyn>
						<isc:DataFormat>dtftJSON</isc:DataFormat>
						<isc:DataProtocol>dsprtPostXML</isc:DataProtocol>
						<isc:OperationType>dsOptTypeUpdate</isc:OperationType>
					</isc:OperationBindingDyn>
				</xsl:if>
			</isc:OperationBindings>
			<isc:WildRecord>
				<xsl:variable name="class" select="."/>

				<xsl:variable name="currentClass" as="xs:string" select="@name"/>
				<xsl:variable name="currentGroup" as="xs:string" select="@group"/>

				<xsl:for-each select="bo:attrs/bo:attr">
					<xsl:variable as="xs:string" name="fieldName" select="@name"/>
					<xsl:variable as="xs:string" name="fieldType" select="@type"/>
					<xsl:if test="@mandatory=true()">
						<xsl:if test="$class/bo:constraints/bo:pk/bo:attr!=$fieldName">
							<isc:WildRecordColumn>
								<isc:Name>
									<xsl:value-of select="$fieldName"/>
								</isc:Name>
								<isc:Value>
									<xsl:copy-of select="$FileDomains/domains:DataTypes/domains:SimpleDataType[@name = $fieldType]/domains:simpleScalaType/domains:default/child::*" copy-namespaces="no"/>
								</isc:Value>
							</isc:WildRecordColumn>
						</xsl:if>
					</xsl:if>
				</xsl:for-each>
				<xsl:for-each select="bo:constraints/bo:fk">
					<xsl:variable name="groupRef" as="xs:string" select="@referenceToGroup"/>
					<xsl:variable name="classRef" as="xs:string" select="@referenceTo"/>

					<xsl:for-each select="$FileSource/bo:allClasses/bo:class[@group=$groupRef][@name=$classRef]">
						<xsl:variable name="bo1" select="."/>
						<xsl:if test="$groupRef!=$currentGroup or $classRef!=$currentClass">
							<xsl:variable name="countSelfAttrs1" as="xs:integer" select="count($bo1/bo:defaults/bo:showAttrs/bo:attrName)"/>

							<xsl:for-each select="$bo1/bo:defaults/bo:showAttrs/bo:attrName">
								<xsl:variable as="xs:string" name="fieldName1" select="text()"/>
								<xsl:for-each select="$bo1/bo:attrs/bo:attr[@name=$fieldName1]">
									<!--<xsl:sort select="@name"/>-->
									<xsl:variable name="fieldName" as="xs:string" select="@name"/>
									<xsl:variable name="fieldType" as="xs:string" select="@type"/>
									<xsl:variable name="fieldMandatory" as="xs:boolean" select="@mandatory"/>

									<xsl:if test="$fieldMandatory=true()">
										<isc:WildRecordColumn>
											<isc:Name>
												<xsl:value-of select="$fieldName"/>
											</isc:Name>
											<isc:Value>
												<xsl:copy-of select="$FileDomains/domains:DataTypes/domains:SimpleDataType[@name = $fieldType]/domains:simpleScalaType/domains:default/child::*" copy-namespaces="no"/>
											</isc:Value>
										</isc:WildRecordColumn>
									</xsl:if>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:if>
					</xsl:for-each>
				</xsl:for-each>
			</isc:WildRecord>
			<isc:DataFormat>dtftJSON</isc:DataFormat>
			<xsl:if test="not(@isAbstract=true())">
				<isc:AddDataURL>
					<xsl:value-of select="concat('logic/',$dataSourecId,'/Add','@simpleSysContextPath')"/>
				</isc:AddDataURL>
			</xsl:if>
			<isc:FetchDataURL>
				<xsl:value-of select="concat('logic/',$dataSourecId,'/Fetch','@simpleSysContextPath')"/>
			</isc:FetchDataURL>
			<xsl:if test="not(@isAbstract=true())">
				<isc:RemoveDataURL>
					<xsl:value-of select="concat('logic/',$dataSourecId,'/Remove','@simpleSysContextPath')"/>
				</isc:RemoveDataURL>
				<isc:UpdateDataURL>
					<xsl:value-of select="concat('logic/',$dataSourecId,'/Update','@simpleSysContextPath')"/>
				</isc:UpdateDataURL>
			</xsl:if>
		</isc:RestDataSourceSSDyn>
	</xsl:template>

	<xsl:template name="bo:isPrimaryKey">
		<xsl:param as="xs:string" name="attrName"/>
		<xsl:param as="node()*" name="bo" tunnel="yes"/>

		<xsl:if test="$bo/bo:constraints/bo:pk/bo:attr=$attrName">
			<isc:CanEdit>false</isc:CanEdit>
			<isc:PrimaryKey>true</isc:PrimaryKey>
		</xsl:if>
	</xsl:template>

	<xsl:template name="bo:recField">
		<xsl:param as="xs:string" name="fieldName"/>
		<xsl:param as="xs:string" name="fieldLookup"/>
		<xsl:param as="xs:string" name="nameLocal" select="''"/>
		<xsl:param as="xs:string" name="captionClassLookup" select="''"/>
		<xsl:param as="node()*" name="bo" tunnel="yes"/>

		<xsl:variable as="node()*" name="forignKeys" select="$bo/bo:constraints/bo:fk[bo:attrRef/@nameLocal=$fieldName]"/>

		<isc:DataSourceFieldDyn>
			<xsl:choose>
				<xsl:when test="$fieldLookup='no'">
					<xsl:if test="@calculated=true()">
						<isc:CanEdit>
							<xsl:value-of select="not(@calculated)"/>
						</isc:CanEdit>
					</xsl:if>
				</xsl:when>
				<xsl:otherwise>
					<isc:CanEdit>true</isc:CanEdit>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:if test="$fieldLookup='no'">
				<isc:Discriminator>
					<xsl:value-of select="@isDiscriminator"/>
				</isc:Discriminator>
			</xsl:if>
			<isc:JObjectFieldName>
				<xsl:value-of select="concat($bo/@name,'.',@name)"/>
			</isc:JObjectFieldName>
			<xsl:if test="((@hidden = true()) or (count($forignKeys) &gt; 0))">
				<isc:Hidden>
					<xsl:value-of select="(@hidden or (count($forignKeys) &gt; 0))"/>
				</isc:Hidden>
			</xsl:if>
			<xsl:if test="$fieldLookup='yes'">
				<isc:ForeignField>
					<xsl:value-of select="$nameLocal"/>
				</isc:ForeignField>
			</xsl:if>
			<xsl:if test="$fieldLookup='no'">
				<xsl:if test="count($forignKeys) &gt; 0">
					<isc:ForeignKey>
						<xsl:value-of select="concat($forignKeys/@referenceToGroup,'_', $forignKeys/@referenceTo,'_DS.', $forignKeys/bo:attrRef[@nameLocal=$fieldName]/@nameRemote)"/>
					</isc:ForeignKey>
				</xsl:if>
			</xsl:if>
			<xsl:call-template name="app:getLength">
				<xsl:with-param name="type" select="@type"/>
			</xsl:call-template>
			<isc:Name>
				<xsl:value-of select="$fieldName"/>
			</isc:Name>
			<isc:GenBySeq>
				<xsl:value-of select="@genBySeq"/>
			</isc:GenBySeq>
			<isc:GetterType>
				<xsl:value-of select="@getterType"/>
			</isc:GetterType>
			<xsl:choose>
				<xsl:when test="$fieldLookup='no'">
					<isc:Calculated>
						<xsl:value-of select="@calculated"/>
					</isc:Calculated>
				</xsl:when>
				<xsl:otherwise>
					<isc:Calculated>false</isc:Calculated>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:if test="$fieldLookup='yes'">
				<isc:Lookup>true</isc:Lookup>
				<isc:CaptionClassLookup>
					<xsl:value-of select="$captionClassLookup"/>
				</isc:CaptionClassLookup>
			</xsl:if>
			<xsl:if test="$fieldLookup='no'">
				<xsl:call-template name="bo:isPrimaryKey">
					<xsl:with-param name="attrName" select="@name"/>
				</xsl:call-template>
			</xsl:if>
			<!--<xsl:if test="$fieldLookup='no'">-->
			<xsl:if test="@mandatory = true()">
				<xsl:if test="not(@calculated = true())">
					<isc:Required>
						<xsl:value-of select="@mandatory = true()"/>
					</isc:Required>
				</xsl:if>
			</xsl:if>
			<!--</xsl:if>-->
			<xsl:if test="@caption">
				<isc:Title>
					<xsl:attribute name="key4MergeValue" select="$fieldName"/>
					<xsl:value-of select="@caption"/>
				</isc:Title>
			</xsl:if>
			<xsl:call-template name="app:getType" exclude-result-prefixes="">
				<xsl:with-param name="type" select="@type"/>
			</xsl:call-template>
		</isc:DataSourceFieldDyn>
	</xsl:template>
</xsl:stylesheet>

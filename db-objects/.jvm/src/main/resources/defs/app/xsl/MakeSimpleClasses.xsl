<?xml version="1.0"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:common="http://simpleSys.ru/xml/library/common" xmlns:isc="http://simpleSys.ru/xml/library/ISC"
                xmlns:domains="http://simpleSys.ru/xml/library/domains" xmlns:bo="http://simpleSys.ru/xml/library/bo" xmlns:app="http://simpleSys.ru/xml/library/app" exclude-result-prefixes="xs isc domains bo app">

	<xsl:import href="common.xsl"/>
	<xsl:import href="commonApp.xsl"/>

	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schema.xsd"/>
	<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd"/>

	<xsl:output indent="yes" method="xml" encoding="UTF-8" name="format"/>

	<xsl:param name="resFile" as="xs:string" select="'file:///e:/target/scala-2.11/src_managed/main/defs/app/tmp/SimpleTypes.xml'"/>
	<xsl:param name="inputBoFile" as="xs:string" select="'file:///e:/target/scala-2.11/src_managed/main/defs/app/tmp/domains.xml'"/>

	<xsl:variable name="FileSource" select="doc($inputBoFile)"/>

	<xsl:template name="ProcessingAll">
		<xsl:variable name="bos" as="node()*">
			<xsl:document>
				<xsl:message select="concat('Processing file: ', $inputBoFile)"/>
				<SimpleTypes xmlns="http://simpleSys.ru/xml/library/ISC" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://simpleSys.ru/xml/library/ISC http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd">
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class[@name='ContractorGroup']"/>-->
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class[@name='Gds']"/>-->
					<!--<xsl:apply-templates select="$FileSource/bo:allClasses/bo:class"/>-->
					<xsl:for-each select="$FileSource/domains:DataTypes/domains:SimpleDataType/domains:isc/domains:SimpleType">
						<xsl:sort select="domains:name"/>
						<SimpleTypeDyn>
							<useSelfName>true</useSelfName>
							<xsl:if test="domains:lenght">
								<FieldProperties>
									<Length>
										<xsl:value-of select="domains:length"/>
									</Length>
								</FieldProperties>
							</xsl:if>
							<xsl:if test="domains:inheritsFrom">
								<InheritsFrom>
									<xsl:value-of select="domains:inheritsFrom"/>
								</InheritsFrom>
							</xsl:if>
							<Name>
								<xsl:value-of select="app:getSimpleTypeName(domains:name)"/>
							</Name>
							<xsl:if test="count(domains:validOperators/domains:validOperator) &gt; 0">
								<ValidOperators>
									<xsl:for-each select="domains:validOperators/domains:validOperator">
										<xsl:choose>
											<xsl:when test=".='equals'">
												<OperatorId>opIdEquals</OperatorId>
											</xsl:when>
											<xsl:when test=".='notEqual'">
												<OperatorId>opIdNotEqual</OperatorId>
											</xsl:when>
											<xsl:when test=".='iEquals'">
												<OperatorId>opIdIEquals</OperatorId>
											</xsl:when>
											<xsl:when test=".='iNotEqual'">
												<OperatorId>opIdINotEqual</OperatorId>
											</xsl:when>
											<xsl:when test=".='greaterThan'">
												<OperatorId>opIdGreaterThan</OperatorId>
											</xsl:when>
											<xsl:when test=".='lessThan'">
												<OperatorId>opIdLessThan</OperatorId>
											</xsl:when>
											<xsl:when test=".='greaterOrEqual'">
												<OperatorId>opIdGreaterOrEqual</OperatorId>
											</xsl:when>
											<xsl:when test=".='lessOrEqual'">
												<OperatorId>opIdLessOrEqual</OperatorId>
											</xsl:when>
											<xsl:when test=".='contains'">
												<OperatorId>opIdContains</OperatorId>
											</xsl:when>
											<xsl:when test=".='startsWith'">
												<OperatorId>opIdStartsWith</OperatorId>
											</xsl:when>
											<xsl:when test=".='endsWith'">
												<OperatorId>opIdEndsWith</OperatorId>
											</xsl:when>
											<xsl:when test=".='iContains'">
												<OperatorId>opIdIContains</OperatorId>
											</xsl:when>
											<xsl:when test=".='iStartsWith'">
												<OperatorId>opIdIStartsWith</OperatorId>
											</xsl:when>
											<xsl:when test=".='iEndsWith'">
												<OperatorId>opIdIEndsWith</OperatorId>
											</xsl:when>
											<xsl:when test=".='notContains'">
												<OperatorId>opIdNotContains</OperatorId>
											</xsl:when>
											<xsl:when test=".='notStartsWith'">
												<OperatorId>opIdNotStartsWith</OperatorId>
											</xsl:when>
											<xsl:when test=".='notEndsWith'">
												<OperatorId>opIdNotEndsWith</OperatorId>
											</xsl:when>
											<xsl:when test=".='iNotContains'">
												<OperatorId>opIdINotContains</OperatorId>
											</xsl:when>
											<xsl:when test=".='iNotStartsWith'">
												<OperatorId>opIdINotStartsWith</OperatorId>
											</xsl:when>
											<xsl:when test=".='iNotEndsWith'">
												<OperatorId>opIdINotEndsWith</OperatorId>
											</xsl:when>
											<xsl:when test=".='iBetweenInclusive'">
												<OperatorId>opIdIBetweenInclusive</OperatorId>
											</xsl:when>
											<xsl:when test=".='iMatchesPattern'">
												<OperatorId>opIdIMatchesPattern</OperatorId>
											</xsl:when>
											<xsl:when test=".='matchesPattern'">
												<OperatorId>opIdMatchesPattern</OperatorId>
											</xsl:when>
											<xsl:when test=".='containsPattern'">
												<OperatorId>opIdContainsPattern</OperatorId>
											</xsl:when>
											<xsl:when test=".='iContainsPattern'">
												<OperatorId>opIdIContainsPattern</OperatorId>
											</xsl:when>
											<xsl:when test=".='regexp'">
												<OperatorId>opIdRegexp</OperatorId>
											</xsl:when>
											<xsl:when test=".='iregexp'">
												<OperatorId>opIdIregexp</OperatorId>
											</xsl:when>
											<xsl:when test=".='isNull'">
												<OperatorId>opIdIsNull</OperatorId>
											</xsl:when>
											<xsl:when test=".='notNull'">
												<OperatorId>opIdNotNull</OperatorId>
											</xsl:when>
											<xsl:when test=".='inSet'">
												<OperatorId>opIdInSet</OperatorId>
											</xsl:when>
											<xsl:when test=".='notInSet'">
												<OperatorId>opIdNotInSet</OperatorId>
											</xsl:when>
											<xsl:when test=".='equalsField'">
												<OperatorId>opIdEqualsField</OperatorId>
											</xsl:when>
											<xsl:when test=".='notEqualField'">
												<OperatorId>opIdNotEqualField</OperatorId>
											</xsl:when>
											<xsl:when test=".='greaterThanField'">
												<OperatorId>opIdGreaterThanField</OperatorId>
											</xsl:when>
											<xsl:when test=".='lessThanField'">
												<OperatorId>opIdLessThanField</OperatorId>
											</xsl:when>
											<xsl:when test=".='greaterOrEqualField'">
												<OperatorId>opIdGreaterOrEqualField</OperatorId>
											</xsl:when>
											<xsl:when test=".='lessOrEqualField'">
												<OperatorId>opIdLessOrEqualField</OperatorId>
											</xsl:when>
											<xsl:when test=".='containsField'">
												<OperatorId>opIdContainsField</OperatorId>
											</xsl:when>
											<xsl:when test=".='startsWithField'">
												<OperatorId>opIdStartsWithField</OperatorId>
											</xsl:when>
											<xsl:when test=".='endsWithField'">
												<OperatorId>opIdEndsWithField</OperatorId>
											</xsl:when>
											<xsl:when test=".='iContainsField'">
												<OperatorId>opIdIContainsField</OperatorId>
											</xsl:when>
											<xsl:when test=".='iStartsWithField'">
												<OperatorId>opIdIStartsWithField</OperatorId>
											</xsl:when>
											<xsl:when test=".='iEndsWithField'">
												<OperatorId>opIdIEndsWithField</OperatorId>
											</xsl:when>
											<xsl:when test=".='notContainsField'">
												<OperatorId>opIdNotContainsField</OperatorId>
											</xsl:when>
											<xsl:when test=".='notStartsWithField'">
												<OperatorId>opIdNotStartsWithField</OperatorId>
											</xsl:when>
											<xsl:when test=".='notEndsWithField'">
												<OperatorId>opIdNotEndsWithField</OperatorId>
											</xsl:when>
											<xsl:when test=".='iNotContainsField'">
												<OperatorId>opIdINotContainsField</OperatorId>
											</xsl:when>
											<xsl:when test=".='iNotStartsWithField'">
												<OperatorId>opIdINotStartsWithField</OperatorId>
											</xsl:when>
											<xsl:when test=".='iNotEndsWithField'">
												<OperatorId>opIdINotEndsWithField</OperatorId>
											</xsl:when>
											<xsl:when test=".='and'">
												<OperatorId>opIdAnd</OperatorId>
											</xsl:when>
											<xsl:when test=".='not'">
												<OperatorId>opIdNot</OperatorId>
											</xsl:when>
											<xsl:when test=".='or'">
												<OperatorId>opIdOr</OperatorId>
											</xsl:when>
											<xsl:when test=".='between'">
												<OperatorId>opIdBetween</OperatorId>
											</xsl:when>
											<xsl:when test=".='betweenInclusive'">
												<OperatorId>opIdBetweenInclusive</OperatorId>
											</xsl:when>
											<xsl:otherwise>
												<xsl:message select="concat('Unknown OperatorId ', .)"/>
											</xsl:otherwise>
										</xsl:choose>
									</xsl:for-each>
								</ValidOperators>
							</xsl:if>
						</SimpleTypeDyn>
					</xsl:for-each>
				</SimpleTypes>

				<xsl:message select="'Processing done.'"/>
			</xsl:document>
		</xsl:variable>

		<xsl:result-document href="{$resFile}" format="format" validation="strict">
			<xsl:copy-of select="$bos"/>
		</xsl:result-document>
	</xsl:template>
</xsl:stylesheet>

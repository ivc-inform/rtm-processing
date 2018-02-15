<?xml version="1.1"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:common="http://simpleSys.ru/xml/library/common" xmlns:isc="http://simpleSys.ru/xml/library/ISC"
                xmlns:bo="http://simpleSys.ru/xml/library/bo" exclude-result-prefixes="common xs isc bo">

	<!--<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd"/>-->

	<xsl:function name="common:capitalize" as="xs:string">
		<xsl:param name="inStr" as="xs:string"/>
		<xsl:value-of select="concat(substring(upper-case($inStr),1,1), substring(lower-case($inStr),2))"/>
	</xsl:function>

	<xsl:function name="common:isPrimaryOrUniqueKey1" as="xs:boolean">
		<xsl:param name="bo"/>
		<xsl:param name="attrName" as="xs:string"/>

		<!--<xsl:value-of select="$bo/bo:constraints/bo:uc[@uniqueType='pk']/bo:attrName = $attrName or $bo/bo:constraints/bo:uc[@uniqueType='uq']/bo:attrName = $attrName"/>-->
		<xsl:value-of select="$bo/bo:constraints/bo:uc[@uniqueType='pk']/bo:attrName = $attrName"/>
	</xsl:function>

	<xsl:function name="common:ellipsis" as="xs:string">
		<xsl:param name="inStr" as="xs:string"/>

		<xsl:value-of select="concat($inStr, ' ...')"/>
	</xsl:function>

	<xsl:function name="common:dblQuoted" as="xs:string">
		<xsl:param name="inStr" as="xs:string"/>

		<xsl:value-of select='concat("""", $inStr, """")'/>
	</xsl:function>

	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()"/>
		</xsl:copy>
	</xsl:template>

	<!--<xsl:template match="*">
      <xsl:message>
          <xsl:text>Processing element </xsl:text>
          <xsl:value-of select="common:dblQuoted(name())"/>
          <xsl:if test="parent::*">
              <xsl:text> wich has a parent element </xsl:text>
              <xsl:value-of select="common:dblQuoted(name(..))"/>
          </xsl:if>
      </xsl:message>
  </xsl:template>-->

	<xsl:function name="common:string-index-of" as="xs:integer">
		<xsl:param name="input" as="xs:string"/>
		<xsl:param name="substr"/>
		<xsl:sequence select="if (contains($input, $substr)) then string-length(substring-before($input, $substr))+1 else 0"/>
	</xsl:function>

	<xsl:function name="common:substring-before-last">
		<xsl:param name="input" as="xs:string"/>
		<xsl:param name="substr" as="xs:string"/>
		<xsl:sequence select="if ($substr) then if (contains($input, $substr)) then string-join(tokenize($input, $substr) [position() ne last()],$substr) else '' else $input"/>
	</xsl:function>

	<xsl:function name="common:substring-after-last">
		<xsl:param name="input" as="xs:string"/>
		<xsl:param name="substr" as="xs:string"/>
		<xsl:sequence select="if ($substr) then if (contains($input, $substr)) then tokenize($input, $substr)[last()] else '' else $input"/>
	</xsl:function>

	<xsl:function name="common:check-last-slash" as="xs:string">
		<xsl:param name="input" as="xs:string"/>
		<xsl:value-of select="if (contains($input, '/')) then (if (common:substring-after-last($input, '/')='') then $input else concat($input,'/')) else concat($input,'/')"/>
	</xsl:function>

	<xsl:function name="common:getFirstPartOfPath" as="xs:string">
		<xsl:param as="xs:string" name="path"/>
		<xsl:value-of select="if (substring-before($path, '/')) then substring-before($path, '/') else $path"/>
	</xsl:function>

	<xsl:function name="common:getCountPartOfPath" as="xs:integer">
		<xsl:param as="xs:string" name="path"/>
		<xsl:value-of select="common:_getCountPartOfPath($path,0)"/>
	</xsl:function>

	<xsl:function name="common:_getCountPartOfPath" as="xs:integer">
		<xsl:param as="xs:string" name="path"/>
		<xsl:param as="xs:integer" name="res"/>
		<xsl:value-of select="if (substring-before($path, '/')) then common:_getCountPartOfPath(substring-after($path, '/'), $res + 1) else $res"/>
	</xsl:function>

    <xsl:function name="common:getRelativeOfPath" as="xs:string">
		<xsl:param as="xs:string" name="path"/>
		<!--<xsl:value-of select="if (substring-after($path,'/')) then substring-after($path,'/') else $path"/>-->
		<xsl:value-of select="$path"/>
	</xsl:function>

	<xsl:function name="common:sort" as="xs:string*">
		<xsl:param as="xs:string*" name="input"/>
		<xsl:for-each select="$input">
			<xsl:sort select="."/>
			<xsl:value-of select="."/>
		</xsl:for-each>
	</xsl:function>
</xsl:stylesheet>

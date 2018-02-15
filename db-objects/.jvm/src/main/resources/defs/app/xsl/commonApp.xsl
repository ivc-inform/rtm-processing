<?xml version="1.1"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:common="http://simpleSys.ru/xml/library/common"
                xmlns:app="http://simpleSys.ru/xml/library/app" xmlns:isc="http://simpleSys.ru/xml/library/ISC" xmlns:bo="http://simpleSys.ru/xml/library/bo"
                xmlns:domains="http://simpleSys.ru/xml/library/domains" exclude-result-prefixes="common xs isc bo app domains">

    <!--<xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/schemaISC.xsd"/>-->
    <xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/domains.xsd"/>

    <xsl:param name="tmpDir" as="xs:string" select="'file:///e:/target/scala-2.11/src_managed/main/defs/app/tmp'"/>
    <xsl:variable name="dataTypes" select="doc(concat(common:check-last-slash($tmpDir), 'domains.xml'))"/>

    <xsl:function name="app:getOperationURL" as="xs:string">
        <xsl:param name="bo" as="node()*"/>
        <xsl:param name="contextPath" as="xs:string"/>
        <xsl:param name="operationType" as="xs:string"/>

        <xsl:variable name="groupName" as="xs:string" select="$bo/bo:group/@name"/>

        <xsl:value-of select="concat($contextPath, '/', common:capitalize($groupName),'/', xs:string($operationType))"/>
    </xsl:function>

    <xsl:function name="app:getHierFieldType" as="bo:domain.type">
        <xsl:param name="source" as="node()*"/>
        <xsl:param name="groupName" as="xs:string"/>
        <xsl:param name="parentGroup" as="node()*"/>
        <xsl:param name="parent" as="xs:string"/>
        <xsl:param name="name" as="xs:string"/>

        <xsl:variable name="group" select="if ($parentGroup) then $parentGroup else $groupName"/>
        <xsl:variable name="res" select="$source/bo:objectSchema/bo:group[@name=$group]/bo:classes/bo:classInHierarchy[@name=$parent]/bo:attrs/bo:attr[@name=$name]/@type"/>
        <xsl:variable name="res1">
            <xsl:if test="not($res)">
                <xsl:for-each select="$source/bo:objectSchema/bo:group[@name=$group]/bo:classes/bo:classInHierarchy[@name=$parent]/bo:referenceToParent">
                    <xsl:value-of select="app:getHierFieldType($source, $group, @parentGroup, @parent, $name)"/>
                </xsl:for-each>
            </xsl:if>
        </xsl:variable>
        <xsl:value-of select="if ($res) then $res else $res1"/>
    </xsl:function>

    <xsl:function name="app:getFieldType" as="bo:domain.type">
        <xsl:param name="source" as="node()*"/>
        <xsl:param name="groupName" as="xs:string"/>
        <xsl:param name="parentGroup" as="node()*"/>
        <xsl:param name="parent" as="xs:string"/>
        <xsl:param name="name" as="xs:string"/>

        <xsl:variable name="group" select="if ($parentGroup) then $parentGroup else $groupName"/>
        <xsl:variable name="s" select="$source/bo:objectSchema/bo:group[@name=$group]/bo:classes/bo:classSimple[@name=$parent]/bo:attrs/bo:attr[@name=$name]/@type"/>
        <xsl:variable name="e" select="$source/bo:objectSchema/bo:group[@name=$group]/bo:classes/bo:classEnum[@name=$parent]/bo:attrs/bo:attr[@name=$name]/@type"/>

        <xsl:variable name="res" select="if ($s) then $s else if ($e) then $e else app:getHierFieldType($source, $groupName, $parentGroup, $parent, $name)"/>

        <xsl:if test="not($res)">
            <xsl:message select="'Не определен тип.'" terminate="yes"/>
        </xsl:if>
        <xsl:value-of select="$res"/>
    </xsl:function>

    <xsl:function name="app:getTableName" as="xs:string">
        <xsl:param as="xs:string" name="groupPrefix"/>
        <xsl:param as="xs:string" name="boName"/>
        <xsl:value-of select="upper-case(concat($groupPrefix,'_',$boName))"/>
    </xsl:function>

    <xsl:function name="app:getDataSourceIdentifier" as="xs:string">
        <xsl:param name="groupName"/>
        <xsl:param name="boName"/>
        <xsl:value-of select="concat($groupName,'_',$boName)"/>
    </xsl:function>

    <xsl:function name="app:getFieldName" as="xs:string">
        <xsl:param name="attrName" as="xs:string"/>
        <xsl:param name="attrType" as="bo:domain.type"/>

        <xsl:variable name="prefix" as="xs:string" select="$dataTypes/domains:DataTypes/domains:SimpleDataType[@name = $attrType]/domains:dbPrefix"/>

        <xsl:value-of select="if ($prefix = $attrName) then upper-case($attrName) else upper-case(concat($prefix, $attrName))"/>
    </xsl:function>

    <xsl:function name="app:getFieldLength" as="xs:string">
        <xsl:param name="attrType" as="xs:string"/>
        <xsl:value-of select="$dataTypes/domains:DataTypes/domains:SimpleDataType[@name = $attrType]/domains:jooqDataType/domains:length"/>
    </xsl:function>

    <xsl:template name="app:getLength">
        <xsl:param name="type" as="bo:domain.type" required="yes"/>

        <xsl:if test="not(empty($dataTypes/domains:DataTypes/domains:SimpleDataType[@name = $type]/domains:jooqDataType/domains:length))">
            <isc:Length>
                <xsl:value-of select="$dataTypes/domains:DataTypes/domains:SimpleDataType[@name = $type]/domains:jooqDataType/domains:length"/>
            </isc:Length>
        </xsl:if>
    </xsl:template>

    <xsl:function name="app:getSimpleTypeName" as="xs:string">
        <xsl:param as="xs:string" name="type"/>
        <xsl:value-of select="concat($type,'_SimpleType')"/>
    </xsl:function>

    <xsl:template name="app:getType">
        <xsl:param name="type" as="bo:domain.type" required="yes"/>
        <isc:Type>
            <xsl:value-of select="app:getSimpleTypeName($dataTypes/domains:DataTypes/domains:SimpleDataType[@name = $type]/domains:isc/domains:SimpleType/domains:name)"/>
        </isc:Type>
    </xsl:template>

    <xsl:template name="app:getType1">
        <xsl:param name="type" as="bo:domain.type" required="yes"/>
        <xsl:param name="iscType" as="node()*" required="yes"/>

        <xsl:choose>
            <xsl:when test="$iscType">
                <isc:Type>
                    <xsl:copy-of select="$iscType/node()"/>
                </isc:Type>
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="app:getType">
                    <xsl:with-param name="type" select="$type"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>

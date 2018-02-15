<?xml version="1.1"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:bo="http://simpleSys.ru/xml/library/bo"
                xmlns:common="http://simpleSys.ru/xml/library/common" xmlns:isc="http://simpleSys.ru/xml/library/ISC" exclude-result-prefixes="xs bo common isc">

    <!--<xsl:import href="common.xsl"/>-->
    <xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schema.xsd"/>
    <xsl:import-schema schema-location="http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd"/>

    <xsl:output indent="yes" method="xml" encoding="UTF-8" byte-order-mark="no" name="format"/>

    <xsl:param name="resFile" as="xs:string" select="'file:///e:/target/scala-2.11/src_managed/main/defs/app/tmp/allBo.xml'"/>
    <xsl:param as="xs:string*" name="files"
               select="(
               '../../bo/ui.xml',
               '../../bo/currency.xml',
               '../../bo/gdsandservice.xml',
               '../../bo/invoice.xml',
               '../../bo/measure.xml',
               '../../bo/organizational.xml',
               '../../bo/refs.xml',
               '../../bo/sequences.xml',
               '../../bo/setting.xml',
               '../../bo/stock.xml',
               '../../bo/test.xml',
               '../../bo/user.xml',
               '../../bo/common.xml')"/>
    <!--<xsl:param as="xs:string*" name="files" select="('../../bo/invoice.xml')"/>-->

    <xsl:template name="ProcessingAll">
        <xsl:result-document href="{$resFile}" format="format" validation="strict">
            <objectSchema xmlns="http://simpleSys.ru/xml/library/bo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                          xsi:schemaLocation="http://simpleSys.ru/xml/library/bo http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schema.xsd">
                <xsl:for-each select="$files">
                    <xsl:message select="concat('Processing file: ', .)"/>
                    <xsl:variable name="FileSource" select="doc(.)"/>

                    <xsl:apply-templates select="$FileSource/bo:objectSchema/bo:group"/>
                </xsl:for-each>
            </objectSchema>

            <xsl:message select="'Processing done.'"/>
        </xsl:result-document>
    </xsl:template>

    <xsl:template match="bo:group">
        <!--<xsl:if test="(count(bo:add2Ribbon) &gt; 0) and ((bo:classes/bo:classSimple/bo:add2Menu) or (bo:classes/bo:classInHierarchy/bo:add2Menu))">-->
        <group xmlns="http://simpleSys.ru/xml/library/bo" prefix="{@prefix}" name="{@name}" caption="{@caption}">           
            <classes>
                <xsl:apply-templates select="bo:classes"/>
            </classes>
            <tables>
                <xsl:apply-templates select="bo:tables"/>
            </tables>
        </group>
        <!--</xsl:if>-->
    </xsl:template>

    <xsl:template match="bo:classes">
        <xsl:apply-templates select="bo:classSimple"/>
        <xsl:apply-templates select="bo:classInHierarchy"/>
        <xsl:apply-templates select="bo:classEnum"/>
    </xsl:template>

    <xsl:template match="bo:classSimple">
        <!--<xsl:if test="count(bo:add2Menu) &gt; 0">-->
        <xsl:copy-of select="."/>
        <!--</xsl:if>-->
    </xsl:template>

    <xsl:template match="bo:classInHierarchy">
        <!--<xsl:if test="count(bo:add2Menu) &gt; 0">-->
        <xsl:copy-of select="."/>
        <!--</xsl:if>-->
    </xsl:template>

    <xsl:template match="bo:classEnum">
        <xsl:copy-of select="."/>
    </xsl:template>

    <xsl:template match="bo:tables">
        <xsl:copy-of select="."/>
    </xsl:template>
</xsl:stylesheet>

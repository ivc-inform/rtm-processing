<?xml version="1.1"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:isc="http://simpleSys.ru/xml/library/ISC" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:mfms="http://simpleSys.ru/xml/library/MFMS"
                xmlns:common="http://simpleSys.ru/xml/library/common" exclude-result-prefixes="isc xs common mfms">

    <xsl:import href="common.xsl"/>
    <xsl:output indent="yes" method="xml" encoding="UTF-8" name="format"/>

    <xsl:param name="tmpDir" as="xs:string" select="'file:///f:/target/scala-2.11/src_managed/main/defs/app/tmp'"/>
    <xsl:variable name="_tmpDir" as="xs:string" select="common:check-last-slash($tmpDir)"/>
    
    <xsl:param name="jsDir" as="xs:string" select="'file:///f:/src/main/resources/defs/app/js'"/>
    <xsl:variable name="_jsDir" as="xs:string" select="common:check-last-slash($jsDir)"/>

    <xsl:template name="Embedding">
        <xsl:message select="'Embedding begin.'"/>
        <xsl:variable as="node()*" name="source" select="doc('../xml/MainView.xml')"/>
        <xsl:variable as="xs:string" name="pathOf_DynamicPartOfRibbon" select="concat($_tmpDir, 'dynamicPartOfRibbon.xml')"/>
        <xsl:choose>
            <xsl:when test="doc-available($pathOf_DynamicPartOfRibbon)">
                <xsl:variable as="node()*" name="embSource" select="doc($pathOf_DynamicPartOfRibbon)"/>

                <xsl:result-document href="{concat($_tmpDir, 'FullMainView.xml')}" format="format" validation="strict">
                    <xsl:apply-templates select="$source/mfms:MainView">
                        <xsl:with-param name="embSource" select="$embSource" tunnel="yes"/>
                    </xsl:apply-templates>
                </xsl:result-document>
            </xsl:when>
            <xsl:otherwise>
                <xsl:variable as="node()*" name="embSource"/>

                <xsl:result-document href="{concat($_tmpDir, 'FullMainView.xml')}" format="format" validation="strict">
                    <xsl:apply-templates select="$source/mfms:MainView">
                        <xsl:with-param name="embSource" select="$embSource" tunnel="yes"/>
                    </xsl:apply-templates>
                </xsl:result-document>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:message select="'Embedding done.'"/>
    </xsl:template>

    <xsl:template match="isc:RibbonGroupDyn[isc:Identifier='DynamicPart']">
        <xsl:param as="node()*" name="embSource" tunnel="yes"/>
        <xsl:comment select="'Dynamic part.'"/>
        <xsl:copy-of select="$embSource/isc:Members/node()"/>
        <xsl:comment select="'End of Dynamic part.'"/>
    </xsl:template>

    <xsl:template match="isc:FunctionsFileURL">
        <isc:FunctionsFileURL>
            <xsl:value-of select="concat($_jsDir,.)"/>
        </isc:FunctionsFileURL>
    </xsl:template>

    <xsl:template match="isc:DataViewSSDyn">
        <DataViewSSDyn xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://simpleSys.ru/xml/library/ISC" xsi:schemaLocation="http://simpleSys.ru/xml/library/ISC  http://toucan.simplesys.lan/xml/xsd/v1.0.0-1/schemaISC.xsd">
            <xsl:apply-templates/>
        </DataViewSSDyn>
    </xsl:template>
</xsl:stylesheet>
